package seedu.address.model.listmanagers.activity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PriorityTest {

    @Test
    public void constructorNullThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Priority(null));
    }

    @Test
    public void constructorInvalidPriorityThrowsIllegalArgumentException() {
        Integer invalidPriority = -100;
        assertThrows(IllegalArgumentException.class, () -> new Priority(invalidPriority));
    }


    @Test
    public void isValidPriority() {

        // null priority
        assertThrows(NullPointerException.class, () -> Priority.isValidPriority(null));

        //Invalid priority
        assertFalse(Priority.isValidPriority(1000));
        assertFalse(Priority.isValidPriority(4)); // Out of bounds
        assertFalse(Priority.isValidPriority(-1)); // Negative value

        //Valid priority
        assertTrue(Priority.isValidPriority(1)); // Only 1 digit
        assertTrue(Priority.isValidPriority(0)); // 0 included

        assertTrue(Priority.isValidPriority(01)); //When a 0 is placed in front
    }

    @Test
    public void testToString() {
        assertEquals("1", new Priority(1).toString());
    }

    @Test
    public void testEquals() {
        assertEquals(new Priority(1), new Priority(1)); // When two priorities are the same.
        assertNotEquals(new Priority(1), new Priority(2)); // When two priorities are different.
    }

    @Test
    public void testHashCode() {
        // Equal Hashcode
        assertEquals(new Priority(1).hashCode(), new Priority(1).hashCode());

        //Non Equal Hashcode
        assertNotEquals(new Priority(1).hashCode(), new Priority(3).hashCode());
    }
}
