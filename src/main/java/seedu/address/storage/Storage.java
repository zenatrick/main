package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.listmanager.ReadOnlyActivityManager;
import seedu.address.model.listmanager.ReadOnlyFixedExpenseManager;
import seedu.address.model.listmanager.ReadOnlyPackingListManager;
import seedu.address.model.listmanager.ReadOnlyTransportBookingManager;
import seedu.address.storage.activity.ActivityManagerStorage;
import seedu.address.storage.fixedexpense.FixedExpenseStorage;
import seedu.address.storage.transportbooking.TransportBookingStorage;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, TransportBookingStorage,
        FixedExpenseStorage, ActivityManagerStorage,
        UserPrefsStorage {
    @Override
    Path getAddressBookFilePath();

    // ---- ActivityManager ----//

    @Override
    Path getActivityManagerStorageFilePath();

    @Override
    Optional<ReadOnlyActivityManager> readActivityManager()
            throws DataConversionException, IOException;

    @Override
    Optional<ReadOnlyActivityManager> readActivityManager(Path filePath)
            throws DataConversionException, IOException;

    @Override
    void saveActivityManager(ReadOnlyActivityManager activityManager)
            throws IOException;

    @Override
    void saveActivityManager(ReadOnlyActivityManager activityManager, Path filePath)
            throws IOException;
    // ---- --------- ----//

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException;

    @Override
    Path getTransportBookingStorageFilePath();

    @Override
    Optional<ReadOnlyTransportBookingManager> readTransportBookings() throws DataConversionException, IOException;

    @Override
    Optional<ReadOnlyTransportBookingManager> readTransportBookings(Path filePath) throws DataConversionException,
            IOException;

    @Override
    void saveTransportBookings(ReadOnlyTransportBookingManager transportBookingManager) throws IOException;

    @Override
    void saveTransportBookings(ReadOnlyTransportBookingManager transportBookingManager,
                               Path filePath) throws IOException;

    // ---- FixedExpenseManager ----//

    @Override
    Path getFixedExpenseStorageFilePath();

    @Override
    Optional<ReadOnlyFixedExpenseManager> readFixedExpenses() throws DataConversionException, IOException;

    @Override
    Optional<ReadOnlyFixedExpenseManager> readFixedExpenses(Path filePath) throws DataConversionException,
            IOException;

    @Override
    void saveFixedExpenses(ReadOnlyFixedExpenseManager fixedExpenseManager) throws IOException;

    @Override
    void saveFixedExpenses(ReadOnlyFixedExpenseManager fixedExpenseManager,
                               Path filePath) throws IOException;

    //--------//

    @Override
    Path getUserPrefsFilePath();

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;
}
