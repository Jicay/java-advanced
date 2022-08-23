import java.io.IOException;

@Component(name = "computeService")
public class StringService implements ComputeServiceAPI {
    @Inject(name = "numberDao")
    private NumberDaoAPI numberDao;

    public void add(IntOperation intOperation) throws IOException {
        numberDao.saveInt(intOperation.number1() + intOperation.number2());
    }

    public int get() throws IOException {
        return numberDao.getInt();
    }
}