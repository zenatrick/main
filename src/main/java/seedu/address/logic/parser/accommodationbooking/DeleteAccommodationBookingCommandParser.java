package seedu.address.logic.parser.accommodationbooking;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.accommodationbooking.DeleteAccommodationBookingCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteAccommodationBookingCommand object.
 */
public class DeleteAccommodationBookingCommandParser implements Parser<DeleteAccommodationBookingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteAccommodationBookingCommand
     * and returns a DeleteAccommodationBookingCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteAccommodationBookingCommand parse(String userInput) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(userInput);
            return new DeleteAccommodationBookingCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAccommodationBookingCommand.MESSAGE_USAGE), pe);
        }
    }

}
