# Instructions

Create a file `Introspection.java`.

Create a function `getClassName` which returns the name of the class as parameter. The name must be without package.  
Create a function `getPublicMethodName` which returns the list of public method names of the given class.  
Create a function `getAllParents` which returns the list of all parent class name (without package) of the given class. The list should contain itself.  

# Expected Functions
```java
public class Introspection {
    public static String getClassName(Class<?> clazz) {
        // your code here
    }

    public static List<String> getPublicMethodName(Class<?> clazz) {
        // your code here
    }

    public static List<String> getAllParents(Class<?> clazz) {
        // your code here
    }
}
```

# Usage

Here is a possible ExerciseRunner.java to test your function :

```java
import java.util.HashSet;

public class ExerciseRunner {
    public static void main(String[] args) {
        System.out.println(Introspection.getClassName(ExerciseForTest.class));
        System.out.println(Introspection.getPublicMethodName(ExerciseForTest.class));
        System.out.println(Introspection.getAllParents(HashSet.class));
    }
}

class ExerciseForTest {

    public void methodOne() {}

    public void methodTwo() {}

    private void privateMethod() {}
}
```
          
and its output :
```shell
$ javac *.java -d build
$ java -cp build ExerciseRunner 
ExerciseForTest
[methodOne, methodTwo]
[HashSet, AbstractSet, AbstractCollection, Object]
$ 
```

# Notions
[Reflection / Introspection](https://docs.oracle.com/javase/tutorial/reflect/)  
