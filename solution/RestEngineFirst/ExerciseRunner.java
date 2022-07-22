import java.io.IOException;

public class ExerciseRunner {

    public static void main(String[] args) throws IOException {
        new RestEngine().startServer(8080, new Class[]{MessageEndpoint.class});
    }
}

@Rest(path = "/api/message")
class MessageEndpoint {
    @Get(path = "/hello")
    public String hello() {
        return "Hello";
    }

    @Get(path = "/bonjour")
    public String bonjour() {
        return "Bonjour";
    }
}
