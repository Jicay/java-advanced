import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionalInterfaceRunner {
    public static Operation createAddition() {
        return (x, y) -> x + y;
    }

    public static Operation createFactorialAndSubstract() {
        return (x, y) -> {
            int res = 1;
            while (x > 1) {
                res *= x;
                x--;
            }
            return res - y;
        };
    }

}