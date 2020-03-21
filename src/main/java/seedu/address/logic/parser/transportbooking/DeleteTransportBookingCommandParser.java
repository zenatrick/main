package seedu.address.logic.parser.transportbooking;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.transportbooking.DeleteTransportBookingCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteTransportBookingCommand object.
 */
public class DeleteTransportBookingCommandParser implements Parser<DeleteTransportBookingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTransportBookingCommand
     * and returns a DeleteTransportBookingCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTransportBookingCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteTransportBookingCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTransportBookingCommand.MESSAGE_USAGE), pe);
        }
    }

}
