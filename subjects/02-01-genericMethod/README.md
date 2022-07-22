# Instructions

Create a file `GenericMethod.java`.

We will create two generic method.
Create a generic function `concat`. It takes a list of any type (the generic type) and returns a string which is the concatenation of the elements, with a ',' as delimiter.
Create a generic function `max`. It takes a list of any type which inherits of Number (the generic type) and returns a double corresponding to the max of the elements in parameter.

# Expected Functions
```java
public class GenericMethod {
    // No method definition, you need to find how to write generic methods :)
    // Create both methods as static
}
```

# Usage

Here is a possible ExerciseRunner.java to test your function :

```java
import java.util.stream.Stream;

public class ExerciseRunner {
    public static void main(String[] args) {
        System.out.println(GenericMethod.concat(List.of(3, 5, 7, 10)));
        System.out.println(GenericMethod.concat(List.of("Hello", "World")));
        System.out.println(GenericMethod.max(List.of(3, 81, 4394, 3193)));
        System.out.println(GenericMethod.max(List.of(-1293.32, 383.128, -2183.32)));
    }
}
```
          
and its output :
```shell
$ javac *.java -d build
$ java -cp build ExerciseRunner 
3,5,7,10
Hello,World
4394.0
383.128
$ 
```

# Notions
[Generic methods](https://docs.oracle.com/javase/tutorial/java/generics/methods.html)  
