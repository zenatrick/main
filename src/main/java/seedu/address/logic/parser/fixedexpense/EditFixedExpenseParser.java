package seedu.address.logic.parser.fixedexpense;

import static java.util.Objects.requireNonNull;

import com.sun.jdi.connect.Connector;

import seedu.address.logic.commands.fixedexpense.EditFixedExpenseCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

public class EditFixedExpenseParser implements Parser<EditFixedExpenseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public EditFixedExpenseCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_AMOUNT, PREFIX_DESCRIPTION, PREFIX_)
    }
}
