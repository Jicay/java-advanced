public class ExerciseRunner {

    public static void main(String[] args)  {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new Book(32, "Game of Thrones"));
        shoppingCart.addItem(new Vegetable(10, 5, "Bean"));
        shoppingCart.addItem(new Vegetable(4, 10, "Tomato"));
        shoppingCart.addItem(new Book(12, "Les fleurs du mal"));

        System.out.println(shoppingCart);
    }
}