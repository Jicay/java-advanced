import java.io.IOException;

public interface ComputeServiceAPI {
    void add(IntOperation intOperation) throws IOException;

    int get() throws IOException;
}
