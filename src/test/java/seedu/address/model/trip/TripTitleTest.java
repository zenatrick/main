package seedu.address.model.trip;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class TripTitleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TripTitle(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new TripTitle(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> TripTitle.isValidTitle(null));

        // invalid name
        assertFalse(TripTitle.isValidTitle("")); // empty string
        assertFalse(TripTitle.isValidTitle(" ")); // spaces only
        assertFalse(TripTitle.isValidTitle("^")); // only non-alphanumeric characters
        assertFalse(TripTitle.isValidTitle("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(TripTitle.isValidTitle("peter jack")); // alphabets only
        assertTrue(TripTitle.isValidTitle("12345")); // numbers only
        assertTrue(TripTitle.isValidTitle("peter the 2nd")); // alphanumeric characters
        assertTrue(TripTitle.isValidTitle("Capital Tan")); // with capital letters
        assertTrue(TripTitle.isValidTitle("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
