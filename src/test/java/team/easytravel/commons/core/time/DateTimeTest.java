package team.easytravel.commons.core.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import static team.easytravel.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Test Class for DateTime. Tests for {@code Date} and {@code Time} as well
 */
class DateTimeTest {


    private Date firstDate = Date.fromString("28-09-2020");
    private Time firstTime = Time.fromString("18:00");

    private Date secondDate = Date.fromString("30-09-2020");
    private Time secondTime = Time.fromString("18:00");

    private Date identicalFirstDate = Date.fromString("28-09-2020");
    private Time identicalFirstTime = Time.fromString("18:00");



    private DateTime firstDateTime = DateTime.of(firstDate, firstTime);
    private DateTime secondDateTime = DateTime.of(secondDate, secondTime);

    private DateTime identicalFirstDateTime = DateTime.of(identicalFirstDate, identicalFirstTime);
    private DateTime sameDayDateTime = DateTime.fromString("28-09-2020 22:00");

    @Test
    public void compareToAndObjectEquality() {

        // Event to Test equality for DateTime
        assertEquals(firstDateTime, identicalFirstDateTime);
        assertNotEquals(firstDateTime, secondDateTime);

        //Event to Test Comparison of Date and Time Objects
        assertEquals(firstTime, identicalFirstTime);
        assertEquals(firstDate, identicalFirstDate);
        assertNotEquals(firstTime, secondDate);
        assertNotEquals(firstTime, sameDayDateTime.getTime());

        //Event to Test Comparison of Date Time
        assertEquals(0, firstDateTime.compareTo(identicalFirstDateTime));
        assertEquals(-2, firstDateTime.compareTo(secondDateTime));
        assertEquals(2, secondDateTime.compareTo(firstDateTime)); // 2 days ahead
        assertEquals(1, sameDayDateTime.compareTo(firstDateTime)); // Same day, diff time


        assertEquals(-2, firstDate.compareTo(secondDate));
        assertEquals(0, firstTime.compareTo(secondTime));
        assertEquals(-1, firstTime.compareTo(sameDayDateTime.getTime()));

        assertEquals(0, firstDateTime.hoursUntilExclusive(identicalFirstDateTime)); // Identical time

        //Event to Test toString and hashCode Methods
        assertEquals(firstDateTime.toString(), identicalFirstDateTime.toString());
        assertEquals(firstDateTime.hashCode(), identicalFirstDateTime.hashCode());
        assertEquals(firstTime.toString(), identicalFirstTime.toString());
        assertEquals(firstTime.hashCode(), identicalFirstTime.hashCode());
        assertEquals(firstDate.toString(), identicalFirstDate.toString());
        assertEquals(firstDate.hashCode(), identicalFirstDate.hashCode());

        //Same day OR Same Time
        assertEquals(firstDate.hashCode(), sameDayDateTime.getDate().hashCode());

        assertNotEquals(firstDate.toString(), secondDate.toString());
        assertNotEquals(firstDate.hashCode(), secondDate.hashCode());

        assertNotEquals(firstDateTime.toString(), secondDateTime.toString());
        assertNotEquals(firstDateTime.hashCode(), secondDateTime.hashCode());

    }

    @Test
    public void stringAndMethodEquality() {
        assertEquals("28-09-2020 18:00", firstDateTime.getStorageFormat());
        assertNotEquals("28-09-2020 19:00", firstDateTime.getStorageFormat());

        assertEquals("18:00", firstTime.getStorageFormat());
        assertNotEquals("22:00", firstTime.getStorageFormat());

        assertEquals("28-09-2020", firstDate.getStorageFormat());
        assertNotEquals("29-09-2020", firstDate.getStorageFormat());

        assertEquals("28-09-2020 22:00", firstDateTime.plusHours(4).getStorageFormat());
        assertNotEquals("28-09-2020 21:00", firstDateTime.plusHours(4).getStorageFormat());
        assertEquals("30-09-2020", firstDate.plusDays(2).getStorageFormat());
        assertNotEquals("28-09-2020", firstDate.plusDays(2).getStorageFormat());

        assertEquals(3, firstDate.daysUntilInclusive(secondDate));
        assertNotEquals(2, firstDate.daysUntilInclusive(secondDate));


    }

    @Test
    public void invalidDateTime_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, ()-> Date.fromString("50-@4-2010"));
        assertThrows(IllegalArgumentException.class, ()-> Time.fromString("40:00"));
        assertThrows(IllegalArgumentException.class, ()-> DateTime.of(Date.fromString("90-04-2010"),
                Time.fromString("40:00")));
        assertThrows(IllegalArgumentException.class, ()-> DateTime.fromString("50-04-2020 40:00"));
    }

    @Test
    public void invalidEndDateComparisionStartDay_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> secondDate.daysUntilInclusive(firstDate));
    }


}
