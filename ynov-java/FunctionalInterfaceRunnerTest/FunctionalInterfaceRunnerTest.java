import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.fail;

import org.junit.jupiter.api.Test;

class FunctionalInterfaceRunnerTest {

    @Test
    void checkOperationType() {
        assertThat(Operation.class.isInterface())
                .withFailMessage("Operation should be an interface but was not")
                .isTrue();
        assertThat(Operation.class.getDeclaredAnnotations())
                .withFailMessage("Operation should have the correct annotation")
                .extractingResultOf("annotationType")
                        .containsExactlyInAnyOrder(FunctionalInterface.class);
        try {
            assertThat(Operation.class.getDeclaredMethod("operation", Integer.class, Integer.class))
                    .withFailMessage("Operation should have the correct annotation")
                    .isNotNull();
        } catch (NoSuchMethodException e) {
            fail("Operation should have an operation method with two integers");
        }
    }

    @Test
    void createAddition() {
        int res1 = FunctionalInterfaceRunner.createAddition().operation(45, 23);
        assertThat(res1)
                .withFailMessage("The computed value should be 68, but was %d", res1)
                .isEqualTo(68);
        int res2 = FunctionalInterfaceRunner.createAddition().operation(0, -3);
        assertThat(res2)
                .withFailMessage("The computed value should be -3, but was %d", res2)
                .isEqualTo(-3);
    }

    @Test
    void createFactorialAndSubstract() {
        int res1 = FunctionalInterfaceRunner.createFactorialAndSubstract().operation(8, 17);
        assertThat(res1)
                .withFailMessage("The computed value should be 40303, but was %d", res1)
                .isEqualTo(40303);
        int res2 = FunctionalInterfaceRunner.createAddition().operation(1, -3);
        assertThat(res2)
                .withFailMessage("The computed value should be -2, but was %d", res2)
                .isEqualTo(-2);
    }
}