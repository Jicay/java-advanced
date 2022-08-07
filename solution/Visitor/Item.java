public interface Item {

    ShoppingCartItem accept(ShoppingCartVisitor visitor);

}
