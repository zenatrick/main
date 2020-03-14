package seedu.address.model.fixedexpense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription));
    }

    @Test
    void isValidDescription() {

        // null description
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // blank description
        assertFalse(Description.isValidDescription("")); // Empty string
        assertFalse(Description.isValidDescription("  ")); // Multiple Spaces
        assertFalse(Description.isValidDescription(" ")); // Single space

        // invalid description
        assertFalse(Description.isValidDescription("^")); // Non-alphanumeric characters
        assertFalse(Description.isValidDescription("Dog*")); // Contains non-alphanumeric characters.
        assertFalse(Description.isValidDescription("yoursystemrunsf1234anyhowjustpresswhymylifesobadnow"
                + "IwanttowithdrawfromNUS")); // Contains 74 characters.


        // Valid name
        assertTrue(Description.isValidDescription("Hello ")); //Normal case
        assertTrue(Description.isValidDescription("Hello1234")); // Contains alphanumeric characters.
        assertTrue(Description.isValidDescription("hellomynameisJ ohn123an dIamA"
                + "Dog")); //Contains 30 alphanumeric char with spaces.
        assertTrue(Description.isValidDescription("yoursystemrunsf1234anyhow"
                + "justpress")); // Contains 34 characters.


    }

    @Test
    void testToString() {
        assertEquals("1000", new Description("1000").toString());
        assertEquals("HelloImaDog", new Description("HelloImaDog").toString()); //Caps and non caps characters
        assertEquals("1000Money", new Description("1000Money").toString()); //Alphanumeric


        //Wrong toString
        assertNotEquals("Wrong", new Description("Hello").toString());
    }

    @Test
    void testEquals() {
        //Equal Description Object
        assertEquals(new Description("Food"), new Description("Food")); // When two description are the same.

        //Non Equal Description Object
        assertNotEquals(new Description("Foody"), new Description("Food")); // When two description are different.

    }

    @Test
    void testHashCode() {
        // Equal Hashcode
        assertEquals(new Description("Hashcode").hashCode(), new Description("Hashcode").hashCode());

        //Non Equal Hashcode
        assertNotEquals(new Description("hashcode").hashCode(), new Description("HashCode").hashCode());

    }
}
