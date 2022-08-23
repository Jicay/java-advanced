# Instructions

Now, let's merge the content of the exercise 5, with the inversion of control and our newly created dependency injection framework.

We will update all the architecture to use the dependency injection framework : 
1. remove all constructor (we only need default constructor)
2. Add `@Inject` on fields and `@Component` on classes. 
   * For the `fileName` field of `NumberDao`, choose the name `numberDb`
   * For the `ComputeController`, use the name `computeController`
   * For all the others, you can choose the name you want. Just be careful to use the same in corresponding `@Inject` and `@Component`

# Usage

Here is a possible ExerciseRunner.java to test your function :

```java
import java.io.IOException;

public class ExerciseRunner {
    public static void main(String[] args) throws IOException {
        DependencyEngine engine = new DependencyEngine();
        engine.loadConfigFile("your/path/configuration.txt");
        engine.init(new Class[]{ComputeController.class, ComputeService.class, NumberDao.class});

        new RestEngine().startServer(8080, new Object[]{engine.getInstance("computeController")});
    }
}
```

and a possible configuration.txt file :
```
numberDb=number.db
```


and its output :
```shell
$ javac *.java -d build
$ java -cp build ExerciseRunner 

$ curl -X POST "http://localhost:8080/api/compute/add" -d "x=782;y=2451"    
OK
$ curl -X GET "http://localhost:8080/api/compute/value" 
3233%
$ cat number.db
3233%
```


