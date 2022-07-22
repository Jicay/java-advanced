import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RestEngine {

    private HttpServer server;

    public void startServer(int port, Class[] classes) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);

        for (Class clazz : classes) {
            manageRestClass(clazz);
        }

        server.setExecutor(null); // creates a default executor
        server.start();
    }

    private void manageRestClass(Class<?> clazz) {
        var annotationClass = clazz.getAnnotation(Rest.class);
        if (annotationClass == null) {
            return;
        }
        String mainPath = annotationClass.path();
        server.createContext(mainPath, (exchange -> {
            try {
                Map<String, Method> mapping = new HashMap<>();
                mapping.putAll(getGetAnnotatedMethods(clazz, mainPath));
                String key = exchange.getRequestMethod() + exchange.getRequestURI().getPath();
                if (mapping.containsKey(key)) {
                    Method resolvedMethod = mapping.get(key);
                    Object controller = clazz.getDeclaredConstructor().newInstance();
                    Object res = resolvedMethod.invoke(controller);
                    exchange.sendResponseHeaders(200, res.toString().getBytes().length);
                    OutputStream output = exchange.getResponseBody();
                    output.write(res.toString().getBytes());
                    output.flush();
                    exchange.close();
                } else {
                    exchange.sendResponseHeaders(404, 0);
                    exchange.close();
                }
            } catch (Exception e) {
                exchange.sendResponseHeaders(500, 0);
                exchange.close();
            }

        }));
    }

    private Map<String, Method> getGetAnnotatedMethods(Class<?> clazz, String mainPath) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(Get.class))
                .collect(Collectors.toMap(m -> "GET" + mainPath + m.getAnnotation(Get.class).path(), m -> m, (m1,m2) -> m1));
    }

    private static Map<String, List<String>> splitQuery(String query) {
        if (query == null || "".equals(query)) {
            return Collections.emptyMap();
        }

        return Pattern.compile("&").splitAsStream(query)
                .map(s -> Arrays.copyOf(s.split("="), 2))
                .collect(groupingBy(s -> decode(s[0]), mapping(s -> decode(s[1]), toList())));

    }

    private static Map<String, String> splitBody(String query) {
        if (query == null || "".equals(query)) {
            return Collections.emptyMap();
        }

        return Pattern.compile(";").splitAsStream(query)
                .map(s -> Arrays.copyOf(s.split("="), 2))
                .collect(Collectors.toMap(s -> decode(s[0]), s -> decode(s[1])));

    }

    private static String decode(final String encoded) {
        return encoded == null ? null : URLDecoder.decode(encoded, StandardCharsets.UTF_8);
    }

    public void stopServer() {
        server.stop(1);
    }
}
