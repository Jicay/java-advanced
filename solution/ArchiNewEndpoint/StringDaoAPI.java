import java.io.IOException;

public interface NumberDaoAPI {
    void saveInt(int number) throws IOException;

    int getInt() throws IOException;
}
