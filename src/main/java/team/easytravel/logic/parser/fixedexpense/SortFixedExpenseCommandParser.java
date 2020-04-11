package team.easytravel.logic.parser.fixedexpense;

import team.easytravel.commons.core.Messages;
import team.easytravel.logic.commands.fixedexpense.SortFixedExpenseCommand;
import team.easytravel.logic.commands.util.SortCommandOrder;
import team.easytravel.logic.parser.Parser;
import team.easytravel.logic.parser.ParserUtil;
import team.easytravel.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a SortFixedExpenseCommand.
 */
public class SortFixedExpenseCommandParser implements Parser<SortFixedExpenseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortFixedExpenseCommand
     * and returns an SortFixedExpenseCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortFixedExpenseCommand parse(String args) throws ParseException {
        String[] sortParameters = args.trim().split("\\s+");
        if (sortParameters.length != 2) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    SortFixedExpenseCommand.MESSAGE_USAGE));
        }

        String parsedCriteria = ParserUtil.parseSortFixedExpenseCriteria(sortParameters[0]);
        SortCommandOrder parsedSortOrder = ParserUtil.parseSortOder(sortParameters[1]);
        return new SortFixedExpenseCommand(parsedSortOrder, parsedCriteria);
    }

}
