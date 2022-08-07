public class ExerciseRunner {

    public static void main(String[] args)  {
        System.out.println(new CarDriver().createTransport().getDistance());
        System.out.println(new PlaneDriver().createTransport().getDistance());
    }
}