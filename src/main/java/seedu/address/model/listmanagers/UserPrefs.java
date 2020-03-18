package seedu.address.model.listmanagers;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {
    private static final Path DEFAULT_FOLDER_PATH = Path.of("data");

    private GuiSettings guiSettings = new GuiSettings();
    private Path addressBookFilePath = DEFAULT_FOLDER_PATH.resolve("addressbook.json");
    private Path transportBookingStorageFilePath = DEFAULT_FOLDER_PATH.resolve("transportation.json");
    private Path fixedExpenseStorageFilePath = DEFAULT_FOLDER_PATH.resolve("expense.json");
    private Path activityManagerStorageFilePath = DEFAULT_FOLDER_PATH.resolve("activity.json");
    private Path accommodationBookingStorageFilePath = DEFAULT_FOLDER_PATH.resolve("accommodation.json");
    private Path packingListStorageFilePath = DEFAULT_FOLDER_PATH.resolve("packinglist.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {
    }

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setAddressBookFilePath(newUserPrefs.getAddressBookFilePath());
        setActivityManagerStorageFilePath(newUserPrefs.getActivityManagerStorageFilePath());
        setAccommodationBookingStorageFilePath(newUserPrefs.getAccommodationBookingStorageFilePath());
        setTransportBookingStorageFilePath(newUserPrefs.getTransportBookingStorageFilePath());
        setFixedExpenseStorageFilePath(newUserPrefs.getFixedExpenseStorageFilePath());
        setPackingListStorageFilePath(newUserPrefs.getPackingListStorageFilePath());
    }

    @Override
    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    @Override
    public Path getAddressBookFilePath() {
        return addressBookFilePath;
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.addressBookFilePath = addressBookFilePath;
    }

    @Override
    public Path getTransportBookingStorageFilePath() {
        return transportBookingStorageFilePath;
    }

    public void setTransportBookingStorageFilePath(Path transportBookingStorageFilePath) {
        requireNonNull(transportBookingStorageFilePath);
        this.transportBookingStorageFilePath = transportBookingStorageFilePath;
    }

    @Override
    public Path getFixedExpenseStorageFilePath() {
        return fixedExpenseStorageFilePath;
    }

    public void setFixedExpenseStorageFilePath (Path fixedExpenseStorageFilePath) {
        requireNonNull(fixedExpenseStorageFilePath);
        this.fixedExpenseStorageFilePath = fixedExpenseStorageFilePath;
    }

    @Override
    public Path getActivityManagerStorageFilePath() {
        return activityManagerStorageFilePath;
    }

    public void setActivityManagerStorageFilePath (Path activityManagerStorageFilePath) {
        requireNonNull(activityManagerStorageFilePath);
        this.activityManagerStorageFilePath = activityManagerStorageFilePath;
    }

    @Override
    public Path getAccommodationBookingStorageFilePath() {
        return accommodationBookingStorageFilePath;
    }

    public void setAccommodationBookingStorageFilePath(Path accommodationBookingStorageFilePath) {
        requireNonNull(accommodationBookingStorageFilePath);
        this.accommodationBookingStorageFilePath = accommodationBookingStorageFilePath;
    }

    @Override
    public Path getPackingListStorageFilePath() {
        return packingListStorageFilePath;
    }

    public void setPackingListStorageFilePath(Path packingListStorageFilePath) {
        requireNonNull(packingListStorageFilePath);
        this.packingListStorageFilePath = packingListStorageFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs otherUserPrefs = (UserPrefs) other;

        return guiSettings.equals(otherUserPrefs.guiSettings)
                && addressBookFilePath.equals(otherUserPrefs.addressBookFilePath)
                && transportBookingStorageFilePath.equals(otherUserPrefs.transportBookingStorageFilePath)
                && fixedExpenseStorageFilePath.equals(otherUserPrefs.fixedExpenseStorageFilePath)
                && activityManagerStorageFilePath.equals(otherUserPrefs.activityManagerStorageFilePath)
                && accommodationBookingStorageFilePath.equals(otherUserPrefs.accommodationBookingStorageFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, addressBookFilePath, transportBookingStorageFilePath,
                fixedExpenseStorageFilePath, accommodationBookingStorageFilePath, activityManagerStorageFilePath);
    }

    @Override
    public String toString() {
        return "Gui Settings : " + guiSettings
                + "\nLocal data file location : " + addressBookFilePath
                + "\nTransport Bookings data file location : " + transportBookingStorageFilePath
                + "\nFixed Expenses data file location : " + fixedExpenseStorageFilePath
                + "\nAccommodation Bookings data file location : " + accommodationBookingStorageFilePath
                + "\nActivities data file location: " + activityManagerStorageFilePath;
    }

}
