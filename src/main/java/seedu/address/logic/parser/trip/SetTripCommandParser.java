package seedu.address.logic.parser.trip;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRIP_BUDGET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRIP_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRIP_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRIP_TITLE;

import java.util.stream.Stream;

import seedu.address.commons.core.time.Date;
import seedu.address.logic.commands.trip.SetTripCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.trip.Budget;
import seedu.address.model.trip.Trip;
import seedu.address.model.util.attributes.Title;

/**
 * Parses input arguments and creates a new SetTripCommand object
 */
public class SetTripCommandParser implements Parser<SetTripCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SetTripCommand
     * and returns an SetTripCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetTripCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TRIP_TITLE, PREFIX_TRIP_BUDGET, PREFIX_TRIP_START_DATE,
                        PREFIX_TRIP_END_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_TRIP_TITLE, PREFIX_TRIP_BUDGET, PREFIX_TRIP_START_DATE,
                PREFIX_TRIP_END_DATE) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SetTripCommand.MESSAGE_USAGE));
        }

        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TRIP_TITLE).get());
        Budget budget = ParserUtil.parseBudget(argMultimap.getValue(PREFIX_TRIP_BUDGET).get());
        Date startDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_TRIP_START_DATE).get());
        Date endDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_TRIP_END_DATE).get());

        try {
            Trip trip = new Trip(title, startDate, endDate, budget);
            return new SetTripCommand(trip);
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
