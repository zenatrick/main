package team.easytravel.logic.parser.accommodationbooking;

import team.easytravel.commons.core.Messages;
import team.easytravel.logic.commands.accommodationbooking.SortAccommodationBookingCommand;
import team.easytravel.logic.commands.util.SortCommandOrder;
import team.easytravel.logic.parser.Parser;
import team.easytravel.logic.parser.ParserUtil;
import team.easytravel.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a SortAccommodationBookingCommand.
 */
public class SortAccommodationBookingParser implements Parser<SortAccommodationBookingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortAccommodationBookingCommand
     * and returns an SortAccommodationBookingCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortAccommodationBookingCommand parse(String args) throws ParseException {
        String[] sortParameters = args.trim().split("\\s+");
        if (sortParameters.length != 2) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    SortAccommodationBookingCommand.MESSAGE_USAGE));
        }

        String parsedCriteria = ParserUtil.parseSortAccommodationCriteria(sortParameters[0]);
        SortCommandOrder parsedSortOrder = ParserUtil.parseSortOder(sortParameters[1]);
        return new SortAccommodationBookingCommand(parsedSortOrder, parsedCriteria);
    }

}
