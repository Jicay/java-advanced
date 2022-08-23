# Instructions

Let's continue our little application and keep your class from previous exercise.  
In this step, we will add a new operation for retrieve the last data stored.

1. In `NumberDao` class, add a method `getInt` which returns the integer from the file of `NumberDao`.
2. In `ComputeService` class, add a method `get` which returns the integer returned by `NumberDao.getInt`.
3. In `ComputeController` class, add a method `get` which returns the integer returned by `ComputerService.get`. The new method is annotated by `@Get` with path "/value"

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
[3-tiers architecture](https://fr.wikipedia.org/wiki/Architecture_trois_tiers)  
[Record](https://docs.oracle.com/en/java/javase/18/language/records.html)  

