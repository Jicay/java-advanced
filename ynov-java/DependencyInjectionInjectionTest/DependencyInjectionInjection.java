import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DependencyInjectionTest {
    @Test
    void testGetHello() throws Exception {
        DependencyEngine server = new DependencyEngine();
        server.init(new Class[]{PrintEndpoint.class, AnswerEndpoint.class});

        var endpoint1 = server.getInstance("endpointTest");
        assertThat(endpoint1)
                .withFailMessage("Component named endpointTest should not be null")
                .isNotNull();
        assertThat(endpoint1)
                .withFailMessage("Component named endpointTest should be of time PrintEndpoint")
                .isInstanceOf(PrintEndpoint.class);

        var res = ((PrintEndpoint) endpoint1).first();

        assertThat(res)
                .withFailMessage("Result of method for component endpointTest should be \"Welcome to Ynov\", but was %s", res)
                .isEqualTo("Welcome to Ynov");

        var endpoint2 = server.getInstance("AnswerEndpoint");
        assertThat(endpoint2)
                .withFailMessage("Component named AnswerEndpoint should not be null")
                .isNotNull();
        assertThat(endpoint2)
                .withFailMessage("Component named AnswerEndpoint should be of time AnswerEndpoint")
                .isInstanceOf(AnswerEndpoint.class);

        var res2 = ((AnswerEndpoint) endpoint2).get();

        assertThat(res2)
                .withFailMessage("Result of method for component endpointTest should be 42, but was %d", res2)
                .isEqualTo(42);

    }
}

@Component(name = "endpointTest")
class PrintEndpoint {
    public String first() {
        return "Welcome to Ynov";
    }

    public String success() {
        return "you succeed";
    }
}


@Component
class AnswerEndpoint {
    public int get() {
        return 42;
    }
}