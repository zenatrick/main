package team.easytravel.logic.parser.packinglist;

import static team.easytravel.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import team.easytravel.logic.commands.packinglist.SortItemCommand;
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
        try {
            if (args.length() < 1) { //The case where nothing is placed after packing list item
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        SortItemCommand.MESSAGE_USAGE));
            }
            String parseSortItemIdentifier = ParserUtil.parseSortItemIdentifier(args.substring(1, 4));
            String parseSortItemParameter = ParserUtil.parseSortItemParameters(args.substring(4).trim().toLowerCase());

            return new SortItemCommand((parseSortItemIdentifier), parseSortItemParameter);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SortItemCommand.MESSAGE_USAGE), pe);
        }
    }
}
