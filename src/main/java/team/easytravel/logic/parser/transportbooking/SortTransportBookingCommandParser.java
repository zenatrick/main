package team.easytravel.logic.parser.transportbooking;

import team.easytravel.commons.core.Messages;
import team.easytravel.logic.commands.transportbooking.SortTransportBookingCommand;
import team.easytravel.logic.commands.util.SortCommandOrder;
import team.easytravel.logic.parser.Parser;
import team.easytravel.logic.parser.ParserUtil;
import team.easytravel.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a SortTransportBookingCommand.
 */
public class SortTransportBookingCommandParser implements Parser<SortTransportBookingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortTransportBookingCommand
     * and returns an SortTransportBookingCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortTransportBookingCommand parse(String args) throws ParseException {
        String[] sortParameters = args.trim().split("\\s+");
        if (sortParameters.length != 2) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    SortTransportBookingCommand.MESSAGE_USAGE));
        }

        String parsedCriteria = ParserUtil.parseSortTransportCriteria(sortParameters[0]);
        SortCommandOrder parsedSortOrder = ParserUtil.parseSortOder(sortParameters[1]);
        return new SortTransportBookingCommand(parsedSortOrder, parsedCriteria);
    }
}
