package seedu.address.model.fixedexpense;

import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

class CategoryTest {


    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Category(null));
    }

    @Test
    public void constructor_invalidCategory_throwsIllegalArgumentException() {
        String invalidCategory = "";
        assertThrows(IllegalArgumentException.class, () -> new Category(invalidCategory));

    }

    @Test
    void isValidCategory() {
        // null category
        assertThrows(NullPointerException.class, () -> Category.isValidCategory(null));

        // blank category
        assertTrue(Category.isValidCategory(""));

    }

    @Test
    void testToString() {
    }

    @Test
    void testEquals() {
    }
}