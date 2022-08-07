public class Vegetable implements Item {

    private int pricePerKilo;
    private int weight;
    private String name;

    public Vegetable(int pricePerKilo, int weight, String name) {
        this.pricePerKilo = pricePerKilo;
        this.weight = weight;
        this.name = name;
    }

    @Override
    public ShoppingCartItem accept(ShoppingCartVisitor visitor) {
        return visitor.visitVegetable(this);
    }

    public String getName() {
        return name;
    }

    public int getPricePerKilo() {
        return pricePerKilo;
    }

    public int getWeight() {
        return weight;
    }
}
