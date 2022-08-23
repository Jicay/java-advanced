import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class DependencyEngine {

    private Map<String, Object> context = new HashMap<>();

    private Pattern pattern = Pattern.compile("^\\d+$");

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

    public void loadConfigFile(String filePath) {
        File configFile = new File(filePath);
        if (configFile.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(configFile));

                String line;

                while ((line = reader.readLine()) != null) {
                    String[] l = line.split("=");
                    String name = l[0];

                    if (pattern.matcher(l[1]).matches()) {
                        context.put(name, Integer.parseInt(l[1]));
                    } else {
                        context.put(name, l[1]);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
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
