import java.util.Optional;

public class OptionalComputer {
    public static Integer retrieveOrGetDefault(Optional<Integer> value) {
        return value.orElse(42);
    }

    public static Integer retrieveOrError(Optional<Integer> value) {
        return value.orElseThrow(() -> new IllegalArgumentException("Value not correct"));
    }

    public static Optional<Integer> addIntegerIfGreaterThan5(int i, int j) {
        if (i + j < 5) {
            return Optional.empty();
        } else {
            return Optional.of(i + j);
        }
    }

}