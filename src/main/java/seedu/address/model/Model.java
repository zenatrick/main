package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.et.Et;

/**
 * The API of the Model component.
 * Place to add your method names,
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Et> PREDICATE_SHOW_ALL_ET = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces etBooks data with the data in {@code etBook}.
     */
    void setEtBook(ReadOnlyEtBook etBook);

    /**
     * Returns the EtBook
     */
    ReadOnlyEtBook getEtBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasEt(Et et);

    /**
     * Deletes the given et.
     * The et must exist in the Etbook.
     */
    void deleteEt(Et et);

    /**
     * Adds the given et.
     * {@code person} must not already exist in the Etbook.
     */
    void addEt(Et et);

    /**
     * Replaces the given Et {@code target} with {@code editedEt}.
     * {@code target} must exist in the Etbook.
     * The person identity of {@code editedEt} must not be the same as another existing person in the address book.
     */
    void setEt(Et target, Et editedEt);

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Et> getFilteredEtList();

    void updateFilteredEtList(Predicate<Et> predicate);


}
