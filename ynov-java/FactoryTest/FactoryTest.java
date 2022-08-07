import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Modifier;
import org.junit.jupiter.api.Test;

class FactoryTest {

    @Test
    void checkClassDriver() {
        var clazz = Driver.class;
        assertThat(clazz)
                .withFailMessage("Driver should be abstract")
                .isAbstract();
        var classCarDriver = CarDriver.class;
        assertThat(classCarDriver.getSuperclass())
                .withFailMessage("CarDriver should inherit Driver class")
                .isEqualTo(Driver.class);
        var classPlaneDriver = PlaneDriver.class;
        assertThat(classPlaneDriver.getSuperclass())
                .withFailMessage("PlaneDriver should inherit Driver class")
                .isEqualTo(Driver.class);
        try {
            var createTransport = clazz.getDeclaredMethod("createTransport");
            assertThat(Modifier.isAbstract(createTransport.getModifiers()))
                    .withFailMessage("createTransport should be abstract")
                    .isTrue();
        } catch (NoSuchMethodException e) {
            fail("Driver class should have a createTransport method");
        }

        var interfaze = Transport.class;
        assertThat(interfaze)
                .withFailMessage("Transport should be an interface")
                .isInterface();
        var classCar = Car.class;
        assertThat(classCar.getInterfaces())
                .withFailMessage("Car should implement Transport interface")
                .containsExactly(Transport.class);
        var classPlane = Plane.class;
        assertThat(classPlane.getInterfaces())
                .withFailMessage("Plane should implement Transport interface")
                .containsExactly(Transport.class);
        try {
            interfaze.getDeclaredMethod("getDistance");
        } catch (NoSuchMethodException e) {
            fail("Transport interface should have a getDistance method");
        }
    }

    @Test
    void createInstance() {
        Transport car = new CarDriver().createTransport();
        assertThat(car)
                .withFailMessage("The created transport of CarDriver should be a Car class")
                .isInstanceOf(Car.class);
        assertThat(car.getDistance())
                .withFailMessage("The distance of a car should be 600")
                .isEqualTo(600);
        Transport plane = new PlaneDriver().createTransport();
        assertThat(plane)
                .withFailMessage("The created transport of PlaneDriver should be a Plane class")
                .isInstanceOf(Plane.class);
        assertThat(plane.getDistance())
                .withFailMessage("The distance of a plane should be 10000")
                .isEqualTo(10000);
    }

}