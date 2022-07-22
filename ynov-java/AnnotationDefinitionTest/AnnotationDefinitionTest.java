import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.fail;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;

class AnnotationDefinitionTest {

    @Test
    void checkTestClass() {
        Class<TestClass> clazz = TestClass.class;
        assertThat(clazz.isAnnotation())
                .withFailMessage("TestClass should be an annotation")
                .isTrue();

        var target = clazz.getAnnotation(Target.class);
        assertThat(target)
                .withFailMessage("TestClass should have a Target")
                .isNotNull();
        assertThat(target.value())
                .withFailMessage("TestClass should have a target to class")
                .containsExactly(ElementType.TYPE);

        var retention = clazz.getAnnotation(Retention.class);
        assertThat(retention)
                .withFailMessage("TestClass should have a defined Retention")
                .isNotNull();
        assertThat(retention.value())
                .withFailMessage("TestClass should have a runtime retention")
                .isEqualTo(RetentionPolicy.RUNTIME);

        try {
            var name = clazz.getDeclaredMethod("name");

            assertThat(name.getDefaultValue())
                    .withFailMessage("name method should have a default value equals to Test")
                    .isEqualTo("Test");

        } catch (NoSuchMethodException e) {
            fail("TestClass should have a name method");
        }
    }

    @Test
    void checkTestMethod() {
        Class<TestMethod> clazz = TestMethod.class;
        assertThat(clazz.isAnnotation())
                .withFailMessage("TestMethod should be an annotation")
                .isTrue();

        var target = clazz.getAnnotation(Target.class);
        assertThat(target)
                .withFailMessage("TestMethod should have a Target")
                .isNotNull();
        assertThat(target.value())
                .withFailMessage("TestMethod should have a target to method")
                .containsExactly(ElementType.METHOD);

        var retention = clazz.getAnnotation(Retention.class);
        assertThat(retention)
                .withFailMessage("TestMethod should have a defined Retention")
                .isNotNull();
        assertThat(retention.value())
                .withFailMessage("TestMethod should have a runtime retention")
                .isEqualTo(RetentionPolicy.RUNTIME);

        try {
            var name = clazz.getDeclaredMethod("name");

            assertThat(name.getDefaultValue())
                    .withFailMessage("name method should have a default value equals to Test")
                    .isEqualTo("Test");

        } catch (NoSuchMethodException e) {
            fail("TestMethod should have a name method");
        }
    }

}