public class ShoppingCartVisitorImpl implements ShoppingCartVisitor {

    @Override
    public ShoppingCartItem visitBook(Book book) {
        return new ShoppingCartItem(book.getName(), book.getPrice());
    }

    @Override
    public ShoppingCartItem visitVegetable(Vegetable vegetable) {
        return new ShoppingCartItem(vegetable.getName(), vegetable.getWeight() * vegetable.getPricePerKilo());
    }
}
