package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.listmanagers.ReadOnlyAccommodationBookingManager;
import seedu.address.model.listmanagers.ReadOnlyActivityManager;
import seedu.address.model.listmanagers.ReadOnlyFixedExpenseManager;
import seedu.address.model.listmanagers.ReadOnlyPackingListManager;
import seedu.address.model.listmanagers.ReadOnlyTransportBookingManager;
import seedu.address.model.listmanagers.ReadOnlyUserPrefs;
import seedu.address.model.listmanagers.UserPrefs;
import seedu.address.model.trip.ReadOnlyTripManager;
import seedu.address.storage.accommodationbooking.AccommodationBookingStorage;
import seedu.address.storage.activity.ActivityManagerStorage;
import seedu.address.storage.fixedexpense.FixedExpenseStorage;
import seedu.address.storage.packinglist.PackingListStorage;
import seedu.address.storage.transportbooking.TransportBookingStorage;
import seedu.address.storage.trip.TripStorage;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private TransportBookingStorage transportBookingStorage;
    private FixedExpenseStorage fixedExpenseStorage;
    private AccommodationBookingStorage accommodationBookingStorage;
    private UserPrefsStorage userPrefsStorage;
    private ActivityManagerStorage activityManagerStorage;
    private PackingListStorage packingListStorage;
    private TripStorage tripStorage;

    /**
     * Instantiates a new Storage manager.
     *
     * @param addressBookStorage          the address book storage
     * @param transportBookingStorage     the transport booking storage
     * @param fixedExpenseStorage         the fixed expense storage
     * @param activityManagerStorage      the activity manager storage
     * @param accommodationBookingStorage the accommodation booking storage
     * @param packingListStorage          the packing list storage
     * @param userPrefsStorage            the user prefs storage
     */
    public StorageManager(AddressBookStorage addressBookStorage,
                          TransportBookingStorage transportBookingStorage,
                          FixedExpenseStorage fixedExpenseStorage,
                          ActivityManagerStorage activityManagerStorage,
                          AccommodationBookingStorage accommodationBookingStorage,
                          PackingListStorage packingListStorage, TripStorage tripStorage,
                          UserPrefsStorage userPrefsStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.transportBookingStorage = transportBookingStorage;
        this.fixedExpenseStorage = fixedExpenseStorage;
        this.activityManagerStorage = activityManagerStorage;
        this.accommodationBookingStorage = accommodationBookingStorage;
        this.packingListStorage = packingListStorage;
        this.tripStorage = tripStorage;
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

    // ================ FixedExpenseManager methods ==============================

    @Override
    public Path getFixedExpenseStorageFilePath() {
        return fixedExpenseStorage.getFixedExpenseStorageFilePath();
    }

    @Override
    public Optional<ReadOnlyFixedExpenseManager> readFixedExpenses() throws DataConversionException,
            IOException {
        return readFixedExpenses(fixedExpenseStorage.getFixedExpenseStorageFilePath());
    }

    @Override
    public Optional<ReadOnlyFixedExpenseManager> readFixedExpenses(
            Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return fixedExpenseStorage.readFixedExpenses(filePath);
    }

    @Override
    public void saveFixedExpenses(ReadOnlyFixedExpenseManager fixedExpenseManager) throws IOException {
        saveFixedExpenses(fixedExpenseManager, fixedExpenseStorage.getFixedExpenseStorageFilePath());
    }

    @Override
    public void saveFixedExpenses(
            ReadOnlyFixedExpenseManager fixedExpenseManager, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        fixedExpenseStorage.saveFixedExpenses(fixedExpenseManager, filePath);
    }

    // ================ ActivityManager methods ==============================

    @Override
    public Path getActivityManagerStorageFilePath() {
        return activityManagerStorage.getActivityManagerStorageFilePath();
    }

    @Override
    public Optional<ReadOnlyActivityManager> readActivityManager() throws DataConversionException,
            IOException {
        return readActivityManager(activityManagerStorage.getActivityManagerStorageFilePath());
    }

    @Override
    public Optional<ReadOnlyActivityManager> readActivityManager(
            Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return activityManagerStorage.readActivityManager(filePath);
    }

    @Override
    public void saveActivityManager(ReadOnlyActivityManager activityManager) throws IOException {
        saveActivityManager(activityManager, activityManagerStorage.getActivityManagerStorageFilePath());
    }

    @Override
    public void saveActivityManager(
            ReadOnlyActivityManager activityManager, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        activityManagerStorage.saveActivityManager(activityManager, filePath);
    }

    // ================ AccommodationBookingManager methods ==============================

    @Override
    public Path getAccommodationBookingStorageFilePath() {
        return accommodationBookingStorage.getAccommodationBookingStorageFilePath();
    }

    @Override
    public Optional<ReadOnlyAccommodationBookingManager> readAccommodationBookings() throws DataConversionException,
            IOException {
        return readAccommodationBookings(accommodationBookingStorage.getAccommodationBookingStorageFilePath());
    }

    @Override
    public Optional<ReadOnlyAccommodationBookingManager> readAccommodationBookings(
            Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return accommodationBookingStorage.readAccommodationBookings(filePath);
    }

    @Override
    public void saveAccommodationBookings(ReadOnlyAccommodationBookingManager accommodationBookingManager)
            throws IOException {
        saveAccommodationBookings(accommodationBookingManager,
                accommodationBookingStorage.getAccommodationBookingStorageFilePath());
    }

    @Override
    public void saveAccommodationBookings(
            ReadOnlyAccommodationBookingManager accommodationBookingManager, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        accommodationBookingStorage.saveAccommodationBookings(accommodationBookingManager, filePath);
    }

    //================ Packing List methods ==============================
    @Override
    public Path getPackingListStorageFilePath() {
        return packingListStorage.getPackingListStorageFilePath();
    }

    @Override
    public Optional<ReadOnlyPackingListManager> readPackingList() throws DataConversionException, IOException {
        return readPackingList(packingListStorage.getPackingListStorageFilePath());
    }

    @Override
    public Optional<ReadOnlyPackingListManager> readPackingList(Path filePath) throws DataConversionException,
            IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return packingListStorage.readPackingList(filePath);
    }

    @Override
    public void savePackingList(ReadOnlyPackingListManager packingList) throws IOException {
        savePackingList(packingList, packingListStorage.getPackingListStorageFilePath());
    }

    @Override
    public void savePackingList(ReadOnlyPackingListManager packingList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        packingListStorage.savePackingList(packingList, filePath);
    }

    @Override
    public Path getTripStorageFilePath() {
        return null;
    }

    @Override
    public Optional<ReadOnlyTripManager> readTrip() throws DataConversionException, IOException {
        return Optional.empty();
    }

    @Override
    public Optional<ReadOnlyTripManager> readTripManager(Path filePath) throws DataConversionException, IOException {
        return Optional.empty();
    }

    @Override
    public void saveTrip(ReadOnlyTripManager tripManager) throws IOException {

    }

    @Override
    public void saveTrip(ReadOnlyTripManager tripManager, Path filePath) throws IOException {

    }
}
