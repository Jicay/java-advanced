import java.io.IOException;
import java.util.Optional;

public class ExerciseRunner {
    public static void main(String[] args) throws IOException {
        System.out.println(OptionalComputer.retrieveOrGetDefault(Optional.empty()));
        System.out.println(OptionalComputer.retrieveOrGetDefault(Optional.of(12)));
        try {
            System.out.println(OptionalComputer.retrieveOrError(Optional.empty()));
        } catch (Exception e){
            System.out.println("There is an exception");
        }
        System.out.println(OptionalComputer.retrieveOrError(Optional.of(12)));
        System.out.println(OptionalComputer.addIntegerIfGreaterThan5(1, 2));
        System.out.println(OptionalComputer.addIntegerIfGreaterThan5(18, 2));
    }
}