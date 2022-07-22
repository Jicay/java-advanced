import java.util.List;

public class ExerciseRunner {
    public static void main(String[] args) {
        System.out.println(GenericMethod.concat(List.of(3, 5, 7, 10)));
        System.out.println(GenericMethod.concat(List.of("Hello", "World")));
        System.out.println(GenericMethod.max(List.of(3, 81, 4394, 3193)));
        System.out.println(GenericMethod.max(List.of(-1293.32, 383.128, -2183.32)));
    }
}