package seedu.address.storage;

import seedu.address.storage.accommodationbooking.AccommodationBookingStorage;
import seedu.address.storage.activity.ActivityManagerStorage;
import seedu.address.storage.fixedexpense.FixedExpenseStorage;
import seedu.address.storage.packinglist.PackingListStorage;
import seedu.address.storage.transportbooking.TransportBookingStorage;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, TransportBookingStorage,
        FixedExpenseStorage, ActivityManagerStorage, AccommodationBookingStorage, PackingListStorage,
        UserPrefsStorage {
}
