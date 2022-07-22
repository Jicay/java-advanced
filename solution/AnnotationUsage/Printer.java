import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Printer {
    public static List<Object> getAllValuesByLevel(Object o, int level) {
        List<Object> res = new ArrayList<>();
        var annotationClass = o.getClass().getAnnotation(Print.class);

        if (annotationClass != null && annotationClass.value()) {

            for (Field f : o.getClass().getDeclaredFields()) {
                var annotationField = f.getAnnotation(Marked.class);
                if (annotationField != null && annotationField.level() <= level) {
                    f.setAccessible(true);
                    if (f.getType().isPrimitive() || f.getType().equals(String.class)) {
                        try {
                            res.add(f.get(o));
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        try {
                            res.addAll(getAllValuesByLevel(f.get(o), level));
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }

            for (Method m : o.getClass().getDeclaredMethods()) {
                var annotationMethod = m.getAnnotation(Marked.class);
                if (annotationMethod != null && annotationMethod.level() <= level) {
                    m.setAccessible(true);
                    try {
                        res.add(m.invoke(o));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        return res;
    }

}
