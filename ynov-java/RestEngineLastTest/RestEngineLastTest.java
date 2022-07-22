import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import org.junit.jupiter.api.Test;

class RestEngineSecondTest {
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


        HttpRequest requestGetSuccessNote200 = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:18080/api/print/string/success?note=18"))
                .GET()
                .build();

        HttpResponse<String> responseGetSuccessNote200 = client.send(requestGetSuccessNote200, BodyHandlers.ofString());

        assertThat(responseGetSuccessNote200.statusCode())
                .withFailMessage("Status code of GET /api/print/string/success?note=18 should be 200, but was %d", responseGetSuccessNote200.statusCode())
                .isEqualTo(200);
        assertThat(responseGetSuccessNote200.body())
                .withFailMessage("Body of GET /api/print/string/success?note=18 should be \"you succeed, with the note 18\", but was \"%s\"", responseGetSuccessNote200.body())
                .isEqualTo("you succeed, with the note 18");


        HttpRequest requestGetSuccessExam200 = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:18080/api/print/string/success?exam=Mastere&note=15"))
                .GET()
                .build();

        HttpResponse<String> responseGetSuccessExam200 = client.send(requestGetSuccessExam200, BodyHandlers.ofString());

        assertThat(responseGetSuccessExam200.statusCode())
                .withFailMessage("Status code of GET /api/print/string/success?exam=Mastere&note=15 should be 200, but was %d", responseGetSuccessExam200.statusCode())
                .isEqualTo(200);
        assertThat(responseGetSuccessExam200.body())
                .withFailMessage("Body of GET /api/print/string/success?exam=Mastere&note=15 should be \"you succeed your exam Mastere, with the note 15\", but was \"%s\"", responseGetSuccessExam200.body())
                .isEqualTo("you succeed your exam Mastere, with the note 15");


        HttpRequest requestGetSuccessNote00 = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:18080/api/print/string/success?note=Bien"))
                .GET()
                .build();

        HttpResponse<String> responseGetSuccessNote400 = client.send(requestGetSuccessNote00, BodyHandlers.ofString());

        assertThat(responseGetSuccessNote400.statusCode())
                .withFailMessage("Status code of GET /api/print/string/success?note=Bien should be 400, but was %d", responseGetSuccessNote400.statusCode())
                .isEqualTo(400);



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



        HttpRequest requestPost200 = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:18080/api/answer/operation"))
                .POST(HttpRequest.BodyPublishers.ofString("prefix=start;a=43;b=438"))
                .build();

        HttpResponse<String> responsePost200 = client.send(requestPost200, BodyHandlers.ofString());

        assertThat(responsePost200.statusCode())
                .withFailMessage("Status code of POST /api/answer/operation should be 200, but was %d", responsePost200.statusCode())
                .isEqualTo(200);
        assertThat(responsePost200.body())
                .withFailMessage("Body of POST /api/answer/operation should be \"substract=-395;appendFirst=start43\", but was %s", responsePost200.body())
                .isEqualTo("substract=-395;appendFirst=start43");


        HttpRequest requestPost400 = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:18080/api/answer/operation"))
                .POST(HttpRequest.BodyPublishers.ofString("prefix=start;a=Test;b=438"))
                .build();

        HttpResponse<String> responsePost400 = client.send(requestPost400, BodyHandlers.ofString());

        assertThat(responsePost400.statusCode())
                .withFailMessage("Status code of POST /api/answer/operation should be 400, but was %d", responsePost400.statusCode())
                .isEqualTo(400);




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
    public String success(@QueryParam(name = "exam") String exam, @QueryParam(name = "note") Integer note) {

        String val = "you succeed";
        if (exam != null) {
            val += " your exam " + exam;
        }
        if (note != null) {
            val += ", with the note " + note;
        }
        return val;
    }
}


@Rest(path = "/api/answer")
class AnswerEndpoint {
    @Get
    public int get() {
        return 42;
    }

    @Post(path = "/operation")
    public Res operation(Value val) {
        Res res = new Res();
        res.substract = val.a - val.b;
        res.appendFirst = val.prefix + val.a;
        return res;
    }
}

class Value {
    public int a;
    public int b;
    public String prefix;
}

class Res {
    public int substract;
    public String appendFirst;
}

