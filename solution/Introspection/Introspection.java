import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Introspection {
    public static String getClassName(Class<?> clazz) {
        return clazz.getSimpleName();
    }

    public static List<String> getPublicMethodName(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(m -> Modifier.isPublic(m.getModifiers()))
                .map(Method::getName)
                .toList();
    }

    public static List<String> getAllParents(Class<?> clazz) {
        List<String> parents = new ArrayList<>();
        Class<?> c = clazz;
        while (c != null) {
            parents.add(c.getSimpleName());
            c = c.getSuperclass();
        }
        return parents;
    }
}
