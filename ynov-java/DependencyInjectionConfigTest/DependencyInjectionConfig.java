import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DependencyInjectionInjectionTest {
    @Test
    void testEngine() throws Exception {
        DependencyEngine server = new DependencyEngine();
        server.init(new Class[]{PrintEndpoint.class, AnotherEndpoint.class, AnswerEndpoint.class});

        var endpoint1 = server.getInstance("endpointTest");
        assertThat(endpoint1)
                .withFailMessage("Component named endpointTest should not be null")
                .isNotNull();
        assertThat(endpoint1)
                .withFailMessage("Component named endpointTest should be of time PrintEndpoint")
                .isInstanceOf(PrintEndpoint.class);

        var res = ((PrintEndpoint) endpoint1).first();

        assertThat(res)
                .withFailMessage("Result of method for component endpointTest should be \"Welcome to Ynov 42\", but was %s", res)
                .isEqualTo("Welcome to Ynov 42");

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

        var endpoint3 = server.getInstance("anotherEndpoint");
        assertThat(endpoint3)
                .withFailMessage("Component named anotherEndpoint should not be null")
                .isNotNull();
        assertThat(endpoint3)
                .withFailMessage("Component named anotherEndpoint should be of time AnotherEndpoint")
                .isInstanceOf(AnotherEndpoint.class);

        var res3 = ((AnotherEndpoint) endpoint3).concat();

        assertThat(res3)
                .withFailMessage("Result of method for component anotherEndpoint should be \"42 you succeed\", but was %s", res3)
                .isEqualTo("42 you succeed");

    }
}

@Component(name = "endpointTest")
class PrintEndpoint {
    @Inject
    private AnswerEndpoint answerEndpoint;

    public String first() {
        return "Welcome to Ynov " + answerEndpoint.get();
    }

    public String success() {
        return "you succeed";
    }
}

@Component(name = "anotherEndpoint")
class AnotherEndpoint {
    @Inject
    private AnswerEndpoint answerEndpoint;

    @Inject(name = "endpointTest")
    private PrintEndpoint printEndpoint;

    public String concat() {
        return answerEndpoint.get() + " " + printEndpoint.success();
    }
}

@Component
class AnswerEndpoint {
    public int get() {
        return 42;
    }
}