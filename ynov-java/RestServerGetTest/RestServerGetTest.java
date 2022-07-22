import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import org.junit.jupiter.api.Test;

class RestServerGetTest {
    @Test
    void testRestServer() throws Exception {
        RestServer server = new RestServer();
        HttpClient client = HttpClient.newBuilder().build();

        server.startServer(18080);

        HttpRequest requestGet200 = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:18080/api/hello"))
                .GET()
                .build();

        HttpResponse<String> responseGet200 = client.send(requestGet200, BodyHandlers.ofString());

        assertThat(responseGet200.statusCode())
                .withFailMessage("Status code of GET /api/hello should be 200, but was %d", responseGet200.statusCode())
                .isEqualTo(200);
        assertThat(responseGet200.body())
                .withFailMessage("Body of GET /api/hello should be \"Hello world!\", but was %s", responseGet200.body())
                .isEqualTo("Hello world!");

        HttpRequest requestGet404 = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:18080/api/hello1"))
                .GET()
                .build();

        HttpResponse<String> responseGet404 = client.send(requestGet404, BodyHandlers.ofString());

        assertThat(responseGet404.statusCode())
                .withFailMessage("Status code of GET /api/hello1 should be 404, but was %d", responseGet404.statusCode())
                .isEqualTo(404);

        HttpRequest requestPost404 = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:18080/api/hello"))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> responsePost404 = client.send(requestPost404, BodyHandlers.ofString());

        assertThat(responsePost404.statusCode())
                .withFailMessage("Status code of POST /api/hello should be 404, but was %d", responsePost404.statusCode())
                .isEqualTo(404);

        server.stopServer();
    }

}