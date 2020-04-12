package team.easytravel.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import team.easytravel.commons.core.GuiSettings;
import team.easytravel.commons.core.LogsCenter;
import team.easytravel.logic.commands.Command;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.logic.commands.accommodationbooking.AddAccommodationBookingCommand;
import team.easytravel.logic.commands.accommodationbooking.ClearAccommodationBookingCommand;
import team.easytravel.logic.commands.accommodationbooking.DeleteAccommodationBookingCommand;
import team.easytravel.logic.commands.accommodationbooking.EditAccommodationBookingCommand;
import team.easytravel.logic.commands.accommodationbooking.ListAccommodationBookingCommand;
import team.easytravel.logic.commands.accommodationbooking.SortAccommodationBookingCommand;
import team.easytravel.logic.commands.activity.AddActivityCommand;
import team.easytravel.logic.commands.activity.ClearActivityCommand;
import team.easytravel.logic.commands.activity.DeleteActivityCommand;
import team.easytravel.logic.commands.activity.EditActivityCommand;
import team.easytravel.logic.commands.activity.FindActivityCommand;
import team.easytravel.logic.commands.activity.FindActivityTagCommand;
import team.easytravel.logic.commands.activity.ListActivityCommand;
import team.easytravel.logic.commands.activity.SortActivityCommand;
import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.logic.commands.fixedexpense.AddFixedExpenseCommand;
import team.easytravel.logic.commands.fixedexpense.ClearFixedExpenseCommand;
import team.easytravel.logic.commands.fixedexpense.DeleteFixedExpenseCommand;
import team.easytravel.logic.commands.fixedexpense.EditFixedExpenseCommand;
import team.easytravel.logic.commands.fixedexpense.ListFixedExpenseCommand;
import team.easytravel.logic.commands.fixedexpense.SortFixedExpenseCommand;
import team.easytravel.logic.commands.general.ExitCommand;
import team.easytravel.logic.commands.general.HelpCommand;
import team.easytravel.logic.commands.packinglist.AddItemCommand;
import team.easytravel.logic.commands.packinglist.AddPresetCommand;
import team.easytravel.logic.commands.packinglist.CheckItemCommand;
import team.easytravel.logic.commands.packinglist.ClearItemCommand;
import team.easytravel.logic.commands.packinglist.DeleteItemCommand;
import team.easytravel.logic.commands.packinglist.EditItemCommand;
import team.easytravel.logic.commands.packinglist.FindItemCategoryCommand;
import team.easytravel.logic.commands.packinglist.FindItemCommand;
import team.easytravel.logic.commands.packinglist.ListItemCommand;
import team.easytravel.logic.commands.packinglist.ListPresetCommand;
import team.easytravel.logic.commands.packinglist.SortItemCommand;
import team.easytravel.logic.commands.packinglist.UncheckItemCommand;
import team.easytravel.logic.commands.schedule.ListScheduleCommand;
import team.easytravel.logic.commands.schedule.ScheduleCommand;
import team.easytravel.logic.commands.schedule.UnscheduleCommand;
import team.easytravel.logic.commands.transportbooking.AddTransportBookingCommand;
import team.easytravel.logic.commands.transportbooking.ClearTransportBookingCommand;
import team.easytravel.logic.commands.transportbooking.DeleteTransportBookingCommand;
import team.easytravel.logic.commands.transportbooking.EditTransportBookingCommand;
import team.easytravel.logic.commands.transportbooking.ListTransportBookingCommand;
import team.easytravel.logic.commands.transportbooking.SortTransportBookingCommand;
import team.easytravel.logic.commands.trip.CheckStatusCommand;
import team.easytravel.logic.commands.trip.DeleteTripCommand;
import team.easytravel.logic.commands.trip.EditBudgetCommand;
import team.easytravel.logic.commands.trip.RenameCommand;
import team.easytravel.logic.commands.trip.SetTripCommand;
import team.easytravel.logic.parser.EasyTravelParser;
import team.easytravel.logic.parser.exceptions.ParseException;
import team.easytravel.model.Model;
import team.easytravel.model.listmanagers.accommodationbooking.AccommodationBooking;
import team.easytravel.model.listmanagers.activity.Activity;
import team.easytravel.model.listmanagers.fixedexpense.FixedExpense;
import team.easytravel.model.listmanagers.packinglistitem.PackingListItem;
import team.easytravel.model.listmanagers.transportbooking.TransportBooking;
import team.easytravel.model.trip.DayScheduleEntry;
import team.easytravel.model.trip.Trip;
import team.easytravel.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final EasyTravelParser easyTravelParser;

    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        easyTravelParser = new EasyTravelParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = easyTravelParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveTripManager(model.getTripManager());
            storage.saveAccommodationBookings(model.getAccommodationBookingManager());
            storage.saveFixedExpenses(model.getFixedExpenseManager());
            storage.saveTransportBookings(model.getTransportBookingManager());
            storage.saveActivityManager(model.getActivityManager());
            storage.savePackingList(model.getPackingListManager());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ObservableList<TransportBooking> getFilteredTransportBookingList() {
        return model.getFilteredTransportBookingList();
    }

    @Override
    public ObservableList<Activity> getFilteredActivityList() {
        return model.getFilteredActivityList();
    }

    @Override
    public ObservableList<FixedExpense> getFilteredFixedExpenseList() {
        return model.getFilteredFixedExpenseList();
    }

    @Override
    public ObservableList<PackingListItem> getFilteredPackingList() {
        return model.getFilteredPackingList();
    }

    @Override
    public ObservableList<AccommodationBooking> getFilteredAccommodationBookingList() {
        return model.getFilteredAccommodationBookingList();
    }

    @Override
    public List<ObservableList<DayScheduleEntry>> getScheduleList() {
        return model.getScheduleList();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public boolean hasTrip() {
        return model.hasTrip();
    }

    @Override
    public Trip getTrip() {
        return model.getTripManager().getTrip();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public ObservableList<String> getHelpQuestions() {
        ArrayList<String> questions = new ArrayList<>();

        // General
        questions.add(HelpCommand.MESSAGE_USAGE);
        questions.add(ExitCommand.MESSAGE_USAGE);

        // Trip Commands
        questions.add(SetTripCommand.MESSAGE_USAGE);
        questions.add(DeleteTripCommand.MESSAGE_USAGE);
        questions.add(RenameCommand.MESSAGE_USAGE);
        questions.add(EditBudgetCommand.MESSAGE_USAGE);
        questions.add(CheckStatusCommand.MESSAGE_USAGE);

        // Activity Commands
        questions.add(AddActivityCommand.MESSAGE_USAGE);
        questions.add(EditActivityCommand.MESSAGE_USAGE);
        questions.add(DeleteActivityCommand.MESSAGE_USAGE);
        questions.add(ClearActivityCommand.MESSAGE_USAGE);
        questions.add(FindActivityCommand.MESSAGE_USAGE);
        questions.add(FindActivityTagCommand.MESSAGE_USAGE);
        questions.add(SortActivityCommand.MESSAGE_USAGE);
        questions.add(ListActivityCommand.MESSAGE_USAGE);

        // Accommodation Booking Commands
        questions.add(AddAccommodationBookingCommand.MESSAGE_USAGE);
        questions.add(EditAccommodationBookingCommand.MESSAGE_USAGE);
        questions.add(DeleteAccommodationBookingCommand.MESSAGE_USAGE);
        questions.add(ClearAccommodationBookingCommand.MESSAGE_USAGE);
        questions.add(SortAccommodationBookingCommand.MESSAGE_USAGE);
        questions.add(ListAccommodationBookingCommand.MESSAGE_USAGE);

        // Transport Booking Commands
        questions.add(AddTransportBookingCommand.MESSAGE_USAGE);
        questions.add(EditTransportBookingCommand.MESSAGE_USAGE);
        questions.add(DeleteTransportBookingCommand.MESSAGE_USAGE);
        questions.add(ClearTransportBookingCommand.MESSAGE_USAGE);
        questions.add(SortTransportBookingCommand.MESSAGE_USAGE);
        questions.add(ListTransportBookingCommand.MESSAGE_USAGE);

        // Packing List Commands
        questions.add(AddItemCommand.MESSAGE_USAGE);
        questions.add(EditItemCommand.MESSAGE_USAGE);
        questions.add(DeleteItemCommand.MESSAGE_USAGE);
        questions.add(CheckItemCommand.MESSAGE_USAGE);
        questions.add(UncheckItemCommand.MESSAGE_USAGE);
        questions.add(ClearItemCommand.MESSAGE_USAGE);
        questions.add(FindItemCommand.MESSAGE_USAGE);
        questions.add(FindItemCategoryCommand.MESSAGE_USAGE);
        questions.add(SortItemCommand.MESSAGE_USAGE);
        questions.add(ListItemCommand.MESSAGE_USAGE);
        questions.add(ListPresetCommand.MESSAGE_USAGE);
        questions.add(AddPresetCommand.MESSAGE_USAGE);

        // Fixed Expense Command
        questions.add(AddFixedExpenseCommand.MESSAGE_USAGE);
        questions.add(EditFixedExpenseCommand.MESSAGE_USAGE);
        questions.add(DeleteFixedExpenseCommand.MESSAGE_USAGE);
        questions.add(ClearFixedExpenseCommand.MESSAGE_USAGE);
        questions.add(SortFixedExpenseCommand.MESSAGE_USAGE);
        questions.add(ListFixedExpenseCommand.MESSAGE_USAGE);

        // Schedule Command
        questions.add(ScheduleCommand.MESSAGE_USAGE);
        questions.add(UnscheduleCommand.MESSAGE_USAGE);
        questions.add(ListScheduleCommand.MESSAGE_USAGE);

        return FXCollections.observableArrayList(questions);
    }
}
