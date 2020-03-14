package seedu.address.model.packinglistitem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }


    @Test
    void isValidName() {

        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // blank name
        assertFalse(Name.isValidName("")); // Empty string
        assertFalse(Name.isValidName(" ")); // Spaces only
        assertFalse(Name.isValidName("  ")); // Multiple spaces

        // invalid name
        assertFalse(Name.isValidName("^")); // Non-alphanumeric characters
        assertFalse(Name.isValidName("Dog*")); // Contains non-alphanumeric characters.
        assertFalse(Name.isValidName("yoursystemrunsf1234anyhowjustpresswhymylifesobadnow"
                + "IwanttowithdrawfromNUS")); // Contains 74 characters.

        // Valid name
        assertTrue(Name.isValidName("Hello ")); //Normal case
        assertTrue(Name.isValidName("Hello1234")); // Contains alphanumeric characters.
        assertTrue(Name.isValidName("hellomynameisJ ohn12"
                + "Doghahahahahhacsgohehelaaldota")); //Contains 50 alphanumeric char with spaces.
        assertTrue(Name.isValidName("yoursystemrunsf1234anyhow"
                + "justpress")); // Contains 34 characters.


    }

    @Test
    void testToString() {

        assertEquals("1000", new Name("1000").toString());
        assertEquals("HelloImaDog", new Name("HelloImaDog").toString()); //Caps and non caps characters
        assertEquals("1000Money", new Name("1000Money").toString()); //Alphanumeric


        //Wrong toString
        assertNotEquals("Wrong", new Name("Hello").toString());

    }

    @Test
    void testEquals() {
        //Equal Description Object
        assertEquals(new Name("10Shirt"), new Name("10Shirt")); // When two name are the same.

        //Non Equal Description Object
        assertNotEquals(new Name("10Pants"), new Name("20Shirt")); // When two name are different.

    }

    @Test
    void testHashCode() {

        // Equal Hashcode
        assertEquals(new Name("Hashcode").hashCode(), new Name("Hashcode").hashCode());

        //Non Equal Hashcode
        assertNotEquals(new Name("hashcode").hashCode(), new Name("HashCode").hashCode());


    }
}