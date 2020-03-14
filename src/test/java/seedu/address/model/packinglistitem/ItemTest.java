package seedu.address.model.packinglistitem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class ItemTest {

    private final Item item = new Item(new Name("Shirt") , new Quantity(10) , false);

    @Test
    public void constructorInvalidNameThrowsIllegalArgumentException() {
        String invalidName = " ";
        Integer invalidQuantity = 0;
        boolean isChecked = false;
        assertThrows(IllegalArgumentException.class, () -> new Item(new Name(invalidName),
                new Quantity(invalidQuantity), isChecked));
    }

    @Test
    public void getItemName() {

        // Correct case
        assertEquals(new Name("Shirt"), new Item(new Name("Shirt"),
                new Quantity(10), false).getItemName());

        //Different case
        assertNotEquals(new Name("Shirt"), new Item(new Name("Pants"),
                new Quantity(10), false).getItemName());

    }

    @Test
    public void getQuantity() {

        // Correct case
        assertEquals(new Quantity(10), new Item(new Name("Shirt"),
                new Quantity(10), false).getQuantity());

        //Different case
        assertNotEquals(new Quantity(5), new Item(new Name("Pants"),
                new Quantity(20), false).getQuantity());

    }

    @Test
    public void isChecked() {

        // Correct case
        assertTrue(new Item(new Name("Shirt"),
                new Quantity(10), true).isChecked());

        //Different case
        assertFalse(new Item(new Name("Shirt"),
                new Quantity(10), false).isChecked());
    }

    @Test
    public void isSameItem() {
        // same object -> returns true
        assertTrue(item.isSameItem(item));

        // null -> returns false
        assertFalse(item.isSameItem(null));

        // different name and quantity -> returns false
        Item editedItem = new Item(new Name("Raincoat"), new Quantity(30), false);
        assertFalse(editedItem.isSameItem(item));

        // different name -> returns false
        Item secondEditedItem = new Item(new Name("Pants"), new Quantity(10), false);
        assertFalse(editedItem.isSameItem(secondEditedItem));
    }

    @Test
    public void testEquals() {
        // Same case
        Item editedItem = new Item(new Name("Shirt"), new Quantity(10), false);
        assertEquals(item, editedItem);

        //Different case
        editedItem = new Item(new Name("Pants"), new Quantity(1), false);
        assertNotEquals(editedItem, item);
    }

    @Test
    public void testHashCode() {

        //Same case
        Item editedItem = new Item(new Name("Shirt"), new Quantity(10), false);
        assertEquals(editedItem.hashCode(), item.hashCode());

        //Different case
        editedItem = new Item(new Name("Pants"), new Quantity(1), false);
        assertNotEquals(editedItem.hashCode(), item.hashCode());
    }

    @Test
    public void testToString() {
        Item editedItem = new Item(new Name("Shirt"), new Quantity(10), false);
        assertEquals(item.toString(), editedItem.toString());

        editedItem = new Item(new Name("Pants"), new Quantity(1), false);
        assertNotEquals(editedItem.toString(), item.toString());

    }
}
