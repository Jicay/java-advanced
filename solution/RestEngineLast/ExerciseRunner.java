import java.io.IOException;

public class ExerciseRunner {

    public static void main(String[] args) throws IOException {
        new RestEngine().startServer(8080, new Class[]{MessageEndpoint.class});
    }
}

@Rest(path = "/api/message")
class MessageEndpoint {
    @Get(path = "/hello")
    public String hello(@QueryParam(name = "name") String name, @QueryParam(name = "age") Integer age) {
        String val = "Hello";
        if (name != null) {
            val += " " + name;
        }
        if (age != null) {
            val += ", you are " + age;
        }
        return val;
    }

    @Post(path = "/add")
    public Result add(Operation operation) {
        Result res = new Result();
        res.sum = operation.x + operation.y;
        res.concat = operation.x + operation.delimiter + operation.y;
        return res;
    }
}

class Operation {
    public int x;
    public int y;
    public String delimiter;
}

class Result {
    public int sum;
    public String concat;
}
