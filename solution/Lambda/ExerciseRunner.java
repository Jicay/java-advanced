import java.io.IOException;

public class ExerciseRunner {
    public static void main(String[] args) throws IOException {
        System.out.println(Lambda.execute((x, y) -> String.valueOf(x + y), 2, 3));
        System.out.println(Lambda.execute((x, y) -> x + " " + (y-1), 2, 3));
        System.out.println(Lambda.buildFibonacciLambda().apply(0));
        System.out.println(Lambda.buildFibonacciLambda().apply(5));
    }
}