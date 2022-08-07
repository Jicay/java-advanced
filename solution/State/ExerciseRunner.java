public class ExerciseRunner {

    public static void main(String[] args)  {
        Character boromir = new Character("Boromir");
        System.out.println(boromir.getState().onAttack("Uruk-hai"));
        System.out.println(boromir.getState().onPoison());
        System.out.println(boromir.getState().onAttack("Uruk-hai"));
        System.out.println(boromir.getState().onDead());
        System.out.println(boromir.getState().onAttack("Uruk-hai"));
    }
}