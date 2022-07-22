import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RestServer {

    private HttpServer server;

    public void startServer(int port) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/api/hello", (exchange -> {
            if (exchange.getRequestURI().getPath().equals("/api/hello") && exchange.getRequestMethod().equalsIgnoreCase("GET")) {
                Map<String, List<String>> queries = splitQuery(exchange.getRequestURI().getQuery());
                String respText = "Hello world!";
                if (queries.containsKey("name")) {
                    respText = "Hello world " + queries.get("name").get(0) + " !";
                }
                exchange.sendResponseHeaders(200, respText.getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(respText.getBytes());
                output.flush();
                exchange.close();
            } else {
                exchange.sendResponseHeaders(404, 0);
                exchange.close();
            }
        }));
        server.createContext("/api/add", (exchange -> {
            if (exchange.getRequestURI().getPath().equals("/api/add") && exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                String respText = "The result is ";
                String body = new String(exchange.getRequestBody().readAllBytes());
                Map<String, String> bodyMap = splitBody(body);
                respText += (Integer.parseInt(bodyMap.getOrDefault("x", "0")) + Integer.parseInt(bodyMap.getOrDefault("y", "0")));
                exchange.sendResponseHeaders(200, respText.getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(respText.getBytes());
                output.flush();
                exchange.close();
            } else {
                exchange.sendResponseHeaders(404, 0);
                exchange.close();
            }
        }));
        server.setExecutor(null); // creates a default executor
        server.start();
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
