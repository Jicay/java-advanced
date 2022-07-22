import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.fail;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

class AnnotationUsageTest {

    @Test
    void checkPrint() {
        Class<Print> clazz = Print.class;
        assertThat(clazz.isAnnotation())
                .withFailMessage("Print should be an annotation")
                .isTrue();

        var target = clazz.getAnnotation(Target.class);
        assertThat(target)
                .withFailMessage("Print should have a Target")
                .isNotNull();
        assertThat(target.value())
                .withFailMessage("Print should have a target to class")
                .containsExactly(ElementType.TYPE);

        var retention = clazz.getAnnotation(Retention.class);
        assertThat(retention)
                .withFailMessage("Print should have a defined Retention")
                .isNotNull();
        assertThat(retention.value())
                .withFailMessage("Print should have a runtime retention")
                .isEqualTo(RetentionPolicy.RUNTIME);

        try {
            var value = clazz.getDeclaredMethod("value");

            assertThat(value.getDefaultValue())
                    .withFailMessage("value method should have a default value equals to true")
                    .isEqualTo(true);

        } catch (NoSuchMethodException e) {
            fail("Print should have a value method");
        }
    }

    @Test
    void checkMarked() {
        Class<Marked> clazz = Marked.class;
        assertThat(clazz.isAnnotation())
                .withFailMessage("Marked should be an annotation")
                .isTrue();

        var target = clazz.getAnnotation(Target.class);
        assertThat(target)
                .withFailMessage("Marked should have a Target")
                .isNotNull();
        assertThat(target.value())
                .withFailMessage("Marked should have a target to method")
                .containsExactlyInAnyOrder(ElementType.METHOD, ElementType.FIELD);

        var retention = clazz.getAnnotation(Retention.class);
        assertThat(retention)
                .withFailMessage("Marked should have a defined Retention")
                .isNotNull();
        assertThat(retention.value())
                .withFailMessage("Marked should have a runtime retention")
                .isEqualTo(RetentionPolicy.RUNTIME);

        try {
            var level = clazz.getDeclaredMethod("level");

            assertThat(level.getDefaultValue())
                    .withFailMessage("level method should have a default value equals to 2")
                    .isEqualTo(2);

        } catch (NoSuchMethodException e) {
            fail("Marked should have a level method");
        }
    }

    @Test
    void testPrinter() {
        var res1 = Printer.getAllValuesByLevel(new HashSet<>(), 10);
        assertThat(res1)
                .withFailMessage("List of value should be empty")
                .isEmpty();

        var res2 = Printer.getAllValuesByLevel(new ExerciseForTest(), 10);
        assertThat(res2)
                .withFailMessage("List of value should contains [13, 19, \"Good\", 4894.32, \"Content of method\", \"Hello\", 33.33], but was %s", res2)
                .containsExactlyInAnyOrder(13, 19, "Good", 4894.32, "Content of method", "Hello", 33.33);

        var res3 = Printer.getAllValuesByLevel(new ExerciseForTest(), 0);
        assertThat(res3)
                .withFailMessage("List of value should contains [13, 19], but was %s", res3)
                .containsExactlyInAnyOrder(13, 19);
    }

}

@Print
class ExerciseForTest {
    @Marked(level = 0)
    private int attributeOne = 13;

    @Marked(level = 0)
    public ClassFirst classOne = new ClassFirst();

    @Marked(level = 1)
    protected ClassSecond classTwo = new ClassSecond();

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
class ClassFirst {
    @Marked(level = 0)
    private int containedAttributeOne = 19;

    @Marked
    public ClassThree classThree = new ClassThree();

    @Marked(level = 3)
    private double test = 4894.32;

    @Marked(level = 1)
    private String methodContained() {
        return "Content of method";
    }
}

@Print(false)
class ClassSecond {
    @Marked(level = 0)
    private boolean containedAttributeTwo = true;
}

@Print
class ClassThree {
    @Marked(level = 0)
    private String containerToto = "Good";
}