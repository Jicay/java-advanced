# Instructions

Create a file `Operation.java` and create a functional interface with a function `operation` which has 2 integer parameters and return an integer.

Create a file `FunctionalInterfaceRunner.java`.

Create a function `createAddition` which returns a function which make the addition of the sum of its two parameters.    
Create e function `createFactorialAndSubstract` which returns a function which compute the factorial of the first number and substracts the second number the the factorial value.


# Expected Functions
```java
public class Operation {
    public static Operation createAddition() {
        // your code here
    }

    public static Operation createFactorialAndSubstract() {
        // your code here
    }
}
```

# Usage

Here is a possible ExerciseRunner.java to test your function
:

```java
public class ExerciseRunner {
    public static void main(String[] args) throws IOException {
        System.out.println(FunctionalInterfaceRunner.createAddition().operation(4, 5));
        System.out.println(FunctionalInterfaceRunner.createFactorielAndSubstract().operation(5, 10));
    }
}
```
          
and its output :
```shell
$ javac *.java -d build
$ java -cp build ExerciseRunner 
9
110
$ 
```

# Notions
[Functional Interface](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/FunctionalInterface.html)  
