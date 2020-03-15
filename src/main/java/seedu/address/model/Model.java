package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.fixedexpense.FixedExpense;
import seedu.address.model.packinglistitem.PackingListItem;
import seedu.address.model.person.Person;
import seedu.address.model.transportbooking.TransportBooking;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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

    /**
     * Returns the AddressBook
     */
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

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    // ========== TransportBookingManager ==========

    /**
     * Returns true if a transport booking that is the same as {@code target} exists in the
     * TransportBookingManager.
     *
     * @param target the given transport booking.
     * @return true if the given transport booking already exist in the TransportBookingManager.
     */
    boolean hasTransportBooking(TransportBooking target);

    /**
     * Deletes the given transport booking.
     * The transport booking must exist in the TransportBookingManager.
     *
     * @param toDelete the given transport booking.
     */
    void deleteTransportBooking(TransportBooking toDelete);

    /**
     * Adds the given transport booking.
     * {@code toAdd} must not already exist in the TransportBookingManager.
     *
     * @param toAdd the given transport booking.
     */
    void addTransportBooking(TransportBooking toAdd);

    /**
     * Replaces the given transport booking {@code target} with {@code edited}.
     * {@code target} must exist in the TransportBookingManager.
     * {@code edited} must not be the same as another existing transport booking in the address book.
     *
     * @param target the given target transport booking.
     * @param edited the given edited transport booking.
     */
    void setTransportBooking(TransportBooking target, TransportBooking edited);

    /**
     * Returns an unmodifiable view of the filtered transport booking list
     */
    ObservableList<TransportBooking> getFilteredTransportBookingList();

    /**
     * Updates the filter of the filtered transport booking list to filter by the given {@code predicate}.
     *
     * @param predicate the given predicate.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTransportBookingList(Predicate<TransportBooking> predicate);

    // ========== FixedExpenseManager ==========

    /**
     * Returns true if a fixed expense that is the same as {@code target} exists in the
     * FixedExpenseManager.
     *
     * @param target the given fixed expense.
     * @return true if the given fixed expense already exist in the FixedExpenseManager.
     */
    boolean hasFixedExpense(FixedExpense target);

    /**
     * Deletes the given fixed expense.
     * The fixed expense must exist in the FixedExpenseManager.
     *
     * @param toDelete the given fixed expense.
     */
    void deleteFixedExpense(FixedExpense toDelete);

    /**
     * Adds the given fixed expense.
     * {@code toAdd} must not already exist in the FixedExpenseManager.
     *
     * @param toAdd the given fixed expense.
     */
    void addFixedExpense(FixedExpense toAdd);

    /**
     * Replaces the given fixed expense {@code target} with {@code edited}.
     * {@code target} must exist in the FixedExpenseManager.
     * {@code edited} must not be the same as another existing fixed expense in the address book.
     *
     * @param target the given target fixed expense.
     * @param edited the given edited fixed expense.
     */
    void setFixedExpense(FixedExpense target, FixedExpense edited);

    /**
     * Returns an unmodifiable view of the filtered fixed expense list
     */
    ObservableList<FixedExpense> getFilteredFixedExpenseList();

    /**
     * Updates the filter of the filtered fixed expense list to filter by the given {@code predicate}.
     *
     * @param predicate the given predicate.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredFixedExpenseList(Predicate<FixedExpense> predicate);

    // ========== PackingListManager ==========

    /**
     * Returns true if a packing list item that is the same as {@code target} exists in the
     * PackingListManager.
     *
     * @param target the given packing list item.
     * @return true if the given packing list item already exist in the PackingListManager.
     */
    boolean hasPackingListItem(PackingListItem target);

    /**
     * Deletes the given packing list item.
     * The packing list item must exist in the PackingListManager.
     *
     * @param toDelete the given packing list item.
     */
    void deletePackingListItem(PackingListItem toDelete);

    /**
     * Adds the given packing list item.
     * {@code toAdd} must not already exist in the PackingListManager.
     *
     * @param toAdd the given packing list item.
     */
    void addPackingListItem(PackingListItem toAdd);

    /**
     * Replaces the given packing list item {@code target} with {@code edited}.
     * {@code target} must exist in the PackingListManager.
     * {@code edited} must not be the same as another existing packing list item in the address book.
     *
     * @param target the given target packing list item.
     * @param edited the given edited packing list item.
     */
    void setPackingListItem(PackingListItem target, PackingListItem edited);

    /**
     * Returns an unmodifiable view of the filtered packing list item list
     */
    ObservableList<PackingListItem> getFilteredPackingList();

    /**
     * Updates the filter of the filtered packing list item list to filter by the given {@code predicate}.
     *
     * @param predicate the given predicate.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPackingList(Predicate<PackingListItem> predicate);
}
