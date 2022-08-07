import java.util.ArrayList;
import java.util.List;

public class DecimalBaseObserver implements NumericBaseObserver {

    private List<String> events;

    public DecimalBaseObserver() {
        events = new ArrayList<>();
    }

    @Override
    public void updateState(int state) {
        events.add(Integer.toString(state));
    }

    @Override
    public List<String> getEvents() {
        return events;
    }
}
