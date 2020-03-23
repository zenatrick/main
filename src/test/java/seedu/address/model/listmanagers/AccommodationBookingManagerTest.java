package seedu.address.model.listmanagers;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAccommodations.ACCOMMODATION_BOOKING_HOTEL;
import static seedu.address.testutil.TypicalAccommodations.getTypicalAccommodationManager;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.listmanagers.accommodationbooking.AccommodationBooking;
import seedu.address.model.util.uniquelist.exceptions.DuplicateElementException;

class AccommodationBookingManagerTest {

    private final AccommodationBookingManager accommodationBookingManager = new AccommodationBookingManager();


    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), accommodationBookingManager.getAccommodationBookingList());
    }

    @Test
    public void resetDataNullThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> accommodationBookingManager.resetData(null));
    }

    @Test
    public void resetDataWithValidReadOnlyAccommodationBookingManagerReplacesData() {
        AccommodationBookingManager newData = getTypicalAccommodationManager();
        accommodationBookingManager.resetData(newData);
        assertEquals(newData, accommodationBookingManager);
    }

    @Test
    public void resetDataWithDuplicateAccommodationBookingThrowsDuplicateElementException() {
        List<AccommodationBooking> newAccommodations = Arrays.asList(ACCOMMODATION_BOOKING_HOTEL,
                ACCOMMODATION_BOOKING_HOTEL);
        AccommodationBookingManagerStub newData = new AccommodationBookingManagerStub(newAccommodations);
        assertThrows(DuplicateElementException.class, () -> accommodationBookingManager.resetData(newData));
    }

    @Test
    public void hasAccommodationBookingNullAccommodationBookingThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> accommodationBookingManager
                .hasAccommodationBooking(null));
    }

    @Test
    public void hasAccommodationBookingNotInAccommodationBookingManagerReturnsFalse() {
        assertFalse(accommodationBookingManager.hasAccommodationBooking(ACCOMMODATION_BOOKING_HOTEL));
    }

    @Test
    public void hasAccommodationBookingInAccommodationBookingManagerReturnsTrue() {
        accommodationBookingManager.addAccommodationBooking(ACCOMMODATION_BOOKING_HOTEL);
        assertTrue(accommodationBookingManager.hasAccommodationBooking(ACCOMMODATION_BOOKING_HOTEL));
    }

    @Test
    public void getAccommodationBookingModifyListThrowsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> accommodationBookingManager
                .getAccommodationBookingList().remove(0));
    }

    /**
     * A stub ReadOnlyAccommodationBookingManager whose accommodation list can violate interface constraints.
     */
    private static class AccommodationBookingManagerStub implements ReadOnlyAccommodationBookingManager {
        private final ObservableList<AccommodationBooking> accommodationBookings =
                FXCollections.observableArrayList();

        AccommodationBookingManagerStub(Collection<AccommodationBooking> accommodationBookings) {
            this.accommodationBookings.setAll(accommodationBookings);
        }

        @Override
        public ObservableList<AccommodationBooking> getAccommodationBookingList() {
            return accommodationBookings;
        }
    }



}