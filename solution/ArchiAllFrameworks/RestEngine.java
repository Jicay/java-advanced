import static java.util.stream.Collectors.groupingBy;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RestEngine {

    private HttpServer server;

    public void startServer(int port, Object[] controllers) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);

        for (Object controller : controllers) {
            manageRestController(controller);
        }

        server.setExecutor(null); // creates a default executor
        server.start();
    }

    private void manageRestController(Object controller) {
        Class<?> clazz = controller.getClass();
        var annotationClass = clazz.getAnnotation(Rest.class);
        if (annotationClass == null) {
            return;
        }
        String mainPath = annotationClass.path();
        server.createContext(mainPath, (exchange -> {
            try {
                Map<String, Method> mapping = new HashMap<>();
                mapping.putAll(getGetAnnotatedMethods(clazz, mainPath));
                mapping.putAll(gePostAnnotatedMethods(clazz, mainPath));
                String key = exchange.getRequestMethod() + exchange.getRequestURI().getPath();
                if (mapping.containsKey(key)) {
                    Method resolvedMethod = mapping.get(key);

                    Map<String, String> queries = splitQuery(exchange.getRequestURI().getQuery());

                    List<Object> args = new ArrayList<>();
                    for (Parameter parameter : resolvedMethod.getParameters()) {
                        if (exchange.getRequestMethod().equals("GET")) {
                            QueryParam annotation = parameter.getAnnotation(QueryParam.class);
                            if (annotation == null || !queries.containsKey(annotation.name())) {
                                args.add(null);
                            } else {
                                String value = queries.get(annotation.name());
                                if (parameter.getType().equals(String.class)) {
                                    args.add(value);
                                } else if (parameter.getType().equals(Integer.class)) {
                                    args.add(Integer.valueOf(value));
                                }
                            }
                        } else {
                            Map<String, String> body = splitBody(new String(exchange.getRequestBody().readAllBytes()));
                            Class<?> parameterClass = parameter.getType();
                            Object arg = parameterClass.getDeclaredConstructor().newInstance();
                            for (Field field : parameterClass.getDeclaredFields()) {
                                if (body.containsKey(field.getName())) {
                                    if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
                                        field.set(arg, Integer.valueOf(body.get(field.getName())));
                                    } else {
                                        field.set(arg, body.get(field.getName()));
                                    }
                                }
                            }
                            args.add(arg);
                        }
                    }

                    Object res = resolvedMethod.invoke(controller, args.toArray());

                    String stringRes = format(res);

                    exchange.sendResponseHeaders(200, stringRes.getBytes().length);

                    OutputStream output = exchange.getResponseBody();
                    output.write(stringRes.getBytes());
                    output.flush();
                    exchange.close();
                } else {
                    exchange.sendResponseHeaders(404, 0);
                    exchange.close();
                }
            } catch (NumberFormatException e) {
                exchange.sendResponseHeaders(400, 0);
                exchange.close();
            } catch (Exception e) {
                exchange.sendResponseHeaders(500, 0);
                exchange.close();
            }

        }));
    }

    private String format(Object o) {
        if (o.getClass().isPrimitive() || o.getClass().equals(String.class) || o instanceof Number) {
            return o.toString();
        } else {
            return Arrays.stream(o.getClass().getDeclaredFields())
                    .peek(f -> f.setAccessible(true))
                    .map(a -> {
                        try {
                            return a.getName() + "=" + a.get(o);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }).collect(Collectors.joining(";"));
        }
    }

    private Map<String, Method> gePostAnnotatedMethods(Class<?> clazz, String mainPath) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(Post.class))
                .collect(Collectors.toMap(m -> "POST" + mainPath + m.getAnnotation(Post.class).path(), m -> m, (m1, m2) -> m1));
    }

    private Map<String, Method> getGetAnnotatedMethods(Class<?> clazz, String mainPath) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(Get.class))
                .collect(Collectors.toMap(m -> "GET" + mainPath + m.getAnnotation(Get.class).path(), m -> m, (m1, m2) -> m1));
    }

    private static Map<String, String> splitQuery(String query) {
        if (query == null || "".equals(query)) {
            return Collections.emptyMap();
        }

        return Pattern.compile("&").splitAsStream(query)
                .map(s -> Arrays.copyOf(s.split("="), 2))
                .collect(Collectors.toMap(s -> decode(s[0]), s -> decode(s[1])));
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
