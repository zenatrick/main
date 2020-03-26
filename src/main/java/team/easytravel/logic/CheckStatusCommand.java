package team.easytravel.logic;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACTIVITY_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.listmanagers.AccommodationBookingManager;
import seedu.address.model.listmanagers.ActivityManager;
import seedu.address.model.listmanagers.FixedExpenseManager;
import seedu.address.model.listmanagers.PackingListManager;
import seedu.address.model.listmanagers.accommodationbooking.AccommodationBooking;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class CheckStatusCommand extends Command {

    public static final String COMMAND_WORD = "checkstatus";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Check the preparedness of trip. "
            + "Example: " + COMMAND_WORD + " ";

    public static final String MESSAGE_SUCCESS = "You are prepared, everything has been checked";
    public static final String MESSAGE_UNREADY = "You are not %d%% ready for the trip";

    private final FixedExpenseManager fixedExpenseManager;
    private final PackingListManager packingListManager;
    private final AccommodationBookingManager accommodationBookingManager;


    /**
     * Creates an CheckStatusCommand to check {@code Person}
     */
    public CheckStatusCommand(FixedExpenseManager fixedExpenseManager,
                              PackingListManager packingListManager,
                              AccommodationBookingManager accommodationBookingManager) {
        requireNonNull(fixedExpenseManager);
        requireNonNull(packingListManager);
        requireNonNull(accommodationBookingManager);
        this.fixedExpenseManager = fixedExpenseManager;
        this.packingListManager = packingListManager;
        this.accommodationBookingManager = accommodationBookingManager;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.isTripPrepared(fixedExpenseManager, packingListManager, accommodationBookingManager)) {

            return new CommandResult(String.format(MESSAGE_UNREADY));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

}
