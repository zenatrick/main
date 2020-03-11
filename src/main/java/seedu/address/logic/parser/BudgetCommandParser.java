package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDIT_BUDGET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SET_BUDGET;

import seedu.address.logic.commands.BudgetCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * BudgetCommandParser parses the input from user.
 * So it gets the AMOUNT from the user.
 */

public class BudgetCommandParser implements Parser<BudgetCommand> {

    @Override
    public BudgetCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        String amount;
        //TODO:Need further refinement.
        if (userInput.contains("set")) {
            ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_SET_BUDGET);
            amount = argumentMultimap.getValue(PREFIX_SET_BUDGET).orElse("");
        } else {
            //Means Edit for now.
            ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_EDIT_BUDGET);
            amount = argumentMultimap.getValue(PREFIX_EDIT_BUDGET).orElse("");
        }
        return new BudgetCommand(amount);

    }
}
