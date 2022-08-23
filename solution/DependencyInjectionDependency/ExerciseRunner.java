public class ExerciseRunner {
    public static void main(String[] args) {
        DependencyEngine engine = new DependencyEngine();
        engine.init(new Class[]{TestComponent.class, ComponentSecond.class});

        System.out.println(((TestComponent) engine.getInstance("componentTest")).getString());
        System.out.println(((ComponentSecond) engine.getInstance("ComponentSecond")).getValue());
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
    public int getValue() {
        return 16;
    }
}