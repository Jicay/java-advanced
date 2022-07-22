import java.util.HashSet;

public class ExerciseRunner {
    public static void main(String[] args) throws NoSuchMethodException {
        System.out.println(Introspection.getClassName(ExerciseTest.class));
        System.out.println(Introspection.getPublicMethodName(ExerciseTest.class));
        System.out.println(Introspection.getAllParents(HashSet.class));
    }
}

class ExerciseTest {

    public void methodOne() {}

    public void methodTwo() {}

    private void privateMethod() {}
}