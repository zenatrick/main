package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.stream.Stream;

import seedu.address.commons.core.time.DateTime;
import seedu.address.logic.commands.AddTransportBookingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.transportbooking.Mode;
import seedu.address.model.transportbooking.TransportBooking;
import seedu.address.model.util.attributes.Location;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddTransportBookingCommandParser implements Parser<AddTransportBookingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTransportBookingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODE, PREFIX_START_LOCATION, PREFIX_END_LOCATION,
                        PREFIX_START_TIME, PREFIX_END_TIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_MODE, PREFIX_START_LOCATION, PREFIX_END_LOCATION,
                PREFIX_START_TIME, PREFIX_END_TIME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTransportBookingCommand.MESSAGE_USAGE));
        }

        Mode mode = ParserUtil.parseMode(argMultimap.getValue(PREFIX_MODE).get());
        Location startLocation = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_START_LOCATION).get());
        Location endLocation = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_END_LOCATION).get());
        DateTime startDateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_START_TIME).get());
        DateTime endDateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_END_TIME).get());

        TransportBooking transportBooking = new TransportBooking(mode, startLocation, endLocation, startDateTime,
                endDateTime);

        return new AddTransportBookingCommand(transportBooking);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
