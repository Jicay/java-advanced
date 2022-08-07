import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class ObserverTest {

    @Test
    void checkClass() {
        var recipeStepClass = RecipeStep.class;
        assertThat(recipeStepClass)
                .withFailMessage("NumericBaseObserver should be abstract")
                .isAbstract();
        var eggStepClass = EggStep.class;
        assertThat(eggStepClass.getSuperclass())
                .withFailMessage("EggStep should inherit RecipeStep")
                .isEqualTo(RecipeStep.class);
        var flourStepClass = FlourStep.class;
        assertThat(flourStepClass.getSuperclass())
                .withFailMessage("FlourStep should inherit RecipeStep")
                .isEqualTo(RecipeStep.class);
        var chocolateStepClass = ChocolateStep.class;
        assertThat(chocolateStepClass.getSuperclass())
                .withFailMessage("ChocolateStep should inherit RecipeStep")
                .isEqualTo(RecipeStep.class);
    }

    @Test
    void chainOfResponsibility() {
        ChocolateStep chocolateStep = new ChocolateStep();
        FlourStep flourStep = new FlourStep();
        EggStep eggStep = new EggStep();
        eggStep.setNextStep(flourStep);
        flourStep.setNextStep(chocolateStep);

        Recipe recipe = new Recipe(eggStep);
        recipe.addIngredient(Ingredient.EGG);
        recipe.addIngredient(Ingredient.CHOCOLATE);
        recipe.addIngredient(Ingredient.FLOUR);
        recipe.addIngredient(Ingredient.FLOUR);
        recipe.addIngredient(Ingredient.CHOCOLATE);

        assertThat(recipe.getSteps())
                .withFailMessage("The steps should be [\"Eggs are ready\", \"Chocolate is melted\", \"Flour is mixed\", \"Flour is mixed\", \"Chocolate is melted\"], but was %s", recipe.getSteps())
                .containsExactlyElementsOf(List.of("Eggs are ready", "Chocolate is melted", "Flour is mixed", "Flour is mixed", "Chocolate is melted"));
    }

}