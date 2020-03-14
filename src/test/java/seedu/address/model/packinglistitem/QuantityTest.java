package seedu.address.model.packinglistitem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class QuantityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Quantity(null));
    }

    @Test
    public void constructor_invalidQuantity_throwsIllegalArgumentException() {
        Integer integer = (Integer) 0;
        assertThrows(IllegalArgumentException.class, () -> new Quantity(integer));
    }

    @Test
    void isValidQuantity() {

        // null quantity
        Integer integer = (Integer) null;
        assertThrows(NullPointerException.class, () -> Quantity.isValidQuantity(integer));

        // invalid quantity
        assertFalse(Quantity.isValidQuantity(0)); // 0 quantity.
        assertFalse(Quantity.isValidQuantity(-100)); //Negative quantity
        assertFalse(Quantity.isValidQuantity(3 / 4)); //Fraction Quantity
        assertFalse(Quantity.isValidQuantity(1000)); //More then 100 quantity.

        // Valid quantity
        assertTrue(Quantity.isValidQuantity(4)); //Normal case
        assertTrue(Quantity.isValidQuantity(18)); //Normal case
    }

    @Test
    void testToString() {

        assertEquals("10", new Quantity(10).toString());

        //Wrong toString
        assertNotEquals("10", new Quantity(18).toString());

    }

    @Test
    void testEquals() {

        //Equal Description Object
        assertEquals(new Quantity(18), new Quantity(18)); // When two quantities are the same.

        //Non Equal Description Object
        assertNotEquals(new Quantity(4), new Quantity(23)); // When two quantities are different.

    }

    @Test
    void testHashCode() {

        // Equal Hashcode
        assertEquals(new Quantity(1).hashCode(), new Quantity(1).hashCode());

        //Non Equal Hashcode
        assertNotEquals(new Quantity(15).hashCode(), new Quantity(1).hashCode());


    }
}
