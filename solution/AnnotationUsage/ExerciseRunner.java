public class ExerciseRunner {
    public static void main(String[] args) throws NoSuchMethodException {
        ExerciseTest exerciseTest = new ExerciseTest();

        System.out.println(Printer.getAllValuesByLevel(exerciseTest, 1));
    }
}

@Print
class ExerciseTest {
    @Marked(level = 0)
    private int attributeOne = 13;

    @Marked(level = 0)
    public ClassOne classOne = new ClassOne();

    @Marked(level = 1)
    protected ClassTwo classTwo = new ClassTwo();

    @Marked(level = 2)
    public String methodOne() {
        return "Hello";
    }

    public int methodTwo() {
        return 42;
    }

    @Marked(level = 1)
    protected Double methodThree() {
        return 33.33;
    }
}

@Print(true)
class ClassOne {
    @Marked(level = 0)
    private int containedAttributeOne = 19;

    @Marked(level = 1)
    private String methodContained() {
        return "Content of method";
    }
}

@Print(false)
class ClassTwo {
    @Marked(level = 0)
    private boolean containedAttributeTwo = true;
}