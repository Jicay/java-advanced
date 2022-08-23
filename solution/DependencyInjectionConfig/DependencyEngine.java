import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DependencyEngine {

    private Map<String, Object> context = new HashMap<>();

    public void init(Class<?>[] components) {
        Map<String, List<Object>> toResolve = new HashMap<>();
        for (Class<?> component : components) {
            var annotationClass = component.getAnnotation(Component.class);
            if (annotationClass != null) {
                String name = annotationClass.name();
                if (name.isEmpty()) {
                    name = component.getSimpleName();
                }
                Object built;
                try {
                    built = component.getDeclaredConstructor().newInstance();
                    context.put(name, built);
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    System.out.println(name + " should have a default constructor");
                    throw new RuntimeException(e);
                }

                for (Field f : component.getDeclaredFields()) {
                    var annotationField = f.getAnnotation(Inject.class);
                    if (annotationField != null) {
                        String dependencyName = annotationField.name();
                        if (dependencyName.isEmpty()) {
                            dependencyName = f.getType().getSimpleName();
                        }
                        if (context.containsKey(dependencyName)) {
                            try {
                                f.setAccessible(true);
                                f.set(built, context.get(dependencyName));
                            } catch (IllegalAccessException e) {
                                System.out.println("Error while setting " + dependencyName + " in class " + name);
                                throw new RuntimeException(e);
                            }
                        } else {
                            List<Object> dependencies = toResolve.get(dependencyName);
                            if (dependencies == null) {
                                dependencies = new ArrayList<>();
                            }
                            dependencies.add(built);
                            toResolve.put(dependencyName, dependencies);
                        }
                    }
                }

                if (toResolve.containsKey(name)) {
                    List<Object> dependencies = toResolve.get(name);

                    for (Object d : dependencies) {
                        Field f = findField(d.getClass(), name);
                        if (f != null) {
                            try {
                                f.setAccessible(true);
                                f.set(d, built);
                            } catch (IllegalAccessException e) {
                                System.out.println("Error while setting " + name + " in class " + d.getClass().getSimpleName());
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    toResolve.remove(name);
                }
            }
        }
    }

    private Field findField(Class<?> clazz, String name) {
        for (Field f : clazz.getDeclaredFields()) {
            var annotationField = f.getAnnotation(Inject.class);
            if (annotationField != null) {
                String dependencyName = annotationField.name();
                if (dependencyName.isEmpty()) {
                    dependencyName = f.getType().getSimpleName();
                }
                if (dependencyName.equals(name)) {
                    return f;
                }
            }
        }
        return null;
    }

    public Object getInstance(String componentName) {
        return context.get(componentName);
    }
}
