import java.io.IOException;

public interface StringServiceAPI {
    void add(String string) throws IOException;

    String get() throws IOException;

    int getSize() throws IOException;
}
