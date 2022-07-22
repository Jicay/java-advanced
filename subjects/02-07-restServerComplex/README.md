# Instructions

We will use the previous class `RestServer`.  

We will update the `GET` `/api/hello` endpoint. It will manage query parameters. If the request receives the query parameter named `name`, the result will be "Hello world {name} !".  
Let's add a new endpoint : `POST` `/api/add`. The body of the request has the pattern : `x={x};y={y}` where x and y are integers. The endpoint returns the String "The result is {add}" where add is the sum of x and y in the body.

Any other endpoint should return a 404 HTTP code.

# Usage

Here is a possible ExerciseRunner.java to test your function :

```java
import java.io.IOException;

public class ExerciseRunner {

    public static void main(String[] args) throws IOException {
        new RestEngine().startServer(8080, new Class[]{MessageEndpoint.class});
    }
}
```
          
and its output :
```shell
$ javac *.java -d build
$ java -cp build ExerciseRunner 

$ curl -X GET "http://localhost:8080/api/hello?name=Ynov"  
Hello world Ynov !
$ curl -X POST "http://localhost:8080/api/add" -d "x=14;y=35"
The result is 49
$
```

# Notions
[HttpServer](https://docs.oracle.com/en/java/javase/17/docs/api/jdk.httpserver/com/sun/net/httpserver/HttpServer.html)  
[HTTP Code](https://developer.mozilla.org/fr/docs/Web/HTTP/Status)  
[HTTP Code](https://developer.mozilla.org/fr/docs/Web/HTTP/Methods)  
[curl](https://curl.se/)  
