package team.easytravel.logic;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import team.easytravel.commons.core.GuiSettings;
import team.easytravel.commons.core.LogsCenter;
import team.easytravel.logic.commands.Command;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.logic.parser.EasyTravelParser;
import team.easytravel.logic.parser.exceptions.ParseException;
import team.easytravel.model.Model;
import team.easytravel.model.listmanagers.accommodationbooking.AccommodationBooking;
import team.easytravel.model.listmanagers.activity.Activity;
import team.easytravel.model.listmanagers.fixedexpense.FixedExpense;
import team.easytravel.model.listmanagers.packinglistitem.PackingListItem;
import team.easytravel.model.listmanagers.transportbooking.TransportBooking;
import team.easytravel.model.trip.DayScheduleEntry;
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
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
