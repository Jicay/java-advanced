# Instructions

We will create two new annotations : 
 * `Print` in a new file `Print.java`. This annotation should be applicable only on class. It has a method `value` with a default value to true.
 * `Marked` in a new file `Marked.java`. This annotation should be applicable on methods and fields. It has a method `level` with a default value to 2.

Let's create a new class `Printer` in a file `Printer.java`.  
It contains a method `getAllValueByLevel` which takes two attribute : 
* the object to inspect
* the level

This method returns a list built as follows : 
* if the class is marked with the `Print` annotation and value as true
  * for each of its fields, if it's marked with `Marked` annotation and with a level less or equal than the level as parameter
    * if the field is a primitive or String field, add it in the list
    * else execute the same algorithm for this field.
  * for each of its method, if it's marked with `Marked` annotation and with a level less or equal than the level as parameter
    * execute the method and adds the result in the list.

# Expected Functions
```java
public class Printer {
    public static List<Object> getAllValuesByLevel(Object o, int level) {
        // your code here
    }
}
```

# Usage

Here is a possible ExerciseRunner.java to test your function :

```java
public class ExerciseRunner {

  public static void main(String[] args) throws NoSuchMethodException {
    ExerciseForTest exerciseTest = new ExerciseForTest();

    System.out.println(Printer.getAllValuesByLevel(exerciseTest, 1));
  }
}

@Print
class ExerciseForTest {

  @Marked(level = 0)
  private int attributeOne = 13;

  @Marked(level = 0)
  public ClassFirst classOne = new ClassFirst();

  @Marked(level = 1)
  protected ClassSecond classTwo = new ClassSecond();

  @Marked(level = 2)
  public String methodOne() {
    return "Hello";
  }

  public int methodTwo() {
    return 42;
  }

  @Marked(level = 1)
  protected Double methodThree() {
    return 33.33;
  }
}

@Print(true)
class ClassFirst {

  @Marked(level = 0)
  private int containedAttributeOne = 19;

  @Marked(level = 1)
  private String methodContained() {
    return "Content of method";
  }
}

@Print(false)
class ClassSecond {

  @Marked(level = 0)
  private boolean containedAttributeTwo = true;
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
