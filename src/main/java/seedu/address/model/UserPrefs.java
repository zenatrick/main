package seedu.address.model;

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
    private Path fixedExpenseStorageFilePath = DEFAULT_FOLDER_PATH.resolve("fixedexpense.json");
    private Path activityManagerStorageFilePath = DEFAULT_FOLDER_PATH.resolve("activity.json");
    private Path accommodationBookingStorageFilePath = DEFAULT_FOLDER_PATH.resolve("accommodation.json");

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

    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getAddressBookFilePath() {
        return addressBookFilePath;
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.addressBookFilePath = addressBookFilePath;
    }

    public Path getTransportBookingStorageFilePath() {
        return transportBookingStorageFilePath;
    }

    public void setTransportBookingStorageFilePath(Path transportBookingStorageFilePath) {
        requireNonNull(transportBookingStorageFilePath);
        this.transportBookingStorageFilePath = transportBookingStorageFilePath;
    }

    public Path getFixedExpenseStorageFilePath() {
        return fixedExpenseStorageFilePath;
    }

    public void setFixedExpenseStorageFilePath (Path fixedExpenseStorageFilePath) {
        requireNonNull(fixedExpenseStorageFilePath);
        this.fixedExpenseStorageFilePath = fixedExpenseStorageFilePath;
    }

    public Path getActivityManagerStorageFilePath() {
        return activityManagerStorageFilePath;
    }

    public void setActivityManagerStorageFilePath (Path activityManagerStorageFilePath) {
        requireNonNull(activityManagerStorageFilePath);
        this.activityManagerStorageFilePath = activityManagerStorageFilePath;
    }

    public Path getAccommodationBookingStorageFilePath() {
        return accommodationBookingStorageFilePath;
    }

    public void setAccommodationBookingStorageFilePath(Path accommodationBookingStorageFilePath) {
        requireNonNull(accommodationBookingStorageFilePath);
        this.accommodationBookingStorageFilePath = accommodationBookingStorageFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && addressBookFilePath.equals(o.addressBookFilePath)
                && transportBookingStorageFilePath.equals(o.transportBookingStorageFilePath)
                && fixedExpenseStorageFilePath.equals(o.fixedExpenseStorageFilePath)
                && activityManagerStorageFilePath.equals(o.activityManagerStorageFilePath)
                && accommodationBookingStorageFilePath.equals(o.accommodationBookingStorageFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, addressBookFilePath, transportBookingStorageFilePath,
                fixedExpenseStorageFilePath, accommodationBookingStorageFilePath);
    }

    @Override
    public String toString() {
        return "Gui Settings : " + guiSettings
                + "\nLocal data file location : " + addressBookFilePath
                + "\nTransport Bookings data file location : " + transportBookingStorageFilePath
                + "\nFixed Expense data file location : " + fixedExpenseStorageFilePath
                + "\nAccommodation Bookings data file location : " + accommodationBookingStorageFilePath;
    }

}
