public class FlourStep extends RecipeStep {

    @Override
    public String cook(Ingredient ingredient) {
        if (ingredient == Ingredient.FLOUR) {
            return "Flour is mixed";
        } else {
            if (nextStep != null) {
                return nextStep.cook(ingredient);
            } else {
                return "I don't know what to do";
            }
        }
    }
}
