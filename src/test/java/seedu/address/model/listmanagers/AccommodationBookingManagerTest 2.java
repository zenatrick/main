package seedu.address.model.listmanagers;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.listmanagers.accommodationbooking.AccommodationBooking;

class AccommodationBookingManagerTest {

    private final AccommodationBookingManager accommodationBookingManager = new AccommodationBookingManager();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), accommodationBookingManager.getAccommodationBookingList());
    }
    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> accommodationBookingManager.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        List<AccommodationBooking> accommodationBookings = new ArrayList<>();
        accommodationBookings.add(new AccommodationBooking())
        AccommodationBookingManager newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }



}
