public class EggStep extends RecipeStep {

    @Override
    public String cook(Ingredient ingredient) {
        if (ingredient == Ingredient.EGG) {
            return "Eggs are ready";
        } else {
            if (nextStep != null) {
                return nextStep.cook(ingredient);
            } else {
                return "I don't know what to do";
            }
        }
    }
}
