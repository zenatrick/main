package seedu.address.model.fixedexpense;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FixedExpenseTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FixedExpense(null, null, null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidAmount = "";
        String invalidDescription = "";
        String invalidCategory = "";
        assertThrows(IllegalArgumentException.class, () -> new FixedExpense(new Amount(invalidAmount),
                new Description(invalidDescription), new Category(invalidCategory)));
    }



    @Test
    void getAmount() {


    }

    @Test
    void getDescription() {
    }

    @Test
    void getCategory() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }
}