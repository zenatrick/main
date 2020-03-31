package team.easytravel.logic.commands.transportbooking;

import static java.util.Objects.requireNonNull;
import static team.easytravel.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Comparator;
import java.util.List;

import team.easytravel.commons.core.Messages;
import team.easytravel.logic.commands.Command;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.model.Model;
import team.easytravel.model.listmanagers.transportbooking.TransportBooking;
import team.easytravel.model.trip.TripManager;

/**
 * Sorts your transport List according to ascending or descending amount.
 */
public class SortTransportBookingCommand extends Command {

    public static final String COMMAND_WORD = "sorttransport";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": sorts the transport identified by the command"
            + " asc or des in the displayed transport\n"
            + "asc sorts by ascending order while des sorts by descending order\n"
            + "Parameters : SORTIDENTIFIER (must be asc or des) "
            + "[mode] " + "[startlocation] [endlocation]\n"
            + "Example: " + COMMAND_WORD + " asc name";

    public static final String MESSAGE_SORT_TRANSPORT_SUCCESS = "Sorting of transport successful :)";

    public static final String SORT_DESCENDING = "des";
    public static final String MODE = "mode";
    public static final String STARTLOCATION = "startlocation";
    public static final String ENDLOCATION = "endlocation";

    private final String sortIdentifier;
    private final String sortParameter;

    public SortTransportBookingCommand(String sortIdentifier, String sortParameter) {
        this.sortIdentifier = sortIdentifier;
        this.sortParameter = sortParameter;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTrip()) {
            throw new CommandException(TripManager.MESSAGE_ERROR_NO_TRIP);
        }

        List<TransportBooking> lastShownList = model.getFilteredTransportBookingList();

        if (lastShownList.size() < 1) {
            throw new CommandException(Messages.MESSAGE_INVALID_EMPTY_TRANSPORT_LIST);
        }

        switch (sortParameter) {
        case "mode":
            if (sortIdentifier.equals("des")) {
                model.sortTransportList((x, y) -> y.getMode().toString().compareTo(
                        x.getMode().toString()
                ));
                return new CommandResult(MESSAGE_SORT_TRANSPORT_SUCCESS);

            } else {
                model.sortTransportList(Comparator.comparing(x -> x.getMode().toString()));
                return new CommandResult(MESSAGE_SORT_TRANSPORT_SUCCESS);
            }
        case "startlocation":
            if (sortIdentifier.equals("des")) {
                model.sortTransportList((x, y) -> y.getStartLocation().toString().compareTo(
                        x.getStartLocation().toString()));
                return new CommandResult(MESSAGE_SORT_TRANSPORT_SUCCESS);

            } else {
                model.sortTransportList(Comparator.comparing(x -> x.getStartLocation().toString()));
                return new CommandResult(MESSAGE_SORT_TRANSPORT_SUCCESS);
            }

        case "endlocation":
            if (sortIdentifier.equals("des")) {
                model.sortTransportList((x, y) -> y.getEndLocation().toString().compareTo(
                        x.getEndLocation().toString()));
                return new CommandResult(MESSAGE_SORT_TRANSPORT_SUCCESS);

            } else {
                model.sortTransportList(Comparator.comparing(x -> x.getEndLocation().toString()));
                return new CommandResult(MESSAGE_SORT_TRANSPORT_SUCCESS);
            }

        default:
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SortTransportBookingCommand.MESSAGE_USAGE));
        }

    }
}
