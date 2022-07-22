import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import org.junit.jupiter.api.Test;

class RestEngineFirstTest {
    @Test
    void testGetHello() throws Exception {
        RestEngine server = new RestEngine();
        HttpClient client = HttpClient.newBuilder().build();

        server.startServer(18080, new Class[]{PrintEndpoint.class, AnswerEndpoint.class});

        HttpRequest requestGetFirst200 = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:18080/api/print/string/first"))
                .GET()
                .build();

        HttpResponse<String> responseGetFirst200 = client.send(requestGetFirst200, BodyHandlers.ofString());

        assertThat(responseGetFirst200.statusCode())
                .withFailMessage("Status code of GET /api/print/string/first should be 200, but was %d", responseGetFirst200.statusCode())
                .isEqualTo(200);
        assertThat(responseGetFirst200.body())
                .withFailMessage("Body of GET /api/print/string/first should be \"Welcome to Ynov\", but was %s", responseGetFirst200.body())
                .isEqualTo("Welcome to Ynov");




        HttpRequest requestGetSuccess200 = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:18080/api/print/string/success"))
                .GET()
                .build();

        HttpResponse<String> responseGetSuccess200 = client.send(requestGetSuccess200, BodyHandlers.ofString());

        assertThat(responseGetSuccess200.statusCode())
                .withFailMessage("Status code of GET /api/print/string/success should be 200, but was %d", responseGetSuccess200.statusCode())
                .isEqualTo(200);
        assertThat(responseGetSuccess200.body())
                .withFailMessage("Body of GET /api/print/string/success should be \"you succeed\", but was \"%s\"", responseGetSuccess200.body())
                .isEqualTo("you succeed");


        HttpRequest requestGetAnswer200 = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:18080/api/answer"))
                .GET()
                .build();

        HttpResponse<String> responseGetAnswer200 = client.send(requestGetAnswer200, BodyHandlers.ofString());

        assertThat(responseGetAnswer200.statusCode())
                .withFailMessage("Status code of GET /api/answer should be 200, but was %d", responseGetAnswer200.statusCode())
                .isEqualTo(200);
        assertThat(responseGetAnswer200.body())
                .withFailMessage("Body of GET /api/answer should be \"42\", but was \"%s\"", responseGetAnswer200.body())
                .isEqualTo("42");





        HttpRequest requestGet404 = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:18080/api/print/string/first1"))
                .GET()
                .build();

        HttpResponse<String> responseGet404 = client.send(requestGet404, BodyHandlers.ofString());

        assertThat(responseGet404.statusCode())
                .withFailMessage("Status code of GET /api/print/string/first1 should be 404, but was %d", responseGet404.statusCode())
                .isEqualTo(404);

        HttpRequest requestPost404 = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:18080/api/print/string/first"))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> responsePost404 = client.send(requestPost404, BodyHandlers.ofString());

        assertThat(responsePost404.statusCode())
                .withFailMessage("Status code of POST /api/print/string/first should be 404, but was %d", responsePost404.statusCode())
                .isEqualTo(404);

        server.stopServer();
    }
}

@Rest(path = "/api/print/string")
class PrintEndpoint {
    @Get(path = "/first")
    public String first() {
        return "Welcome to Ynov";
    }

    @Get(path = "/success")
    public String success() {
        return "you succeed";
    }
}


@Rest(path = "/api/answer")
class AnswerEndpoint {
    @Get
    public int get() {
        return 42;
    }
}