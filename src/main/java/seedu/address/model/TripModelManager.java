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

public class TripModelManager implements TripModel {

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TripBook tripBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Trip> filteredTrips;

    public TripModelManager(ReadOnlyTripBook tripBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(tripBook, userPrefs);

        logger.fine("Initializing with address book: " + tripBook + " and user prefs " + userPrefs);

        this.tripBook = new TripBook(tripBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredTrips = new FilteredList<>(this.tripBook.getTripList());
    }

    public TripModelManager() {
        this(new TripBook(), new UserPrefs());
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

    //=========== TripBook ================================================================================

    @Override
    public void setTripBook(ReadOnlyTripBook addressBook) {
        this.tripBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyTripBook getAddressBook() {
        return tripBook;
    }

    @Override
    public boolean hasTrip(Trip trip) {
        requireNonNull(trip);
        return tripBook.hasTrip(trip);
    }

    @Override
    public void deleteTrip(Trip trip) {
        tripBook.removeTrip(trip);
    }

    @Override
    public void addTrip(Trip trip) {
        tripBook.addTrip(trip);
        updateFilteredTripList(PREDICATE_SHOW_ALL_TRIPS);
    }

    @Override
    public void setTrip(Trip target, Trip editedPerson) {
        requireAllNonNull(target, editedPerson);
        tripBook.setTrip(target, editedPerson);
    }

    @Override
    public ObservableList<Trip> getFilteredTripList() {
        return filteredTrips;
    }

    @Override
    public void updateFilteredTripList(Predicate<Trip> predicate) {
        requireNonNull(predicate);
        filteredTrips.setPredicate(predicate);
    }


}
