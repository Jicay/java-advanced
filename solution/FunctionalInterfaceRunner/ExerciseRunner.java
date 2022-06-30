import java.io.IOException;

public class ExerciseRunner {
    public static void main(String[] args) throws IOException {
        System.out.println(FunctionalInterfaceRunner.createAddition().operation(4, 5));
        System.out.println(FunctionalInterfaceRunner.createFactorialAndSubstract().operation(5, 10));
    }
}