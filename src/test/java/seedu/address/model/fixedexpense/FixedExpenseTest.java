package seedu.address.model.fixedexpense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.DigestException;

import org.junit.jupiter.api.Test;

class FixedExpenseTest {

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
        // Correct case
        assertEquals(new Amount("100"), new FixedExpense(new Amount("100"),
                new Description("Test"), new Category("Food")).getAmount());

        //Different case
        assertNotEquals(new Amount("500"), new FixedExpense(new Amount("100"),
                new Description("Test"), new Category("Food")).getAmount());
    }

    @Test
    void getDescription() {
        // Correct case
        assertEquals(new Description("Test"), new FixedExpense(new Amount("100"),
                new Description("Test"), new Category("Food")).getDescription());

        //Different case
        assertNotEquals(new Description("50p"), new FixedExpense(new Amount("100"),
                new Description("Test"), new Category("Food")).getDescription());

    }

    @Test
    void getCategory() {
        // Correct case
        assertEquals(new Category("Food"), new FixedExpense(new Amount("100"),
                new Description("Test"), new Category("Food")).getCategory());

        //Different case
        assertNotEquals(new Category("Lala"), new FixedExpense(new Amount("100"),
                new Description("Test"), new Category("Food")).getCategory());
    }

    @Test
    void testEquals() {
        //Same case
        assertEquals(new FixedExpense(new Amount("100"), new Description("Test"),
                new Category("Food")), new FixedExpense(new Amount("100"),
                new Description("Test"), new Category("Food")));

        //Different case
        assertNotEquals(new FixedExpense(new Amount("200"), new Description("Testy"),
                new Category("Foody")), new FixedExpense(new Amount("100"),
                new Description("Test"), new Category("Food")));
    }

    @Test
    void testHashCode() {
        //Same case
        assertEquals(new FixedExpense(new Amount("12345"),
                new Description("Foody"), new Category("Haha")).hashCode(),
                new FixedExpense(new Amount("12345"), new Description("Foody"),
                        new Category("Haha")).hashCode());
        //Different Case
        assertNotEquals(new FixedExpense(new Amount("123456"),
                        new Description("Food"), new Category("Hahas")).hashCode(),
                new FixedExpense(new Amount("12345"), new Description("Foody"),
                        new Category("Haha")).hashCode());
    }

    @Test
    void testToString() {
        //Same case
        FixedExpense fixedExpense = new FixedExpense(new Amount("100"),
                new Description("Food"), new Category("Eating"));
        assertEquals("Fixed Expense Entry - Description: " + fixedExpense.getDescription().toString()
                + " Amount: " + fixedExpense.getAmount().toString()
                + " Category: " + fixedExpense.getCategory().toString(),
                "Fixed Expense Entry - Description: " + fixedExpense.getDescription().toString()
                        + " Amount: " + fixedExpense.getAmount().toString()
                        + " Category: " + fixedExpense.getCategory().toString());

        //Different case
        assertNotEquals("Fixed Expense Entry - Description: " + fixedExpense.getAmount().toString()
                        + " Amount: " + fixedExpense.getDescription().toString()
                        + " Category: " + fixedExpense.getCategory().toString(),
                "Fixed Expense Entry - Description: " + fixedExpense.getDescription().toString()
                        + " Amount: " + fixedExpense.getAmount().toString()
                        + " Category: " + fixedExpense.getCategory().toString());

    }
}
