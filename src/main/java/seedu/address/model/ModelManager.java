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
import seedu.address.model.et.Et;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final EtBook etBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Et> filteredEts;



    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyEtBook etBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(etBook, userPrefs);

        logger.fine("Initializing with address book: " + etBook + " and user prefs " + userPrefs);

        this.etBook = new EtBook(etBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredEts = new FilteredList<Et>(this.etBook.getEtList());
    }

    public ModelManager() {
        this(new EtBook(), new UserPrefs());
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
    public void setEtBook(ReadOnlyEtBook addressBook) {
        this.etBook.resetData(etBook);
    }

    @Override
    public ReadOnlyEtBook getEtBook() {
        return etBook;
    }

    @Override
    public boolean hasEt(Et et) {
        requireNonNull(et);
        return etBook.hasEt(et);
    }

    @Override
    public void deleteEt(Et target) {
        etBook.removeEt(target);
    }

    @Override
    public void addEt(Et et) {
        etBook.addEt(et);
        updateFilteredEtList(PREDICATE_SHOW_ALL_ET);
    }

    @Override
    public void setEt(Et target, Et editedEt) {
        requireAllNonNull(target, editedEt);

        etBook.setEt(target, editedEt);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Et> getFilteredEtList() {
        return filteredEts;
    }

    @Override
    public void updateFilteredEtList(Predicate<Et> predicate) {
        requireNonNull(predicate);
        filteredEts.setPredicate(predicate);
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
        return etBook.equals(other.etBook)
                && userPrefs.equals(other.userPrefs)
                && filteredEts.equals(other.filteredEts);
    }

}
