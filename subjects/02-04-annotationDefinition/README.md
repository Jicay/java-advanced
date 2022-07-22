# Instructions

We will now create two annotations : 
 * `TestClass` in a new file `TestClass.java`. This annotation should be application only on class.
 * `TestMethod` in a new file `TestMethod.java`. This annotation should be application only on method.

Both annotation must have a method `name`, with a default value as `"Test"`.

# Usage

Here is a possible ExerciseRunner.java to test your function :

```java
public class ExerciseRunner {
    public static void main(String[] args) throws NoSuchMethodException {
        Class<ExerciseForTest> exerciseClass = ExerciseForTest.class;

        System.out.println(exerciseClass.getAnnotation(TestClass.class).name());
        System.out.println(exerciseClass.getDeclaredMethod("methodOne").getAnnotation(TestMethod.class).name());
        System.out.println(exerciseClass.getDeclaredMethod("methodTwo").getAnnotation(TestMethod.class).name());
    }
}

@TestClass(name = "Runner")
class ExerciseForTest {

    @TestMethod
    public void methodOne() {}

    @TestMethod(name = "MethodRun")
    public void methodTwo() {}
}
```
          
and its output :
```shell
$ javac *.java -d build
$ java -cp build ExerciseRunner 
Runner
Test
MethodRun
$ 
```

# Notions
[Annotation](https://docs.oracle.com/javase/8/docs/technotes/guides/language/annotations.html)  
