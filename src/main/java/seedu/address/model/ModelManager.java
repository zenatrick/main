package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.trip.Trip;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook tripBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Trip> filteredTrips;



    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook tripBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(tripBook, userPrefs);

        logger.fine("Initializing with address book: " + tripBook + " and user prefs " + userPrefs);

        this.tripBook = new AddressBook(tripBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredTrips = new FilteredList<Trip>(this.tripBook.getTripList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== EtBook ================================================================================

    @Override
    public void setTripBook(ReadOnlyAddressBook addressBook) {
        this.tripBook.resetData(tripBook);
    }

    @Override
    public ReadOnlyAddressBook getTripBook() {
        return tripBook;
    }

    @Override
    public boolean hasTrip(Trip trip) {
        requireNonNull(trip);
        return tripBook.hasTrip(trip);
    }

    @Override
    public void deleteTrip(Trip target) {
        tripBook.removeTrip(target);
    }

    @Override
    public void addTrip(Trip trip) {
        tripBook.addTrip(trip);
        updateFilteredEtList(PREDICATE_SHOW_ALL_ET);
    }

    @Override
    public void setTrip(Trip target, Trip editedTrip) {
        requireAllNonNull(target, editedTrip);

        tripBook.setTrip(target, editedTrip);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Trip> getFilteredEtList() {
        return filteredTrips;
    }

    @Override
    public void updateFilteredEtList(Predicate<Trip> predicate) {
        requireNonNull(predicate);
        filteredTrips.setPredicate(predicate);
    }


    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return tripBook.equals(other.tripBook)
                && userPrefs.equals(other.userPrefs)
                && filteredTrips.equals(other.filteredTrips);
    }

}
