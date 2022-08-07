public abstract class RecipeStep {
    protected RecipeStep nextStep;

    public void setNextStep(RecipeStep nextStep) {
        this.nextStep = nextStep;
    }

    public abstract String cook(Ingredient ingredient);
}
