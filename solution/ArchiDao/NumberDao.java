import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class NumberDao {

    private static final String FILE_NAME = "number.db";

    public static void saveInt(int number) throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_NAME);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.print(number);

        printWriter.close();
    }

}