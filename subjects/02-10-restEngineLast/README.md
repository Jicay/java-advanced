# Instructions

Let's end our little framework development.

Create a new annotation `Post` in a new file `Post.java`, applicable on method, with a method named `path` with an empty String as default value.

This annotation will allow us to manage `POST` endpoints, which matches the path of `@Post`.

The request body will take the following format "argument1={value1};argument2={value2}".  
The request body will be mapped to a matching object with field argument1 and argument2. To simplify the implementation, the type of each argument will be an integer or a String.  
If the body failed to be mapped to the matching object (for integer which cannot be mapped for example), the response HTTP code will be 400. 

About the response body, if it is a primitive, a Number or a String, it is return as is.
For a complex class, it will be mapped with the same format of as the request body. You just need to implement the other operation.

All the other behaviours are not impacted.

# Usage

Here is a possible ExerciseRunner.java to test your function :

```java
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
```
          
and its output :
```shell
$ javac *.java -d build
$ java -cp build ExerciseRunner 

$ curl -X GET "http://localhost:8080/api/message/hello?name=Ynov&age=23"   
Hello Ynov, you are 23%
$ curl -X POST "http://localhost:8080/api/message/add" -d "x=12;y=43;delimiter=and"    
sum=55;concat=12and43
$ curl -X POST "http://localhost:8080/api/message/add" -d "x=12;y=Test;delimiter=and" -v  
< HTTP/1.1 400 Bad Request
< Date: Sun, 17 Jul 2022 00:05:46 GMT
< Transfer-encoding: chunked
$
```

# Notions
[HttpServer](https://docs.oracle.com/en/java/javase/17/docs/api/jdk.httpserver/com/sun/net/httpserver/HttpServer.html)  
[HTTP Code](https://developer.mozilla.org/fr/docs/Web/HTTP/Status)  
[HTTP Method](https://developer.mozilla.org/fr/docs/Web/HTTP/Methods)  
[curl](https://curl.se/)  
[Annotation](https://docs.oracle.com/javase/8/docs/technotes/guides/language/annotations.html)  

