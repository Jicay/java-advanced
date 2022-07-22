# Instructions

Let's continue the framework development.

Create a new annotation `QueryParam` in a new file `QueryParam.java`, applicable on parameter, with a method named `name` with no default value.

This annotation will allow us to manage query parameters in `GET` endpoints. 
To simplify the implementation, this annotation will only be applicated on Integer or String parameters.

When the server will receive a GET method with query parameters you will need to map the parameters of the method to the query parameters, using the `name` value of `@QueryParam`.
If the query param is not present is the request, you will put a `null` value instead. 
If the query param can't be parsed as an Integer, the server will send a response with a 400 HTTP code. 

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

    @Get(path = "/bonjour")
    public String bonjour() {
        return "Bonjour";
    }
}
```
          
and its output :
```shell
$ javac *.java -d build
$ java -cp build ExerciseRunner 

$ curl -X GET "http://localhost:8080/api/message/hello?name=Ynov&age=23"   
Hello Ynov, you are 23%
$ curl -X GET "http://localhost:8080/api/message/hello?name=Ynov&age=Bordeaux" -I
HTTP/1.1 400 Bad Request
Date: Sat, 16 Jul 2022 22:46:48 GMT
Transfer-encoding: chunked
$
```

# Notions
[HttpServer](https://docs.oracle.com/en/java/javase/17/docs/api/jdk.httpserver/com/sun/net/httpserver/HttpServer.html)  
[HTTP Code](https://developer.mozilla.org/fr/docs/Web/HTTP/Status)  
[HTTP Code](https://developer.mozilla.org/fr/docs/Web/HTTP/Methods)  
[curl](https://curl.se/)  
[Annotation](https://docs.oracle.com/javase/8/docs/technotes/guides/language/annotations.html)  

