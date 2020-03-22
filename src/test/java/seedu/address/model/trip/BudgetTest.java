package seedu.address.model.trip;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BudgetTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Budget(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        Integer invalidBudget = 0;
        assertThrows(IllegalArgumentException.class, () -> new Budget(invalidBudget));
    }

    @Test
    public void isValidBudget() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Budget.isValidBudget(null));

        // invalid phone numbers
        assertFalse(Budget.isValidBudget(-000000)); // negative 0s
        assertFalse(Budget.isValidBudget(-2)); // spaces only

        // valid phone numbers
        assertTrue(Budget.isValidBudget(911)); // exactly 3 numbers
        assertTrue(Budget.isValidBudget(000000001)); //extra 0s
        assertTrue(Budget.isValidBudget(999999999)); // very big number
    }
}
