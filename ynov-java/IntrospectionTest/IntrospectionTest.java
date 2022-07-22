import static org.assertj.core.api.Assertions.assertThat;
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

class IntrospectionTest {
    @Test
    public void getClassName() {
        String res1 = Introspection.getClassName(List.class);
        assertThat(res1)
                .withFailMessage("The result should be \"List\", but was %s", res1)
                .isEqualTo("List");
        String res2 = Introspection.getClassName(String.class);
        assertThat(res2)
                .withFailMessage("The result should be \"String\", but was %s", res2)
                .isEqualTo("String");
    }

    @Test
    public void getPublicMethodName() {
        List<String> res1 = Introspection.getPublicMethodName(TestClass.class);
        assertThat(res1)
                .withFailMessage("The class have two method : firstMethod and lastMethod, but %s was returned", res1)
                .containsExactlyInAnyOrder("firstMethod", "lastMethod");
    }

    @Test
    public void getAllParents() {
        List<String> res1 = Introspection.getAllParents(Object.class);
        assertThat(res1)
                .withFailMessage("The Object class has [Object], but was %s", res1)
                .containsExactly("Object");
        List<String> res2 = Introspection.getAllParents(Integer.class);
        assertThat(res2)
                .withFailMessage("The Integer class has [Integer, Number, Object], but was %s", res2)
                .containsExactly("Integer", "Number", "Object");
    }

}

class TestClass {

    public void firstMethod() {}

    public void lastMethod() {}

    private void privateMethod1() {}
    protected void protectedMethod() {}
}