public interface ShoppingCartVisitor {
    ShoppingCartItem visitBook(Book book);
    ShoppingCartItem visitVegetable(Vegetable vegetable);
}
