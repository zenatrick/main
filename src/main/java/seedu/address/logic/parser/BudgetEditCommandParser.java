package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDIT_BUDGET;

import seedu.address.logic.commands.BudgetEditCommand;
import seedu.address.logic.commands.BudgetEditCommand.EditBudgetDescriptor;
import seedu.address.logic.commands.BudgetSetCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.budget.Budget;

/**
 * To edit the budget.
 */
public class BudgetEditCommandParser implements Parser<BudgetEditCommand> {

    public static final String MESSAGE_INVALID_NUMBER = "You have entered an invalid number";


    @Override
    public BudgetEditCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        String amount;
        ArgumentMultimap argumentMultimap;

        // Means the user wants to set a new Budget.
        argumentMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_EDIT_BUDGET);
        try {
            amount = argumentMultimap.getValue(PREFIX_EDIT_BUDGET).orElse("");
            if (Integer.parseInt(amount) < 0) {
                throw new ParseException(MESSAGE_INVALID_NUMBER);
            }
        } catch (ParseException e) {
            // To show the user that he has fucked up.
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BudgetSetCommand.MESSAGE_USAGE), e);
        }

        argumentMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_EDIT_BUDGET);
        EditBudgetDescriptor editBudgetDescriptor = new EditBudgetDescriptor();

        // If the argument budget is present
        if (argumentMultimap.getValue(PREFIX_EDIT_BUDGET).isPresent()) {
            editBudgetDescriptor.setBudget(new Budget(argumentMultimap.getValue(PREFIX_EDIT_BUDGET).get()));
        }
        return new BudgetEditCommand(editBudgetDescriptor);

    }
}
