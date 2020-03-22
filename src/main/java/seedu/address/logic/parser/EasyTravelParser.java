package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.accommodationbooking.AddAccommodationBookingCommand;
import seedu.address.logic.commands.accommodationbooking.ClearAccommodationBookingCommand;
import seedu.address.logic.commands.accommodationbooking.DeleteAccommodationBookingCommand;
import seedu.address.logic.commands.accommodationbooking.EditAccommodationBookingCommand;
import seedu.address.logic.commands.activity.AddActivityCommand;
import seedu.address.logic.commands.activity.ClearActivityCommand;
import seedu.address.logic.commands.activity.DeleteActivityCommand;
import seedu.address.logic.commands.activity.EditActivityCommand;
import seedu.address.logic.commands.fixedexpense.AddFixedExpenseCommand;
import seedu.address.logic.commands.fixedexpense.ClearFixedExpenseCommand;
import seedu.address.logic.commands.fixedexpense.DeleteFixedExpenseCommand;
import seedu.address.logic.commands.fixedexpense.EditFixedExpenseCommand;
import seedu.address.logic.commands.fixedexpense.SortFixedExpenseCommand;
import seedu.address.logic.commands.packinglist.AddItemCommand;
import seedu.address.logic.commands.packinglist.CheckItemCommand;
import seedu.address.logic.commands.packinglist.DeleteItemCommand;
import seedu.address.logic.commands.packinglist.EditItemCommand;
import seedu.address.logic.commands.packinglist.UncheckItemCommand;
import seedu.address.logic.commands.transportbooking.AddTransportBookingCommand;
import seedu.address.logic.commands.transportbooking.ClearTransportBookingCommand;
import seedu.address.logic.commands.transportbooking.DeleteTransportBookingCommand;
import seedu.address.logic.commands.transportbooking.EditTransportBookingCommand;
import seedu.address.logic.parser.accommodationbooking.AddAccommodationBookingCommandParser;
import seedu.address.logic.parser.accommodationbooking.DeleteAccommodationBookingCommandParser;
import seedu.address.logic.parser.accommodationbooking.EditAccommodationBookingCommandParser;
import seedu.address.logic.parser.activity.AddActivityParser;
import seedu.address.logic.parser.activity.DeleteActivityParser;
import seedu.address.logic.parser.activity.EditActivityParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.fixedexpense.AddFixedExpenseParser;
import seedu.address.logic.parser.fixedexpense.DeleteFixedExpenseCommandParser;
import seedu.address.logic.parser.fixedexpense.EditFixedExpenseParser;
import seedu.address.logic.parser.fixedexpense.SortFixedExpenseCommandParser;
import seedu.address.logic.parser.packinglist.AddItemParser;
import seedu.address.logic.parser.packinglist.CheckItemParser;
import seedu.address.logic.parser.packinglist.DeleteItemParser;
import seedu.address.logic.parser.packinglist.EditItemParser;
import seedu.address.logic.parser.packinglist.UncheckItemParser;
import seedu.address.logic.parser.transportbooking.AddTransportBookingCommandParser;
import seedu.address.logic.parser.transportbooking.DeleteTransportBookingCommandParser;
import seedu.address.logic.parser.transportbooking.EditTransportBookingCommandParser;

/**
 * Parses user input.
 */
public class EasyTravelParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {
        // Address Book Commands
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        // Fixed Expense Commands
        case AddFixedExpenseCommand.COMMAND_WORD:
            return new AddFixedExpenseParser().parse(arguments);

        case ClearFixedExpenseCommand.COMMAND_WORD:
            return new ClearFixedExpenseCommand();

        case DeleteFixedExpenseCommand.COMMAND_WORD:
            return new DeleteFixedExpenseCommandParser().parse(arguments);

        case EditFixedExpenseCommand.COMMAND_WORD:
            return new EditFixedExpenseParser().parse(arguments);

        case SortFixedExpenseCommand.COMMAND_WORD:
            return new SortFixedExpenseCommandParser().parse(arguments);

        // Transport Booking Commands
        case AddTransportBookingCommand.COMMAND_WORD:
            return new AddTransportBookingCommandParser().parse(arguments);

        case EditTransportBookingCommand.COMMAND_WORD:
            return new EditTransportBookingCommandParser().parse(arguments);

        case DeleteTransportBookingCommand.COMMAND_WORD:
            return new DeleteTransportBookingCommandParser().parse(arguments);

        case ClearTransportBookingCommand.COMMAND_WORD:
            return new ClearTransportBookingCommand();

        // Packing List Commands
        case AddItemCommand.COMMAND_WORD:
            return new AddItemParser().parse(arguments);

        case CheckItemCommand.COMMAND_WORD:
            return new CheckItemParser().parse(arguments);

        case DeleteItemCommand.COMMAND_WORD:
            return new DeleteItemParser().parse(arguments);

        case EditItemCommand.COMMAND_WORD:
            return new EditItemParser().parse(arguments);

        case UncheckItemCommand.COMMAND_WORD:
            return new UncheckItemParser().parse(arguments);

        // Activity Manager Command
        case AddActivityCommand.COMMAND_WORD:
            return new AddActivityParser().parse(arguments);

        case DeleteActivityCommand.COMMAND_WORD:
            return new DeleteActivityParser().parse(arguments);

        case ClearActivityCommand.COMMAND_WORD:
            return new ClearActivityCommand();

        case EditActivityCommand.COMMAND_WORD:
            return new EditActivityParser().parse(arguments);

        case AddAccommodationBookingCommand.COMMAND_WORD:
            return new AddAccommodationBookingCommandParser().parse(arguments);

        case ClearAccommodationBookingCommand.COMMAND_WORD:
            return new ClearAccommodationBookingCommand();

        case DeleteAccommodationBookingCommand.COMMAND_WORD:
            return new DeleteAccommodationBookingCommandParser().parse(arguments);

        case EditAccommodationBookingCommand.COMMAND_WORD:
            return new EditAccommodationBookingCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
