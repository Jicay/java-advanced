public abstract class RacletteDecorator implements Raclette {
    private final Raclette decoratedRaclette;

    public RacletteDecorator(Raclette raclette) {
        this.decoratedRaclette = raclette;
    }

    @Override
    public int getCalories() {
        return decoratedRaclette.getCalories();
    }

    @Override
    public String getIngredients() {
        return decoratedRaclette.getIngredients();
    }

    public String toString() {
        return getIngredients() + " pour " + getCalories() + " calories";
    }
}
