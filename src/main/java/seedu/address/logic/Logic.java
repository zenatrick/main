package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.listmanagers.accommodationbooking.AccommodationBooking;
import seedu.address.model.listmanagers.activity.Activity;
import seedu.address.model.listmanagers.fixedexpense.FixedExpense;
import seedu.address.model.listmanagers.packinglistitem.PackingListItem;
import seedu.address.model.listmanagers.transportbooking.TransportBooking;
import seedu.address.model.person.Person;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see seedu.address.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns an unmodifiable view of the filtered list of persons
     */
    ObservableList<Person> getFilteredPersonList();

    ///**
    // * Returns an unmodifiable view of the current planning status.
    // *
    // * @return the unmodifiable view of the current planning status.
    // */
    //SimpleStringProperty getPlanningStatus();

    /**
     * Returns an unmodifiable view of the filtered list of activities.
     *
     * @return the unmodifiable view of the filtered list of activities.
     */
    ObservableList<Activity> getFilteredActivityList();

    /**
     * Returns an unmodifiable view of the filtered list of transport bookings.
     *
     * @return the unmodifiable view of the filtered list of transport bookings.
     */
    ObservableList<TransportBooking> getFilteredTransportBookingList();

    /**
     * Returns an unmodifiable view of the filtered list of accommodation bookings.
     *
     * @return the unmodifiable view of the filtered list of accommodation bookings.
     */
    ObservableList<AccommodationBooking> getFilteredAccommodationBookingList();

    /**
     * Returns an unmodifiable view of the filtered list of fixed expenses.
     *
     * @return the unmodifiable view of the filtered list of fixed expenses.
     */
    ObservableList<FixedExpense> getFilteredFixedExpenseList();

    /**
     * Returns an unmodifiable view of the filtered packing list.
     *
     * @return the unmodifiable view of the filtered packing list.
     */
    ObservableList<PackingListItem> getFilteredPackingList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
