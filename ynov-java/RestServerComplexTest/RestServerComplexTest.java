import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import org.junit.jupiter.api.Test;

class RestServerComplexTest {
    @Test
    void testGetHello() throws Exception {
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

        HttpRequest requestGetParams200 = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:18080/api/hello?test=dfjskj&name=Toto"))
                .GET()
                .build();

        HttpResponse<String> responseGetParams200 = client.send(requestGetParams200, BodyHandlers.ofString());

        assertThat(responseGetParams200.statusCode())
                .withFailMessage("Status code of GET /api/hello?test=dfjskj&name=Toto should be 200, but was %d", responseGetParams200.statusCode())
                .isEqualTo(200);
        assertThat(responseGetParams200.body())
                .withFailMessage("Body of GET /api/hello?test=dfjskj&name=Toto should be \"Hello world Toto !\", but was \"%s\"", responseGetParams200.body())
                .isEqualTo("Hello world Toto !");

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

    @Test
    void testPostAdd() throws Exception {
        RestServer server = new RestServer();
        HttpClient client = HttpClient.newBuilder().build();

        server.startServer(18080);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:18080/api/add"))
                .POST(HttpRequest.BodyPublishers.ofString("x=4385;y=39328"))
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        assertThat(response.statusCode())
                .withFailMessage("Status code of POST /api/add should be 200, but was %d", response.statusCode())
                .isEqualTo(200);
        assertThat(response.body())
                .withFailMessage("Body of POST /api/add should be \"The result is 43713\", but was \"%s\"", response.body())
                .isEqualTo("The result is 43713");

        server.stopServer();
    }

}