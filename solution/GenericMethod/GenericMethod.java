import java.util.List;
import java.util.stream.Collectors;

public class GenericMethod {
    public static <E> String concat(List<E> s) {
        return s.stream().map(E::toString).collect(Collectors.joining(","));
    }

    public static <E extends Number> double max(List<E> s) {
        return s.stream()
                .mapToDouble(Number::doubleValue)
                .max().getAsDouble();
    }

}