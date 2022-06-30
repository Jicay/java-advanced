# Instructions

Create a file `FunctionalInterface.java`.

We will write and execute some anonymous functions.
Create a function `execute` which take an anonymous function as parameter and its two parameters. It returns the execution of the function using the two parameters.  
Create e function `buildFibonacciLambda` which return an anonymous which take an integer n as parameter and returns the n-th value of Fibonacci number, as follow :
- The 0th and 1st number is 1
- The nth number is n-1th + n-2th


# Expected Functions
```java
public class RegexReplace {
    public static String execute(BiFunction<Integer, Integer, String> f, int i, int i1) {
        // your code here
    }

    public static Function<Integer, Integer> buildFibonacciLambda() {
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
        System.out.println(FunctionalInterface.execute((x, y) -> String.valueOf(x + y), 2, 3));
        System.out.println(FunctionalInterface.execute((x, y) -> x + " " + (y * 2), 2, 3));
        System.out.println(FunctionalInterface.buildFibonacciLambda().apply(0));
        System.out.println(FunctionalInterface.buildFibonacciLambda().apply(5));
    }
}
```
          
and its output :
```shell
$ javac *.java -d build
$ java -cp build ExerciseRunner 
5
2 6
1
8
$ 
```

# Notions
[FunctionalInterface](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)  
[Fibonacci](https://fr.wikipedia.org/wiki/Suite_de_Fibonacci)
