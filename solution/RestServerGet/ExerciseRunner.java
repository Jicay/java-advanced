import java.io.IOException;

public class ExerciseRunner {

    public static void main(String[] args) throws IOException {
        new RestServer().startServer(8080);
    }
}
