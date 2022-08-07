import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<ShoppingCartItem> items;
    private int totalPrice;

    private ShoppingCartVisitor shoppingCartVisitor;

    public ShoppingCart() {
        items = new ArrayList<>();
        totalPrice = 0;
        shoppingCartVisitor = new ShoppingCartVisitorImpl();
    }

    public void addItem(Item item) {
        ShoppingCartItem i = item.accept(shoppingCartVisitor);
        items.add(i);
        totalPrice += i.getPrice();
    }

    @Override
    public String toString() {
        return "You need to pay " + totalPrice + "€ for : " + items.toString();
    }
}
