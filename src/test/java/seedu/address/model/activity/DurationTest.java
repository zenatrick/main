package seedu.address.model.activity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


class DurationTest {

    @Test
    public void constructorNullThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Duration(null));
    }

    @Test
    public void constructorInvalidDurationThrowsIllegalArgumentException() {
        String invalidDuration = "";
        assertThrows(IllegalArgumentException.class, () -> new Duration(invalidDuration));
    }


    @Test
    public void isValidDuration() {

        // null Duration
        assertThrows(NullPointerException.class, () -> Duration.isValidDuration(null));

        // Blank Duration
        assertFalse(Duration.isValidDuration("")); // empty duration
        assertFalse(Duration.isValidDuration(" ")); // just spaces.


        //Not valid Duration
        assertFalse(Duration.isValidDuration("1000.000"));
        assertFalse(Duration.isValidDuration("1000"));
        assertFalse(Duration.isValidDuration("0")); // 0 included

        //Valid Duration
        assertTrue(Duration.isValidDuration("23")); // Normal case
        assertTrue(Duration.isValidDuration("1")); // Only 1 digit
        assertTrue(Duration.isValidDuration("023")); //When a 0 is placed in front
        assertTrue(Duration.isValidDuration("0000000000000001")); // Trailing 0s
    }

    @Test
    public void testToString() {
        assertEquals("2", new Duration("2").toString());
    }

    @Test
    public void testEquals() {
        assertEquals(new Duration("2"), new Duration("2")); // When two duration are the same.
        assertNotEquals(new Duration("2"), new Duration("3")); // When two durations are different.
    }

    @Test
    public void testHashCode() {
        // Equal Hashcode
        assertEquals(new Duration("20").hashCode(), new Duration("20").hashCode());

        //Non Equal Hashcode
        assertNotEquals(new Duration("13").hashCode(), new Duration("21").hashCode());
    }
}

