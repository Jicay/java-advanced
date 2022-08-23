import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class DependencyEngine {

    private Map<String, Object> context = new HashMap<>();

    public void init(Class<?>[] components) {
        for (Class<?> component : components) {
            var annotationClass = component.getAnnotation(Component.class);
            if (annotationClass != null) {
                String name = annotationClass.name();
                if (name.isEmpty()) {
                    name = component.getSimpleName();
                }
                try {
                    context.put(name, component.getDeclaredConstructor().newInstance());
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    System.out.println(name + " should have a default constructor");
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public Object getInstance(String componentName) {
        return context.get(componentName);
    }
}
