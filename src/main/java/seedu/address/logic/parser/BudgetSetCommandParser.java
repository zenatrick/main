package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SET_BUDGET;

import seedu.address.logic.commands.BudgetSetCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.budget.Budget;

/**
 * BudgetCommandParser parses the input from user.
 * So it gets the AMOUNT from the user.
 */

public class BudgetSetCommandParser implements Parser<BudgetSetCommand> {
    public static final String MESSAGE_INVALID_NUMBER = "You have entered an invalid number";

    @Override
    public BudgetSetCommand parse(String userInput) throws ParseException {
        // Exception handled in Account through Budget
        requireNonNull(userInput);
        String amount;
        ArgumentMultimap argumentMultimap;

        // Means the user wants to set a new Budget.
        argumentMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_SET_BUDGET);
        try {
            amount = argumentMultimap.getValue(PREFIX_SET_BUDGET).orElse("");
            if (Integer.parseInt(amount) < 0) {
                throw new ParseException(MESSAGE_INVALID_NUMBER);
            }
        } catch (ParseException e) {
            // To show the user that he has fucked up.
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BudgetSetCommand.MESSAGE_USAGE), e);

        }

        return new BudgetSetCommand(new Budget(amount));


    }


}
