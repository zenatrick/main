package team.easytravel.model.trip;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import team.easytravel.testutil.Assert;

public class BudgetTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Budget(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        Double invalidBudget = 0.0;
        Assert.assertThrows(IllegalArgumentException.class, () -> new Budget(invalidBudget));
    }

    @Test
    public void isValidBudget() {
        // null phone number
        Assert.assertThrows(NullPointerException.class, () -> Budget.isValidBudget(null));

        // invalid phone numbers
        assertFalse(Budget.isValidBudget(-000000.0)); // negative 0s
        assertFalse(Budget.isValidBudget(-2.0)); // spaces only
        assertFalse(Budget.isValidBudget(999999999.0)); // very big number

        // valid phone numbers
        assertTrue(Budget.isValidBudget(911.0)); // exactly 3 numbers
        assertTrue(Budget.isValidBudget(000000001.0)); //extra 0s

    }
}
