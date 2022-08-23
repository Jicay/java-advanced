# Instructions

In this exercise, we will create a little framework for dependency injection.

Let's create a new annotation `Component`. It is an annotation for class and has a method named `name` with return a String. It has a default value to empty String.

Create another class `DependencyEngine`.
* It contains one private field : `context`, a map of String and Object.
* A method `init` which takes a array of `Class`. For each item of the array, if the class has a `Component` annotation :
  * it will instantiate the class (using the default constructor) 
  * adds the new instance in the context map. The key of the object will be the `name` of the annotation or the name of class if the name is an empty String.

# Usage

Here is a possible ExerciseRunner.java to test your function :

```java
public class ExerciseRunner {
    public static void main(String[] args) {
        DependencyEngine engine = new DependencyEngine();
        engine.init(new Class[]{TestComponent.class, ComponentSecond.class});

        System.out.println(((TestComponent) engine.getInstance("componentTest")).getString());
        System.out.println(((ComponentSecond) engine.getInstance("ComponentSecond")).getValue());
    }
}

@Component(name = "componentTest")
class TestComponent {
    public String getString() {
        return "Test String";
    }
}

@Component
class ComponentSecond {
    public int getValue() {
        return 16;
    }
}
```
          
and its output :
```shell
$ javac *.java -d build
$ java -cp build ExerciseRunner 
Test String
16
```
