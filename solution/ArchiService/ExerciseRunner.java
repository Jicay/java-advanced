import java.io.IOException;

public class ExerciseRunner {
    public static void main(String[] args) throws IOException {
        NumberDao numberDao = new NumberDao("number.db");
        numberDao.saveInt(13);
    }
}