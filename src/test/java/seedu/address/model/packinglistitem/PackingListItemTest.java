//package seedu.address.model.packinglistitem;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.testutil.Assert.assertThrows;
//
//import org.junit.jupiter.api.Test;
//
//class PackingListItemTest {
//
//    private final PackingListItem packingListItem = new PackingListItem(new ItemName("Shirt") , new Quantity(10) ,
//    false);
//
//    @Test
//    public void constructorInvalidNameThrowsIllegalArgumentException() {
//        String invalidName = " ";
//        Integer invalidQuantity = 0;
//        boolean isChecked = false;
//        assertThrows(IllegalArgumentException.class, () -> new PackingListItem(new ItemName(invalidName),
//                new Quantity(invalidQuantity), isChecked));
//    }
//
//    @Test
//    public void getItemName() {
//
//        // Correct case
//        assertEquals(new ItemName("Shirt"), new PackingListItem(new ItemName("Shirt"),
//                new Quantity(10), false).getItemName());
//
//        //Different case
//        assertNotEquals(new ItemName("Shirt"), new PackingListItem(new ItemName("Pants"),
//                new Quantity(10), false).getItemName());
//
//    }
//
//    @Test
//    public void getQuantity() {
//
//        // Correct case
//        assertEquals(new Quantity(10), new PackingListItem(new ItemName("Shirt"),
//                new Quantity(10), false).getQuantity());
//
//        //Different case
//        assertNotEquals(new Quantity(5), new PackingListItem(new ItemName("Pants"),
//                new Quantity(20), false).getQuantity());
//
//    }
//
//    @Test
//    public void isChecked() {
//
//        // Correct case
//        assertTrue(new PackingListItem(new ItemName("Shirt"),
//                new Quantity(10), true).isChecked());
//
//        //Different case
//        assertFalse(new PackingListItem(new ItemName("Shirt"),
//                new Quantity(10), false).isChecked());
//    }
//
//    @Test
//    public void isSameItem() {
//        // same object -> returns true
//        assertTrue(packingListItem.isSameItem(packingListItem));
//
//        // null -> returns false
//        assertFalse(packingListItem.isSameItem(null));
//
//        // different name and quantity -> returns false
//        PackingListItem editedPackingListItem = new PackingListItem(new ItemName("Raincoat"), new Quantity(30),
//        false);
//        assertFalse(editedPackingListItem.isSameItem(packingListItem));
//
//        // different name -> returns false
//        PackingListItem secondEditedPackingListItem = new PackingListItem(new ItemName("Pants"), new Quantity(10),
//        false);
//        assertFalse(editedPackingListItem.isSameItem(secondEditedPackingListItem));
//    }
//
//    @Test
//    public void testEquals() {
//        // Same case
//        PackingListItem editedPackingListItem = new PackingListItem(new ItemName("Shirt"), new Quantity(10), false);
//        assertEquals(packingListItem, editedPackingListItem);
//
//        //Different case
//        editedPackingListItem = new PackingListItem(new ItemName("Pants"), new Quantity(1), false);
//        assertNotEquals(editedPackingListItem, packingListItem);
//    }
//
//    @Test
//    public void testHashCode() {
//
//        //Same case
//        PackingListItem editedPackingListItem = new PackingListItem(new ItemName("Shirt"), new Quantity(10), false);
//        assertEquals(editedPackingListItem.hashCode(), packingListItem.hashCode());
//
//        //Different case
//        editedPackingListItem = new PackingListItem(new ItemName("Pants"), new Quantity(1), false);
//        assertNotEquals(editedPackingListItem.hashCode(), packingListItem.hashCode());
//    }
//
//    @Test
//    public void testToString() {
//        PackingListItem editedPackingListItem = new PackingListItem(new ItemName("Shirt"), new Quantity(10), false);
//        assertEquals(packingListItem.toString(), editedPackingListItem.toString());
//
//        editedPackingListItem = new PackingListItem(new ItemName("Pants"), new Quantity(1), false);
//        assertNotEquals(editedPackingListItem.toString(), packingListItem.toString());
//
//    }
//}
