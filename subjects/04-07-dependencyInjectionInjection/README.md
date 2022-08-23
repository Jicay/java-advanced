# Instructions

Let's continue the implementation of the framework.

Let's create a new annotation `Inject`. It is an annotation for field and has a method named `name` with return a String. It has a default value to empty String.

In the `DependencyEngine` class, you will still instantiate each class in parameter.  
Now a class may have a field annotated with `@Inject`. For each annotated field, you will affect to this field the dependency matching the name.  
If the `name` of the `@Inject` annotation is empty, then the computed name is the name of the type of the field.

For example, if a class is annotated with `@Component(name="value")`, then the instantiated object will be affected to each field annotated with `@Inject(name="value")`.

To simplify the implementation, there will not be any circular dependency between Object. There will neither be unresolved dependency.

# Usage

Here is a possible ExerciseRunner.java to test your function :

```java
public class ExerciseRunner {
  public static void main(String[] args) {
    DependencyEngine engine = new DependencyEngine();
    engine.init(new Class[]{AnotherComponent.class, TestComponent.class, ComponentSecond.class});

    System.out.println(((TestComponent) engine.getInstance("componentTest")).getString());
    System.out.println(((ComponentSecond) engine.getInstance("ComponentSecond")).getValue());
    System.out.println(((AnotherComponent) engine.getInstance("AnotherComponent")).concat());
  }
}

@Component
class AnotherComponent {
  @Inject(name = "componentTest")
  private TestComponent testComponent;

  @Inject
  private ComponentSecond componentSecond;

  public String concat() {
    return testComponent.getString() + componentSecond.getValue();
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
  @Inject(name = "componentTest")
  private TestComponent testComponent;

  public int getValue() {
    return 16 + testComponent.getString().length();
  }
}
```
          
and its output :
```shell
$ javac *.java -d build
$ java -cp build ExerciseRunner 
Test String
27
Test String27
```
