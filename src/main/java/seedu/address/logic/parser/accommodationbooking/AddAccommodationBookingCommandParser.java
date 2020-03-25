package seedu.address.logic.parser.accommodationbooking;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACCOMMODATION_END_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACCOMMODATION_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACCOMMODATION_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACCOMMODATION_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACCOMMODATION_START_DAY;

import java.util.stream.Stream;

import seedu.address.logic.commands.accommodationbooking.AddAccommodationBookingCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.listmanagers.accommodationbooking.AccommodationBooking;
import seedu.address.model.listmanagers.accommodationbooking.AccommodationName;
import seedu.address.model.listmanagers.accommodationbooking.Day;
import seedu.address.model.listmanagers.accommodationbooking.Remark;
import seedu.address.model.util.attributes.Location;

/**
 * Parses input arguments and creates a new AddAccommodationBooking object
 */
public class AddAccommodationBookingCommandParser implements Parser<AddAccommodationBookingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddAccommodationBookingCommand
     * and returns an AddAccommodationBookingCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAccommodationBookingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ACCOMMODATION_NAME, PREFIX_ACCOMMODATION_LOCATION,
                        PREFIX_ACCOMMODATION_START_DAY, PREFIX_ACCOMMODATION_END_DAY, PREFIX_ACCOMMODATION_REMARK);

        if (!arePrefixesPresent(argMultimap, PREFIX_ACCOMMODATION_NAME, PREFIX_ACCOMMODATION_LOCATION,
                PREFIX_ACCOMMODATION_START_DAY, PREFIX_ACCOMMODATION_END_DAY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAccommodationBookingCommand.MESSAGE_USAGE));
        }

        AccommodationName accommodationName = ParserUtil.parseAccommodationName(argMultimap
                .getValue(PREFIX_ACCOMMODATION_NAME).get());
        Location accommodationLocation = ParserUtil.parseLocation(argMultimap
                .getValue(PREFIX_ACCOMMODATION_LOCATION).get());
        Day startDay = ParserUtil.parseDay(argMultimap.getValue(PREFIX_ACCOMMODATION_START_DAY).get());
        Day endDay = ParserUtil.parseDay(argMultimap.getValue(PREFIX_ACCOMMODATION_END_DAY).get());

        Remark remark;
        if (argMultimap.getValue(PREFIX_ACCOMMODATION_REMARK).isPresent()) {
            remark = ParserUtil.parseRemark(argMultimap.getValue(PREFIX_ACCOMMODATION_REMARK).get());
        } else {
            remark = new Remark(" ");
        }

        try {
            AccommodationBooking accommodationBooking = new AccommodationBooking(accommodationName,
                    accommodationLocation, startDay, endDay, remark);
            return new AddAccommodationBookingCommand(accommodationBooking);
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage());
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
