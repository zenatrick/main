package seedu.address.model.fixedexpense;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class AmountTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Amount(null));
    }

    @Test
    public void constructor_invalidAmount_throwsIllegalArgumentException() {
        String invalidAmount = "";
        assertThrows(IllegalArgumentException.class, () -> new Amount(invalidAmount));
    }


    @Test
    void isValidAmount() {

        // null Amount
        assertThrows(NullPointerException.class, () -> Amount.isValidAmount(null));

        // Blank Amount
        assertFalse(Amount.isValidAmount("")); // empty amount
        assertFalse(Amount.isValidAmount(" ")); // just spaces.

        // Amount with "$/¥" in front
        assertFalse(Amount.isValidAmount("$3000"));
        assertFalse(Amount.isValidAmount("¥3000"));

        //Valid amount
        assertTrue(Amount.isValidAmount("1000")); // Normal case
        assertTrue(Amount.isValidAmount("1")); // Only 1 digit
        assertTrue(Amount.isValidAmount("0")); // 0 included
        assertTrue(Amount.isValidAmount("0123")); //When a 0 is placed in front
        assertTrue(Amount.isValidAmount("10000000000000000000000")); // Very large number
    }

    @Test
    void testToString() {
        assertEquals("1000", new Amount("1000").toString());
    }

    @Test
    void testEquals() {
        assertEquals(new Amount("1000"), new Amount("1000")); // When two amounts are the same.
        assertNotEquals(new Amount("1000"), new Amount("2000")); // When two amounts are different.
    }
}