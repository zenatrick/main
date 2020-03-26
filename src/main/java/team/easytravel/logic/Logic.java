package team.easytravel.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import team.easytravel.commons.core.GuiSettings;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.logic.parser.exceptions.ParseException;
import team.easytravel.model.listmanagers.accommodationbooking.AccommodationBooking;
import team.easytravel.model.listmanagers.activity.Activity;
import team.easytravel.model.listmanagers.fixedexpense.FixedExpense;
import team.easytravel.model.listmanagers.packinglistitem.PackingListItem;
import team.easytravel.model.listmanagers.transportbooking.TransportBooking;

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
     * Returns the user prefs' trip storage file path.
     */
    Path getEasyTravelStorageFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
