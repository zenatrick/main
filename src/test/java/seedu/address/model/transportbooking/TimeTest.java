package seedu.address.model.transportbooking;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

class TimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Time(null));
    }

    @Test
    public void constructor_invalidTime_throwsDateTimeParseException() {
        // Dont have 2016, Month 13, Year 13
        String now = "2016-13-13 10:30";
        assertThrows(DateTimeParseException.class ,()-> LocalDateTime.parse(now));
    }


    @Test
    void compareTo() {
    }

    @Test
    void testToString() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }
}