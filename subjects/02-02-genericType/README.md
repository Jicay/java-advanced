# Instructions

Create a generic class TestClass in a file `TestClass.java`.


This class has three private attributes : `first`, `second` and `third`, each of them has a different generic type.
Create public getters and setters for each attribute and a constructor with each attribute.

Override the `toString` method with the following format : "TestClass{first=`first.toString`, second=`second.toString`, third=`third.toString`}"

# Usage

Here is a possible ExerciseRunner.java to test your function :

```java
import java.util.List;

public class ExerciseRunner {

    public static void main(String[] args) {
        TestClass<Integer, String, List<Double>> testClass = new TestClass<>(14, "I'm the second", List.of(234.23, 584984.23));
        System.out.println(testClass);
    }
}
```
          
and its output :
```shell
$ javac *.java -d build
$ java -cp build ExerciseRunner 
TestClass{first=14, second=I'm the second, third=[234.23, 584984.23]}
$ 
```

# Notions
[Generic types](https://docs.oracle.com/javase/tutorial/java/generics/types.html)  
