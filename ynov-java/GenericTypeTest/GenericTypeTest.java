import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.fail;

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

class GenericTypeTest {

    @Test
    void checkClass() {
        Class<Triplet> clazz = Triplet.class;
        String genericString = clazz.toGenericString();
        Pattern pattern = Pattern.compile("^public class Triplet<([A-Za-z]+),([A-Za-z]+),([A-Za-z]+)>$");

        assertThat(genericString)
                .withFailMessage("The declaration of class Triplet is not correct, Should be public and have three generic types")
                .matches(pattern);

        var matcher = pattern.matcher(genericString);
        matcher.matches();
        String firstType = matcher.group(1);
        String secondType = matcher.group(2);
        String thirdType = matcher.group(3);

        try {
            var constructor = clazz.getConstructor(Object.class, Object.class, Object.class);
            var genericType = constructor.getGenericParameterTypes();

            assertThat(genericType.length)
                    .withFailMessage("Constructor should have three generic types")
                    .isEqualTo(3);

            assertThat(genericType[0].getTypeName())
                    .withFailMessage("The first element must be of the first generic type")
                    .isEqualTo(firstType);

            assertThat(genericType[1].getTypeName())
                    .withFailMessage("The second element must be of the second generic type")
                    .isEqualTo(secondType);

            assertThat(genericType[2].getTypeName())
                    .withFailMessage("The third element must be of the third generic type")
                    .isEqualTo(thirdType);
        } catch (NoSuchMethodException e) {
            fail("No constructor with 3 parameter");
        }

        try {
            Field field = clazz.getDeclaredField("first");
            assertThat(Modifier.isPrivate(field.getModifiers()))
                    .withFailMessage("First attribute should be private")
                    .isTrue();
            assertThat(field.getGenericType().getTypeName())
                    .withFailMessage("The first attribute should be of type of the first generic type")
                    .isEqualTo(firstType);
        } catch (NoSuchFieldException e) {
            fail("The class should have a first attribute");
        }

        try {
            Field field = clazz.getDeclaredField("second");
            assertThat(Modifier.isPrivate(field.getModifiers()))
                    .withFailMessage("Second attribute should be private")
                    .isTrue();
            assertThat(field.getGenericType().getTypeName())
                    .withFailMessage("The second attribute should be of type of the second generic type")
                    .isEqualTo(secondType);
        } catch (NoSuchFieldException e) {
            fail("The class should have a second attribute");
        }

        try {
            Field field = clazz.getDeclaredField("third");
            assertThat(Modifier.isPrivate(field.getModifiers()))
                    .withFailMessage("Third attribute should be private")
                    .isTrue();
            assertThat(field.getGenericType().getTypeName())
                    .withFailMessage("The third attribute should be of type of the third generic type")
                    .isEqualTo(thirdType);
        } catch (NoSuchFieldException e) {
            fail("The class should have a third attribute");
        }
    }

    @Test
    public void testImplementation() {
        Class<Triplet> clazz = Triplet.class;
        try {
            var constructor = clazz.getConstructor(Object.class, Object.class, Object.class);
            Set<String> set = new HashSet<>(Set.of("Hello world !", "Bonjour tout seul"));
            Triplet<Double, Set<String>, Integer> object = constructor.newInstance(4385.482, set, -73864);
            var getFirst = clazz.getMethod("getFirst");
            var first = getFirst.invoke(object);
            assertThat(first.getClass())
                    .withFailMessage("First element should be a Double")
                    .isEqualTo(Double.class);
            assertThat(first)
                    .withFailMessage("Value of first should be 4385.482, but was %s", first)
                    .isEqualTo(4385.482);
            var getSecond = clazz.getMethod("getSecond");
            var second = getSecond.invoke(object);
            assertThat(second.getClass())
                    .withFailMessage("second element should be a Set")
                    .isEqualTo(HashSet.class);
            assertThat(second)
                    .withFailMessage("Value of second should be [\"Hello world !\", \"Bonjour tout seul\"], but was %s", second)
                    .isEqualTo( Set.of("Hello world !", "Bonjour tout seul"));
            var getThird = clazz.getMethod("getThird");
            var third = getThird.invoke(object);
            assertThat(third.getClass())
                    .withFailMessage("Third element should be a Integer")
                    .isEqualTo(Integer.class);
            assertThat(third)
                    .withFailMessage("Value of first should be -73864, but was %s", third)
                    .isEqualTo( -73864);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            fail("Fail to run Triplet object", e);
        }

    }

}