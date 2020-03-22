package seedu.address.logic.parser.accommodationbooking;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACCOMMODATION_END_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACCOMMODATION_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACCOMMODATION_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACCOMMODATION_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACCOMMODATION_START_DAY;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.accommodationbooking.EditAccommodationBookingCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditAccommodationBookingCommand object
 */
public class EditAccommodationBookingCommandParser implements Parser<EditAccommodationBookingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditAccommodationBookingCommand
     * and returns an EditAccommodationBookingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditAccommodationBookingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ACCOMMODATION_NAME, PREFIX_ACCOMMODATION_LOCATION,
                        PREFIX_ACCOMMODATION_START_DAY, PREFIX_ACCOMMODATION_END_DAY, PREFIX_ACCOMMODATION_REMARK);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAccommodationBookingCommand.MESSAGE_USAGE), pe);
        }

        EditAccommodationBookingCommand.EditAccommodationBookingDescriptor editAccommodationBookingDescriptor =
                new EditAccommodationBookingCommand.EditAccommodationBookingDescriptor();

        if (argMultimap.getValue(PREFIX_ACCOMMODATION_NAME).isPresent()) {
            editAccommodationBookingDescriptor.setAccommodationName(ParserUtil.parseAccommodationName(argMultimap
                    .getValue(PREFIX_ACCOMMODATION_NAME).get()));
        }

        if (argMultimap.getValue(PREFIX_ACCOMMODATION_LOCATION).isPresent()) {
            editAccommodationBookingDescriptor.setLocation(ParserUtil.parseLocation(argMultimap
                    .getValue(PREFIX_ACCOMMODATION_LOCATION).get()));
        }

        if (argMultimap.getValue(PREFIX_ACCOMMODATION_START_DAY).isPresent()) {
            editAccommodationBookingDescriptor.setStartDay(ParserUtil.parseDay(argMultimap
                    .getValue(PREFIX_ACCOMMODATION_START_DAY).get()));
        }

        if (argMultimap.getValue(PREFIX_ACCOMMODATION_END_DAY).isPresent()) {
            editAccommodationBookingDescriptor.setEndDay(ParserUtil.parseDay(argMultimap
                    .getValue(PREFIX_ACCOMMODATION_END_DAY).get()));
        }

        if (argMultimap.getValue(PREFIX_ACCOMMODATION_REMARK).isPresent()) {
            editAccommodationBookingDescriptor.setRemark(ParserUtil.parseRemark(argMultimap
                    .getValue(PREFIX_ACCOMMODATION_REMARK).get()));
        }

        if (!editAccommodationBookingDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditAccommodationBookingCommand(index, editAccommodationBookingDescriptor);
    }

}
