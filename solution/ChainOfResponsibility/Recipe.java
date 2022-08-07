import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private List<String> steps;
    private RecipeStep defaultStep;

    public Recipe(RecipeStep recipeStep) {
        defaultStep = recipeStep;
        steps = new ArrayList<>();
    }

    public void addIngredient(Ingredient ingredient) {
        steps.add(defaultStep.cook(ingredient));
    }

    public List<String> getSteps() {
        return steps;
    }
}
