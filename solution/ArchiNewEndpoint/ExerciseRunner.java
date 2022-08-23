import java.io.IOException;

public class ExerciseRunner {
    public static void main(String[] args) throws IOException {
        DependencyEngine engine = new DependencyEngine();
        engine.loadConfigFile("solution/ArchiAllFrameworks/configuration.txt");
        engine.init(new Class[]{ComputeController.class, ComputeService.class, NumberDao.class});

        new RestEngine().startServer(8080, new Object[]{engine.getInstance("computeController")});
    }
}