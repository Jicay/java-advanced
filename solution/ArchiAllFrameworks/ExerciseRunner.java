import java.io.IOException;

public class ExerciseRunner {
    public static void main(String[] args) throws IOException {
        NumberDao numberDao = new NumberDao("number.db");
        ComputeService computeService = new ComputeService(numberDao);
        ComputeController computeController = new ComputeController(computeService);

        new RestEngine().startServer(8080, new Object[]{computeController});
    }
}