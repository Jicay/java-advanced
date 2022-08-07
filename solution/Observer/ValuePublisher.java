import java.util.ArrayList;
import java.util.List;

public class ValuePublisher {
    private List<NumericBaseObserver> observers;
    private int currentState;

    public ValuePublisher() {
        observers = new ArrayList<>();
        currentState = 0;
    }

    public void updateState(int value) {
        currentState = value;
        notifyAllObservers();
    }

    private void notifyAllObservers() {
        observers.forEach(it -> it.updateState(currentState));
    }

    public void subscribe(NumericBaseObserver observer) {
        observers.add(observer);
    }

    public void unsubscribe(NumericBaseObserver observer) {
        observers.remove(observer);
    }

}
