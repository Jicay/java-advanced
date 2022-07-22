# Instructions

Create a file `RestServer.java`. 

This class has two method : `startServer` which start a HTTP server on the port as parameter and `stopServer` which stops the server.

You need to create an endpoint for `GET` verb on URL `/api/hello` which will return the String "Hello world !" with the HTTP code 200.  
Any other endpoint should return a 404 HTTP code.

To implement the HTTP Server, use this class : `com.sun.net.httpserver.HttpServer`.

# Expected Functions
```java
public class RestServer {
    public void startServer(int port) throws IOException {
        // your code here
    }
    
    public void stopServer() {
        // your code here
    }
}
```

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

$ curl -X GET http://localhost:8080/api/hello
Hello world!
$
```

# Notions
[HttpServer](https://docs.oracle.com/en/java/javase/17/docs/api/jdk.httpserver/com/sun/net/httpserver/HttpServer.html)  
[HTTP Code](https://developer.mozilla.org/fr/docs/Web/HTTP/Status)  
[HTTP Code](https://developer.mozilla.org/fr/docs/Web/HTTP/Methods)  
[curl](https://curl.se/)  
