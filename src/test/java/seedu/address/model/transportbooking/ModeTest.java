package seedu.address.model.transportbooking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


class ModeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Mode(null));
    }

    @Test
    public void constructor_invalidMode_throwsIllegalArgumentException() {
        String invalidMode = "";
        assertThrows(IllegalArgumentException.class, () -> new Mode(invalidMode));
    }

    @Test
    void isValidMode() {

        // null mode
        assertThrows(NullPointerException.class, () -> Mode.isValidMode(null));

        // blank mode
        assertFalse(Mode.isValidMode("")); // Empty string
        assertFalse(Mode.isValidMode("  ")); // Multiple Spaces
        assertFalse(Mode.isValidMode(" ")); // Single space

        // invalid mode
        assertFalse(Mode.isValidMode("^")); // Non-alphanumeric characters
        assertFalse(Mode.isValidMode("Dog*")); // Contains non-alphanumeric characters.
        assertFalse(Mode.isValidMode("yoursystemrunsf1234anyhowjustpresswhymylifesobadnow"
                + "IwanttowithdrawfromNUS")); // Contains 74 characters.
        assertFalse(Mode.isValidMode("Rocket"));
        assertFalse(Mode.isValidMode("SpaceShip"));



        // Valid mode
        assertTrue(Mode.isValidMode("plane"));
        assertTrue(Mode.isValidMode("train"));
        assertTrue(Mode.isValidMode("bus"));
        assertTrue(Mode.isValidMode("car"));
        assertTrue(Mode.isValidMode("others"));
        assertTrue(Mode.isValidMode("PLane")); //TODO Check with boss if he wants this format a not.
        assertTrue(Mode.isValidMode("TRain"));
        assertTrue(Mode.isValidMode("BUS"));


    }

    @Test
    void testToString() {
        assertEquals("bus", new Mode("bus").toString());
        assertEquals("PLAne", new Mode("PLAne").toString()); //Caps and non caps characters

        //Wrong toString
        assertNotEquals("Wrong", new Mode("Plane").toString());
    }

    @Test
    void testEquals() {

        //Equal Mode Object
        assertEquals(new Mode("plane"), new Mode("plane")); // When two description are the same.

        //Non Equal Mode Object
        assertNotEquals(new Mode("train"), new Mode("plane")); // When two description are different.


    }

    @Test
    void testHashCode() {

        // Equal Hashcode
        assertEquals(new Mode("plane").hashCode(), new Mode("plane").hashCode());

        //Non Equal Hashcode
        assertNotEquals(new Mode("train").hashCode(), new Mode("plane").hashCode());


    }
}
