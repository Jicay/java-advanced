public class Book implements Item {

    private int price;
    private String name;

    public Book(int price, String name) {
        this.price = price;
        this.name = name;
    }

    @Override
    public ShoppingCartItem accept(ShoppingCartVisitor visitor) {
        return visitor.visitBook(this);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
