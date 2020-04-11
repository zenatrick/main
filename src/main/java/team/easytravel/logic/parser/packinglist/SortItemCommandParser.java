package team.easytravel.logic.parser.packinglist;

import team.easytravel.commons.core.Messages;
import team.easytravel.logic.commands.packinglist.SortItemCommand;
import team.easytravel.logic.commands.util.SortCommandOrder;
import team.easytravel.logic.parser.Parser;
import team.easytravel.logic.parser.ParserUtil;
import team.easytravel.logic.parser.exceptions.ParseException;

/**
 * The type Sort item parser.
 */
public class SortItemCommandParser implements Parser<SortItemCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortItemCommand
     * and returns an SortItemCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortItemCommand parse(String args) throws ParseException {
        String[] sortParameters = args.trim().split("\\s+");
        if (sortParameters.length != 2) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    SortItemCommand.MESSAGE_USAGE));
        }

        String parsedCriteria = ParserUtil.parseSortPackingListItemCriteria(sortParameters[0]);
        SortCommandOrder parsedSortOrder = ParserUtil.parseSortOder(sortParameters[1]);
        return new SortItemCommand(parsedSortOrder, parsedCriteria);
    }
}
