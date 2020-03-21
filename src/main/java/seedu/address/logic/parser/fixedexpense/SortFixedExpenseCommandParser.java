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

    public SortFixedExpenseCommand parse(String args) throws ParseException {
        try {
            String highOrLow = ParserUtil.parsehighOrLow(args);
            return new SortFixedExpenseCommand(highOrLow);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortFixedExpenseCommand.MESSAGE_USAGE), pe);
        }
    }

}
