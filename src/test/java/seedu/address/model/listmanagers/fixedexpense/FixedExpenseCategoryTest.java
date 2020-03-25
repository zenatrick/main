package seedu.address.model.listmanagers.fixedexpense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class FixedExpenseCategoryTest {


    @Test
    public void constructorNullThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FixedExpenseCategory(null));
    }

    @Test
    public void constructorInvalidCategoryThrowsIllegalArgumentException() {
        String invalidCategory = "";
        assertThrows(IllegalArgumentException.class, () -> new FixedExpenseCategory(invalidCategory));
    }

    @Test
    public void isValidCategory() {
        // null category
        assertThrows(NullPointerException.class, () -> FixedExpenseCategory.isValidCategory(null));

        // blank category
        assertFalse(FixedExpenseCategory.isValidCategory("")); // Empty string
        assertFalse(FixedExpenseCategory.isValidCategory(" ")); // Spaces only

        // invalid category
        assertFalse(FixedExpenseCategory.isValidCategory("^")); // Non-alphanumeric characters
        assertFalse(FixedExpenseCategory.isValidCategory("Dog*")); // Contains non-alphanumeric characters.

        // valid category
        assertTrue(FixedExpenseCategory.isValidCategory("Hello")); //Normal case
        assertTrue(FixedExpenseCategory.isValidCategory("Hello1234")); // Contains alphanumeric characters.
        assertTrue(FixedExpenseCategory.isValidCategory(
                "hellomynameisJohn123andIamADog")); //Contains 30 alphanumeric char.
        assertTrue(FixedExpenseCategory.isValidCategory(
                "yoursystemrunsf1234anyhowjustpress")); // Contains 34 characters.

    }

    @Test
    public void testToString() {
        assertEquals("1000", new FixedExpenseCategory("1000").toString());
        assertEquals("HelloImaDog", new FixedExpenseCategory("HelloImaDog").toString());
        assertEquals("1000Money", new FixedExpenseCategory("1000Money").toString());

        //Wrong toString
        assertNotEquals("Wrong", new FixedExpenseCategory("Hello").toString());
    }

    @Test
    public void testEquals() {
        //Equal FixedExpenseCategory Object
        assertEquals(new FixedExpenseCategory("1000Hello"),
                new FixedExpenseCategory("1000Hello")); // When two categories are the same.

        //Non Equal FixedExpenseCategory Object
        assertNotEquals(new FixedExpenseCategory("1000Bye"),
                new FixedExpenseCategory("2000Hehe")); // When two categories are different.
    }

    @Test
    public void testHashCode() {
        // Equal Hashcode
        assertEquals(new FixedExpenseCategory("Hashcode").hashCode(), new FixedExpenseCategory("Hashcode").hashCode());

        //Non Equal Hashcode
        assertNotEquals(new FixedExpenseCategory("hashcode").hashCode(),
                new FixedExpenseCategory("HashCode").hashCode());
    }
}
