public class ExerciseRunner {

    public static void main(String[] args)  {
        Context context = new Context();
        System.out.println(context.execute(23, 43));

        context.changeStrategy(new MaxStrategy());
        System.out.println(context.execute(23, 43));

        context.changeStrategy(new ConcatStrategy());
        System.out.println(context.execute(23, 43));
    }
}