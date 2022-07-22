public class ExerciseRunner {
    public static void main(String[] args) throws NoSuchMethodException {
        Class<ExerciseTest> exerciseClass = ExerciseTest.class;

        System.out.println(exerciseClass.getAnnotation(TestClass.class).name());
        System.out.println(exerciseClass.getDeclaredMethod("methodOne").getAnnotation(TestMethod.class).name());
        System.out.println(exerciseClass.getDeclaredMethod("methodTwo").getAnnotation(TestMethod.class).name());
    }
}

@TestClass(name = "Runner")
class ExerciseTest {

    @TestMethod
    public void methodOne() {}

    @TestMethod(name = "MethodRun")
    public void methodTwo() {}
}