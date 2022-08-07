import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class VisitorTest {

    @Test
    void checkClass() {
        var shoppingCartVisitorClass = ShoppingCartVisitor.class;
        assertThat(shoppingCartVisitorClass)
                .withFailMessage("ShoppingCartVisitor should be an interface")
                .isInterface();
        var shoppingCartVisitorImplClass = ShoppingCartVisitorImpl.class;
        assertThat(shoppingCartVisitorImplClass.getInterfaces())
                .withFailMessage("ShoppingCartVisitorImpl should implement ShoppingCartVisitor")
                .containsExactly(ShoppingCartVisitor.class);
        var itemClass = Item.class;
        assertThat(itemClass)
                .withFailMessage("Item should be an interface")
                .isInterface();
        var bookClass = Book.class;
        assertThat(bookClass.getInterfaces())
                .withFailMessage("Book should implement Item")
                .containsExactly(Item.class);
        var vegetableClass = Vegetable.class;
        assertThat(vegetableClass.getInterfaces())
                .withFailMessage("Vegetable should implement Item")
                .containsExactly(Item.class);

    }

    @Test
    void visitor() {
        ShoppingCart cart = new ShoppingCart();

        assertThat(cart.toString())
                .withFailMessage("The cart should be \"You need to pay 0€ for : []\", but was %s", cart.toString())
                .isEqualTo("You need to pay 0€ for : []");

        cart.addItem(new Book(56, "First book"));
        assertThat(cart.toString())
                .withFailMessage("The cart should be \"You need to pay 56€ for : [Item First book for 56 €]\", but was %s", cart.toString())
                .isEqualTo("You need to pay 56€ for : [Item First book for 56 €]");

        cart.addItem(new Vegetable(12, 45, "Vegetable 1"));
        assertThat(cart.toString())
                .withFailMessage("The cart should be \"You need to pay 596€ for : [Item First book for 56 €, Item Vegetable 1 for 540 €]\", but was %s", cart.toString())
                .isEqualTo("You need to pay 596€ for : [Item First book for 56 €, Item Vegetable 1 for 540 €]");

        cart.addItem(new Book(1384, "Book the second"));
        assertThat(cart.toString())
                .withFailMessage("The cart should be \"You need to pay 1980€ for : [Item First book for 56 €, Item Vegetable 1 for 540 €, Item Book the second for 1384 €]\", but was %s", cart.toString())
                .isEqualTo("You need to pay 1980€ for : [Item First book for 56 €, Item Vegetable 1 for 540 €, Item Book the second for 1384 €]");

        cart.addItem(new Vegetable(32, 49, "2. Vegetable"));
        assertThat(cart.toString())
                .withFailMessage("The cart should be \"You need to pay 3548€ for : [Item First book for 56 €, Item Vegetable 1 for 540 €, Item Book the second for 1384 €, Item 2. Vegetable for 1568 €]\", but was %s", cart.toString())
                .isEqualTo("You need to pay 3548€ for : [Item First book for 56 €, Item Vegetable 1 for 540 €, Item Book the second for 1384 €, Item 2. Vegetable for 1568 €]");
    }

}