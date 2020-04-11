package team.easytravel.logic.parser.activity;

import team.easytravel.commons.core.Messages;
import team.easytravel.logic.commands.activity.SortActivityCommand;
import team.easytravel.logic.commands.util.SortCommandOrder;
import team.easytravel.logic.parser.Parser;
import team.easytravel.logic.parser.ParserUtil;
import team.easytravel.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a SortActivityCommand.
 */
public class SortActivityCommandParser implements Parser<SortActivityCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortActivityCommand
     * and returns an SortActivityCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortActivityCommand parse(String args) throws ParseException {
        String[] sortParameters = args.trim().split("\\s+");
        if (sortParameters.length != 2) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    SortActivityCommand.MESSAGE_USAGE));
        }

        String parsedCriteria = ParserUtil.parseSortActivityCriteria(sortParameters[0]);
        SortCommandOrder parsedSortOrder = ParserUtil.parseSortOder(sortParameters[1]);
        return new SortActivityCommand(parsedSortOrder, parsedCriteria);
    }
}
