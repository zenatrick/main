package seedu.address.logic.parser.fixedexpense;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.accommodationbooking.DeleteAccommodationBookingCommand;
import seedu.address.logic.commands.fixedexpense.DeleteFixedExpenseCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteFixedExpenseCommand.
 */
public class DeleteFixedExpenseCommandParser implements Parser<DeleteFixedExpenseCommand> {

    @Override
    public DeleteFixedExpenseCommand parse(String userInput) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(userInput);
            return new DeleteFixedExpenseCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteAccommodationBookingCommand.MESSAGE_USAGE), pe);
        }
    }
}
