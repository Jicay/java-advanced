public class WithPickles extends RacletteDecorator {

    public WithPickles(Raclette raclette) {
        super(raclette);
    }

    @Override
    public int getCalories() {
        return super.getCalories() + 50;
    }

    @Override
    public String getIngredients() {
        return super.getIngredients() + ", cornichons";
    }
}
