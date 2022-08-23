import java.io.IOException;

public class ExerciseRunner {
    public static void main(String[] args) throws IOException {
        NumberDao numberDao = new NumberDao("number.db");
        ComputeService computeService = new ComputeService(numberDao);
        computeService.add(new IntOperation(14, 5490));
    }
}