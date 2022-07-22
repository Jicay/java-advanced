# Instructions

We will apply everything we learned. Let's create a simple Rest framework.

To start, we will create two annotation : 
* `Rest` in a new file `Rest.java`, only for class and with a method named `path`. It has no default value.
* `Get` in a new file `Get.java`, only for method and with a method named `path`. Its default value is an empty String.

Now let's create a `RestEngine` class in a new file `RestEngine.java`.

It has two method : 
* `startServer` which takes two parameters : the port to start the server and the list of classes annotation with `@Rest`. This method starts the server.
* `stopServer` which will stop the server.

When starting the server, you will scan all classes in parameter and create an endpoint for each method annotated with `@Get`.  
For each method, the created endpoint will listen a `GET` on the concatenation of the paths in `@Rest` and in `@Get`. The response of the endpoint will be the value returned by the method. For the moment, the method should return only primitive type or String.

Any other endpoint should return a 404 HTTP code.

# Expected Functions
```java
public class RestEngine {
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

@Rest(path = "/api/message")
class MessageEndpoint {
    @Get(path = "/hello")
    public String hello() {
        return "Hello";
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

$ curl -X GET "http://localhost:8080/api/message/hello" 
Hello
$ curl -X GET "http://localhost:8080/api/message/bonjour" 
Bonjour
$
```

# Notions
[HttpServer](https://docs.oracle.com/en/java/javase/17/docs/api/jdk.httpserver/com/sun/net/httpserver/HttpServer.html)  
[HTTP Code](https://developer.mozilla.org/fr/docs/Web/HTTP/Status)  
[HTTP Code](https://developer.mozilla.org/fr/docs/Web/HTTP/Methods)  
[curl](https://curl.se/)  
[Annotation](https://docs.oracle.com/javase/8/docs/technotes/guides/language/annotations.html)  

