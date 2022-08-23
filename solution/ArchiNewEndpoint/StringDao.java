import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@Component(name = "numberDao")
public class NumberDao implements NumberDaoAPI {
    @Inject(name = "numberDb")
    private String fileName;

    public void saveInt(int number) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.print(number);

        printWriter.close();
    }

    public int getInt() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        String line;

        if ((line = reader.readLine()) != null) {
            return Integer.parseInt(line);
        }

        return 0;
    }

}