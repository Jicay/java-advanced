public class ExerciseRunner {

    public static void main(String[] args) {
        ChocolateStep chocolateStep = new ChocolateStep();
        FlourStep flourStep = new FlourStep();
        EggStep eggStep = new EggStep();
        eggStep.setNextStep(flourStep);
        flourStep.setNextStep(chocolateStep);

        Recipe chocolateMousse = new Recipe(eggStep);
        chocolateMousse.addIngredient(Ingredient.EGG);
        chocolateMousse.addIngredient(Ingredient.CHOCOLATE);

        System.out.println(chocolateMousse.getSteps());
    }
}