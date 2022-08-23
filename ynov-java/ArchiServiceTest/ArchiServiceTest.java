import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.junit.jupiter.api.Test;

class ArchiDaoTest {

    @Test
    void testClass() {
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
    void saveInt() throws IOException {
        var numberDao = new NumberDao("test.db");
        numberDao.saveInt(494);

        BufferedReader reader = new BufferedReader(new FileReader("test.db"));

        String line;

        if ((line = reader.readLine()) != null) {
            int value = Integer.parseInt(line);
            // Print the Integer
            assertThat(value)
                    .withFailMessage("The stored value should be 494 but was %d", value)
                    .isEqualTo(494);
        }
    }

    @Test
    void saveInt_multipleCall() throws IOException {
        var numberDao = new NumberDao("test.db");
        numberDao.saveInt(494);
        numberDao.saveInt(2);

        BufferedReader reader = new BufferedReader(new FileReader("test.db"));

        String line;

        if ((line = reader.readLine()) != null) {
            int value = Integer.parseInt(line);
            // Print the Integer
            assertThat(value)
                    .withFailMessage("The stored value should be 2 but was %d", value)
                    .isEqualTo(2);
        }
    }
}