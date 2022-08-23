import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class NumberDao {
    private String fileName;

    public NumberDao(String fileName) {
        this.fileName = fileName;
    }

    public void saveInt(int number) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.print(number);

        printWriter.close();
    }

}