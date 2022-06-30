import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.regex.Pattern;

public class Lambda {
    public static String execute(BiFunction<Integer, Integer, String> f, int i, int i1) {
        return f.apply(i, i1);
    }

    public static Function<Integer, Integer> buildFibonacciLambda() {
        return n -> {
            if (n < 0) return 0;
            if (n == 0 || n == 1) return 1;
            int c = 1;
            int p = 1;
            int i = 1;
            while (i < n) {
                int s = c;
                c = c + p;
                p = s;
                i++;
            }
            return c;
        };
    }
}