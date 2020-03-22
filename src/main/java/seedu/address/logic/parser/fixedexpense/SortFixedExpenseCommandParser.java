package seedu.address.logic.parser.fixedexpense;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.fixedexpense.SortFixedExpenseCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

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
        try {
            if (args.length() < 1) { //The case where nothing is placed after sortexpense
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        SortFixedExpenseCommand.MESSAGE_USAGE));
            }
            String parseSortIdentifier = ParserUtil.parseSortIdentifier(args.substring(1, 2));
            String parseSortParameter = ParserUtil.parseSortParameters(args.substring(2).trim().toLowerCase());
            return new SortFixedExpenseCommand(Integer.parseInt(parseSortIdentifier), parseSortParameter);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SortFixedExpenseCommand.MESSAGE_USAGE), pe);
        }
    }

}
