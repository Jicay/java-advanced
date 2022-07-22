import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import org.junit.jupiter.api.Test;

class GenericMethodTest {

    @Test
    void checkMethod() {
        try {
            Method method = GenericMethod.class.getMethod("concat", List.class);
            assertThat(method.getGenericParameterTypes().length)
                    .withFailMessage("The concat method should have 1 parameter type")
                    .isEqualTo(1);
            assertThat(method.getGenericParameterTypes()[0].getTypeName())
                    .withFailMessage("The parameter type of concat method should be used as parameter in a List")
                    .matches("^java.util.List<[A-Za-z]+>$");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            fail("GenericMethod should have concat method");
        }
        try {
            Method method = GenericMethod.class.getMethod("max", List.class);
            assertThat(method.getGenericParameterTypes().length)
                    .withFailMessage("The max method should have 1 parameter type")
                    .isEqualTo(1);

            assertThat(method.getGenericParameterTypes()[0].getTypeName())
                    .withFailMessage("The parameter type of max method should be used as parameter in a List")
                    .matches("^java.util.List<[A-Za-z]+>$");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            fail("GenericMethod should have max method");
        }
    }

    @Test
    void concat() {
        String res1 = GenericMethod.concat(List.of("Il", "fait", "beau"));
        assertThat(res1)
                .withFailMessage("The result should be \"Il,fait,beau\", but was %s", res1)
                .isEqualTo("Il,fait,beau");
        String res2 = GenericMethod.concat(List.of(List.of(2, 4), List.of(23, 345)));
        assertThat(res2)
                .withFailMessage("The result should be \"[2, 4],[23, 345]\", but was %s", res2)
                .isEqualTo("[2, 4],[23, 345]");
    }

    @Test
    void max() {
        Double res1 = GenericMethod.max(List.of(1, 32, 435, 6950, 13));
        assertThat(res1)
                .withFailMessage("The result should be 6950.0, but was %f", res1)
                .isEqualTo(6950.0);
        Double res2 = GenericMethod.max(List.of(43.0, 412.23, 4593.2145, 1.21, 43.2345));
        assertThat(res2)
                .withFailMessage("The result should be 4593.2145, but was %f", res2)
                .isEqualTo(4593.2145);
    }


}