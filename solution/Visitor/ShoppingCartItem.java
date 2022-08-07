public class ShoppingCartItem {
    private String name;
    private int price;

    public ShoppingCartItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Item " + name + " for " + price + " €";
    }
}
