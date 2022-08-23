public class ExerciseRunner {
    public static void main(String[] args) {
        DependencyEngine engine = new DependencyEngine();
        engine.init(new Class[]{AnotherComponent.class, TestComponent.class, ComponentSecond.class});

        System.out.println(((TestComponent) engine.getInstance("componentTest")).getString());
        System.out.println(((ComponentSecond) engine.getInstance("ComponentSecond")).getValue());
        System.out.println(((AnotherComponent) engine.getInstance("AnotherComponent")).concat());
    }
}

@Component
class AnotherComponent {
    @Inject(name = "componentTest")
    private TestComponent testComponent;

    @Inject
    private ComponentSecond componentSecond;

    public String concat() {
        return testComponent.getString() + componentSecond.getValue();
    }
}

@Component(name = "componentTest")
class TestComponent {
    public String getString() {
        return "Test String";
    }
}

@Component
class ComponentSecond {
    @Inject(name = "componentTest")
    private TestComponent testComponent;

    public int getValue() {
        return 16 + testComponent.getString().length();
    }
}