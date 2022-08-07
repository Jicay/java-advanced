public class ChocolateStep extends RecipeStep {

    @Override
    public String cook(Ingredient ingredient) {
        if (ingredient == Ingredient.CHOCOLATE) {
            return "Chocolate is melted";
        } else {
            if (nextStep != null) {
                return nextStep.cook(ingredient);
            } else {
                return "I don't know what to do";
            }
        }
    }
}
