# Instructions

Now, we will modify our little application by implementing a good practice in this type of architecture : the design pattern Inversion of Control.

To do that, create two new interface : 
* `ComputeServiceAPI` which is implemented by `ComputeService`. Put all the methods of `ComputeService`. Update the type of the field `computeService` and of the constructor parameter of the `ComputeController`.
* `NumberDaoAPI` which is implemented by `NumberDao`. Put all the methods of `NumberDao`. Update the type of the field `numberDao` and of the constructor parameter of of `ComputeService`.

# Usage

Here is a possible ExerciseRunner.java to test your function :

```java
import java.io.IOException;

public class ExerciseRunner {
    public static void main(String[] args) throws IOException {
        NumberDao numberDao = new NumberDao("number.db");
        ComputeService computeService = new ComputeService(numberDao);
        ComputeController computeController = new ComputeController(computeService);

        new RestEngine().startServer(8080, new Object[]{computeController});
    }
}
```
          
and its output :
```shell
$ javac *.java -d build
$ java -cp build ExerciseRunner 

$ curl -X POST "http://localhost:8080/api/compute/add" -d "x=12;y=46"  
OK
$ curl -X GET "http://localhost:8080/api/compute/value"  
58
$ cat number.db
58
```

# Notions
[Inversion of control](https://fr.wikipedia.org/wiki/Inversion_de_contr%C3%B4le)  

