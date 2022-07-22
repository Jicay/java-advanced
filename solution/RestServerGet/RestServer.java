import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class RestServer {

    private HttpServer server;
    public void startServer(int port) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/api/hello", (exchange -> {
            if (exchange.getRequestURI().getPath().equals("/api/hello") && exchange.getRequestMethod().equalsIgnoreCase("GET")) {
                String respText = "Hello world!";
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

    public void stopServer() {
        server.stop(1);
    }
}
