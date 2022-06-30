import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.fail;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class LambdaTest {

    @Test
    void execute() {
        String res1 = Lambda.execute((x, y) -> String.valueOf(x * y), 3, 8);
        assertThat(res1)
                .withFailMessage("The computed value should be \"24\", but was %s", res1)
                .isEqualTo("24");
        String res2 = Lambda.execute((x, y) -> x + " " + x + " " + y + " " + x + "" + y, 3, 8);
        assertThat(res2)
                .withFailMessage("The computed value should be \"3 3 8 38\", but was %s", res2)
                .isEqualTo("3 3 8 38");
    }

    @Test
    void removeFeminineAndPlural() {
        var fibo = Lambda.buildFibonacciLambda();
        int res1 = fibo.apply(6);
        assertThat(res1)
                .withFailMessage("The 6th value of fibonacci should be 13, but was %s", res1)
                .isEqualTo(13);
        int res2 = fibo.apply(0);
        assertThat(res2)
                .withFailMessage("The 0th value of fibonacci should be 1, but was %s", res2)
                .isEqualTo(1);
        int res3 = fibo.apply(42);
        assertThat(res3)
                .withFailMessage("The 42th value of fibonacci should be 433494437, but was %s", res3)
                .isEqualTo(433494437);
    }
}