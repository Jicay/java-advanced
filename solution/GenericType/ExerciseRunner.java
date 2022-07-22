import java.util.List;

public class ExerciseRunner {
    public static void main(String[] args) {
        Triplet<Integer, String, List<Double>> triplet = new Triplet<>(14, "I'm the second", List.of(234.23, 584984.23));
        System.out.println(triplet);
    }
}