import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ComputeService {
    private NumberDao numberDao;

    public ComputeService(NumberDao numberDao) {
        this.numberDao = numberDao;
    }

    public void add(IntOperation intOperation) throws IOException {
        numberDao.saveInt(intOperation.number1() + intOperation.number2());
    }

}