# Instructions

Create a file `OptionalComputer.java`.

Create a function `retrieveOrGetDefault` which returns the value inside the optional if any, or returns 42.  
Create e function `retrieveOrError` which returns the value inside the optional or throw an `IllegalArgumentException`.  
Create e function `addIntegerIfGreaterThan5` which returns, wrapped inside an optional, the sum of the two integers or nothing if the sum is less than 5


# Expected Functions
```java
public class RegexReplace {
    public static Integer retrieveOrGetDefault(Optional<Integer> value) {
        // your code here
    }
    
    public static Integer retrieveOrError(Optional<Integer> value) {
        // your code here
    }

    public static Optional<Integer> addIntegerIfGreaterThan5(int i, int j) {
        // your code here
    }
}
```

# Usage

Here is a possible ExerciseRunner.java to test your function :

```java
import java.io.IOException;
import java.util.Optional;

public class ExerciseRunner {
    public static void main(String[] args) throws IOException {
        System.out.println(OptionalComputer.retrieveOrGetDefault(Optional.empty()));
        System.out.println(OptionalComputer.retrieveOrGetDefault(Optional.of(12)));
        try {
            System.out.println(OptionalComputer.retrieveOrError(Optional.empty()));
        } catch (Exception e){
            System.out.println("There is an exception");
        }
        System.out.println(OptionalComputer.retrieveOrError(Optional.of(12)));
        System.out.println(OptionalComputer.addIntegerIfGreaterThan5(1, 2));
        System.out.println(OptionalComputer.addIntegerIfGreaterThan5(18, 2));
    }
}
```
          
and its output :
```shell
$ javac *.java -d build
$ java -cp build ExerciseRunner 
42
12
There is an exception
12
Optional.empty
Optional[20]
$ 
```

# Notions
[Optional](https://docs.oracle.com/en/java/javase/17/docs/api//java.base/java/util/Optional.html)  
