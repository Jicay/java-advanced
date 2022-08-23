import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import org.junit.jupiter.api.Test;

class ArchiAllFrameworksTest {

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
            Method getInt = NumberDao.class.getDeclaredMethod("getInt");
            assertThat(Modifier.isPublic(getInt.getModifiers()))
                    .withFailMessage("Method getInt should be public")
                    .isTrue();
        } catch (NoSuchMethodException e) {
            fail("Class NumberDao should have a getInt value");
        }

        try {
            Constructor constructor = NumberDao.class.getDeclaredConstructor();
            assertThat(Modifier.isPublic(constructor.getModifiers()))
                    .withFailMessage("Constructor should be public")
                    .isTrue();
        } catch (NoSuchMethodException e) {
            fail("Class NumberDao should have a default constructor");
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
            Method get = ComputeService.class.getDeclaredMethod("get");
            assertThat(Modifier.isPublic(get.getModifiers()))
                    .withFailMessage("Method get should be public")
                    .isTrue();
        } catch (NoSuchMethodException e) {
            fail("Class ComputeService should have a get value");
        }

        try {
            Constructor constructor = ComputeService.class.getDeclaredConstructor();
            assertThat(Modifier.isPublic(constructor.getModifiers()))
                    .withFailMessage("Constructor should be public")
                    .isTrue();
        } catch (NoSuchMethodException e) {
            fail("Class ComputeService should have a default constructor");
        }
    }

    @Test
    void testClassController() {
        try {
            Method add = ComputeController.class.getDeclaredMethod("add", OperationDTO.class);
            assertThat(Modifier.isPublic(add.getModifiers()))
                    .withFailMessage("Method add should be public")
                    .isTrue();
        } catch (NoSuchMethodException e) {
            fail("Class ComputeController should have a add value");
        }

        try {
            Method get = ComputeController.class.getDeclaredMethod("get");
            assertThat(Modifier.isPublic(get.getModifiers()))
                    .withFailMessage("Method get should be public")
                    .isTrue();
        } catch (NoSuchMethodException e) {
            fail("Class ComputeController should have a get value");
        }

        try {
            Constructor constructor = ComputeController.class.getDeclaredConstructor();
            assertThat(Modifier.isPublic(constructor.getModifiers()))
                    .withFailMessage("Constructor should be public")
                    .isTrue();
        } catch (NoSuchMethodException e) {
            fail("Class ComputeController should have a default constructor");
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
    void addAndGet() throws IOException, URISyntaxException, InterruptedException {
        FileWriter fileWriter = new FileWriter("conf.yaml");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.print("""
                numberDb=test.db
                """);

        printWriter.close();

        DependencyEngine engine = new DependencyEngine();
        engine.loadConfigFile("conf.yaml");
        engine.init(new Class[]{ComputeController.class, ComputeService.class, NumberDao.class});

        new RestEngine().startServer(18080, new Object[]{engine.getInstance("computeController")});

        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:18080/api/compute/add"))
                .POST(HttpRequest.BodyPublishers.ofString("x=43;y=438"))
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        assertThat(response.statusCode())
                .withFailMessage("Status code of POST /api/compute/add should be 200, but was %d", response.statusCode())
                .isEqualTo(200);

        BufferedReader reader = new BufferedReader(new FileReader("test.db"));

        String line;

        if ((line = reader.readLine()) != null) {
            int value = Integer.parseInt(line);
            // Print the Integer
            assertThat(value)
                    .withFailMessage("The stored value should be 481 but was %d", value)
                    .isEqualTo(481);
        }

        HttpRequest requestGet = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:18080/api/compute/value"))
                .GET()
                .build();

        HttpResponse<String> responseGet = client.send(requestGet, BodyHandlers.ofString());

        assertThat(responseGet.statusCode())
                .withFailMessage("Status code of GET /api/compute/value should be 200, but was %d", responseGet.statusCode())
                .isEqualTo(200);

        assertThat(responseGet.body())
                .withFailMessage("Response of GET /api/compute/value should be 481, but was %s", responseGet.body())
                .isEqualTo("481");
    }
}