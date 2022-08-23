import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.junit.jupiter.api.Test;

class ArchiServiceTest {

    @Test
    void testClassDao() {
        try {
            Method saveInt = NumberDao.class.getDeclaredMethod("saveInt", int.class);
            assertThat(Modifier.isPublic(saveInt.getModifiers()))
                    .withFailMessage("Method saveInt should be public")
                    .isTrue();
        } catch (NoSuchMethodException e) {
            fail("Class NumberDao should have a saveInt value");
        }

        try {
            Constructor constructor = NumberDao.class.getDeclaredConstructor(String.class);
            assertThat(Modifier.isPublic(constructor.getModifiers()))
                    .withFailMessage("Constructor should be public")
                    .isTrue();
        } catch (NoSuchMethodException e) {
            fail("Class NumberDao should have a constructor with a String");
        }
    }

    @Test
    void testClassService() {
        try {
            Method add = ComputeService.class.getDeclaredMethod("add", IntOperation.class);
            assertThat(Modifier.isPublic(add.getModifiers()))
                    .withFailMessage("Method add should be public")
                    .isTrue();
        } catch (NoSuchMethodException e) {
            fail("Class ComputeService should have a add value");
        }

        try {
            Constructor constructor = ComputeService.class.getDeclaredConstructor(NumberDao.class);
            assertThat(Modifier.isPublic(constructor.getModifiers()))
                    .withFailMessage("Constructor should be public")
                    .isTrue();
        } catch (NoSuchMethodException e) {
            fail("Class ComputeService should have a constructor with a NumberDao");
        }
    }

    @Test
    void testClassIntOperation() {
        try {
            assertThat(IntOperation.class.isRecord())
                    .withFailMessage("IntOperation should be record")
                    .isTrue();

            Constructor constructor = IntOperation.class.getDeclaredConstructor(int.class, int.class);
            assertThat(Modifier.isPublic(constructor.getModifiers()))
                    .withFailMessage("Constructor should be public")
                    .isTrue();
        } catch (NoSuchMethodException e) {
            fail("Class IntOperation should have a constructor with two int value");
        }
    }

    @Test
    void add() throws IOException {
        var numberDao = new NumberDao("test.db");
        var service = new ComputeService(numberDao);
        service.add(new IntOperation(43, 54));

        BufferedReader reader = new BufferedReader(new FileReader("test.db"));

        String line;

        if ((line = reader.readLine()) != null) {
            int value = Integer.parseInt(line);
            // Print the Integer
            assertThat(value)
                    .withFailMessage("The stored value should be 97 but was %d", value)
                    .isEqualTo(97);
        }
    }

    @Test
    void add_multipleCall() throws IOException {
        var numberDao = new NumberDao("test.db");
        var service = new ComputeService(numberDao);
        service.add(new IntOperation(43, 54));
        service.add(new IntOperation(1, 549));

        BufferedReader reader = new BufferedReader(new FileReader("test.db"));

        String line;

        if ((line = reader.readLine()) != null) {
            int value = Integer.parseInt(line);
            // Print the Integer
            assertThat(value)
                    .withFailMessage("The stored value should be 550 but was %d", value)
                    .isEqualTo(550);
        }
    }
}