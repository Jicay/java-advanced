# Instructions

Let's end our little framework.

Our objective here is to load some configuration (as creadientials to a dabase for example) from a configuration file.

Our configuration file will be very simple : 
```
propertyName1=propertyValue1
propertyName2=propertyValue2
...
```

In the `DependencyEngine` class, add a new method `loadConfigFile` which takes the path of the configuration file.  
For a given of the configuration file (`propertyName1=propertyValue1`), you will create a "dependency" in our context with the name as `propertyName1` and the value as `propertyValue1`. 

If a class annotated with `@Dependency` has a field annotated with `@Inject(name="propertyName1")`, then `propertyValue1` will be set in the field.

To simplify, only String or int has allowed value in this case. (You will need to parse the `String` in `int` if is only composed of digit).

# Usage

Here is a possible ExerciseRunner.java to test your function :

```java
public class ExerciseRunner {
    public static void main(String[] args) {
        DependencyEngine engine = new DependencyEngine();
        engine.loadConfigFile("your/path/configuration.txt");
        engine.init(new Class[]{AnotherComponent.class, TestComponent.class, ComponentSecond.class});

        System.out.println(((TestComponent) engine.getInstance("componentTest")).getString());
        System.out.println(((ComponentSecond) engine.getInstance("ComponentSecond")).getValue());
        System.out.println(((ComponentSecond) engine.getInstance("ComponentSecond")).getConfig());
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

    @Inject(name = "otherParam")
    private int value;

    @Inject(name = "param")
    private String otherConfig;

    public int getValue() {
        return 16 + testComponent.getString().length();
    }

    public String getConfig() {
        return otherConfig + " " + value;
    }
}
```

and a possible configuration.txt file : 
```
param=It's a string
otherParam=452
```

and its output :
```shell
$ javac *.java -d build
$ java -cp build ExerciseRunner 
Test String
27
It's a string 452
Test String27
```
