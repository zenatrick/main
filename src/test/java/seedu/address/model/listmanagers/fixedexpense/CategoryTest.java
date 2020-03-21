package seedu.address.model.listmanagers.fixedexpense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CategoryTest {


    @Test
    public void constructorNullThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Category(null));
    }

    @Test
    public void constructorInvalidCategoryThrowsIllegalArgumentException() {
        String invalidCategory = "";
        assertThrows(IllegalArgumentException.class, () -> new Category(invalidCategory));
    }

    @Test
    public void isValidCategory() {
        // null category
        assertThrows(NullPointerException.class, () -> Category.isValidCategory(null));

        // blank category
        assertFalse(Category.isValidCategory("")); // Empty string
        assertFalse(Category.isValidCategory(" ")); // Spaces only

        // invalid category
        assertFalse(Category.isValidCategory("^")); // Non-alphanumeric characters
        assertFalse(Category.isValidCategory("Dog*")); // Contains non-alphanumeric characters.

        // valid category
        assertTrue(Category.isValidCategory("Hello")); //Normal case
        assertTrue(Category.isValidCategory("Hello1234")); // Contains alphanumeric characters.
        assertTrue(Category.isValidCategory("hellomynameisJohn123andIamADog")); //Contains 30 alphanumeric char.
        assertTrue(Category.isValidCategory("yoursystemrunsf1234anyhowjustpress")); // Contains 34 characters.

    }

    @Test
    public void testToString() {
        assertEquals("1000", new Category("1000").toString());
        assertEquals("HelloImaDog", new Category("HelloImaDog").toString());
        assertEquals("1000Money", new Category("1000Money").toString());

        //Wrong toString
        assertNotEquals("Wrong", new Category("Hello").toString());
    }

    @Test
    public void testEquals() {
        //Equal Category Object
        assertEquals(new Category("1000Hello"), new Category("1000Hello")); // When two categories are the same.

        //Non Equal Category Object
        assertNotEquals(new Category("1000Bye"), new Category("2000Hehe")); // When two categories are different.
    }

    @Test
    public void testHashCode() {
        // Equal Hashcode
        assertEquals(new Category("Hashcode").hashCode(), new Category("Hashcode").hashCode());

        //Non Equal Hashcode
        assertNotEquals(new Category("hashcode").hashCode(), new Category("HashCode").hashCode());
    }
}
