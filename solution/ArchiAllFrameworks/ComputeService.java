import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ComputeService implements ComputeServiceAPI {
    private NumberDaoAPI numberDao;

    public ComputeService(NumberDaoAPI numberDao) {
        this.numberDao = numberDao;
    }

    public void add(IntOperation intOperation) throws IOException {
        numberDao.saveInt(intOperation.number1() + intOperation.number2());
    }

    public int get() throws IOException {
        return numberDao.getInt();
    }
}