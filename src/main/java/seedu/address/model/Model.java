package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.budget.FixedCost;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<FixedCost> PREDICATE_SHOW_ALL_FIXED_COST = unused -> true;


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
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    //---------------- From FixedCostModel -------------------------------//

    /**
     * Replaces FixedCost book data with the data in {@code addressBook}.
     */
    void setFixedCostBook(ReadOnlyFixedCostBook fixedCostBook);

    /** Returns the FixedCostBook */
    ReadOnlyFixedCostBook getFixedCostBook();

    /**
     * Returns true if a FixedCost with the same identity as {@code FixedCost} exists in the address book.
     */
    boolean hasFixedCost(FixedCost fixedCost);

    /**
     * Deletes the given FixedCost.
     * The FixedCost must exist in the address book.
     */
    void deleteFixedCost(FixedCost target);

    /**
     * Adds the given FixedCost.
     * {@code FixedCost} must not already exist in the address book.
     */
    void addFixedCost(FixedCost fixedCost);

    /**
     * Replaces the given FixedCost {@code target} with {@code editedFixedCost}.
     * {@code target} must exist in the address book.
     * The FixedCost identity of {@code editedFixedCost} must not be the same as another existing FixedCost
     * in the address book.
     */
    void setFixedCost(FixedCost target, FixedCost editedFixedCost);

    /** Returns an unmodifiable view of the filtered FixedCost list */
    ObservableList<FixedCost> getFilteredFixedCostList();

    /**
     * Updates the filter of the filtered FixedCost list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredFixedCostList(Predicate<FixedCost> predicate);





}
