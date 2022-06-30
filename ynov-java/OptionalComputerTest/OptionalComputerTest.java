import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.fail;

import java.util.Optional;
import org.junit.jupiter.api.Test;

class OptionalComputerTest {

    @Test
    void retrieveOrGetDefault() {
        Integer res1 = OptionalComputer.retrieveOrGetDefault(Optional.of(89));
        assertThat(res1)
                .withFailMessage("The result should be 89, but was %d", res1)
                .isEqualTo(89);
        Integer res2 = OptionalComputer.retrieveOrGetDefault(Optional.empty());
        assertThat(res2)
                .withFailMessage("The result should be 42, but was %d", res2)
                .isEqualTo(42);
    }

    @Test
    void retrieveOrError() {
        Integer res1 = OptionalComputer.retrieveOrError(Optional.of(89));
        assertThat(res1)
                .withFailMessage("The result should be 89, but was %d", res1)
                .isEqualTo(89);
        try {
            OptionalComputer.retrieveOrError(Optional.empty());
            fail("An exception should have been thrown");
        } catch (Exception e) {
            assertThat(e)
                    .withFailMessage("The exception should be an IllegalArgumentException")
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    void addIntegerIfGreaterThan5() {
        Optional<Integer> res1 = OptionalComputer.addIntegerIfGreaterThan5(45, 98);
        assertThat(res1.isPresent())
                .withFailMessage("The result should have a value")
                .isTrue();
        assertThat(res1.get())
                .withFailMessage("The result should be 143, but was %d", res1.get())
                .isEqualTo(143);
        Optional<Integer> res2 = OptionalComputer.addIntegerIfGreaterThan5(-3, 2);
        assertThat(res2.isEmpty())
                .withFailMessage("The result should not have a value")
                .isTrue();
    }
}