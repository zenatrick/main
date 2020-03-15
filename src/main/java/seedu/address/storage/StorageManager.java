package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.listmanager.ReadOnlyTransportBookingManager;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private TransportBookingStorage transportBookingStorage;
    private UserPrefsStorage userPrefsStorage;

    public StorageManager(AddressBookStorage addressBookStorage,
                          TransportBookingStorage transportBookingStorage,
                          UserPrefsStorage userPrefsStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.transportBookingStorage = transportBookingStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    // ================ TransportBookingManager methods ==============================

    @Override
    public Path getTransportBookingStorageFilePath() {
        return transportBookingStorage.getTransportBookingStorageFilePath();
    }

    @Override
    public Optional<ReadOnlyTransportBookingManager> readTransportBookings() throws DataConversionException,
            IOException {
        return readTransportBookings(transportBookingStorage.getTransportBookingStorageFilePath());
    }

    @Override
    public Optional<ReadOnlyTransportBookingManager> readTransportBookings(
            Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return transportBookingStorage.readTransportBookings(filePath);
    }

    @Override
    public void saveTransportBookings(ReadOnlyTransportBookingManager transportBookingManager) throws IOException {
        saveTransportBookings(transportBookingManager, transportBookingStorage.getTransportBookingStorageFilePath());
    }

    @Override
    public void saveTransportBookings(
            ReadOnlyTransportBookingManager transportBookingManager, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        transportBookingStorage.saveTransportBookings(transportBookingManager, filePath);
    }

}
