public class ExerciseRunner {

    public static void main(String[] args)  {
        Raclette r = new BaseRaclette();
        System.out.println(r);
        r = new WithPickles(r);
        System.out.println(r);
        r = new WithColdMeats(r);
        System.out.println(r);
    }
}