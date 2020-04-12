package team.easytravel.logic.parser.fixedexpense;

import static java.util.Objects.requireNonNull;
import static team.easytravel.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_EXPENSE_AMOUNT;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_EXPENSE_CATEGORY;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_EXPENSE_CURRENCY;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_EXPENSE_DESCRIPTION;

import team.easytravel.commons.core.index.Index;
import team.easytravel.logic.commands.fixedexpense.EditFixedExpenseCommand;
import team.easytravel.logic.parser.ArgumentMultimap;
import team.easytravel.logic.parser.ArgumentTokenizer;
import team.easytravel.logic.parser.Parser;
import team.easytravel.logic.parser.ParserUtil;
import team.easytravel.logic.parser.exceptions.ParseException;
import team.easytravel.model.listmanagers.fixedexpense.Amount;

/**
 * Parses input arguments and creates a new EditFixedExpenseCommand.
 */
public class EditFixedExpenseCommandParser implements Parser<EditFixedExpenseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditFixedExpenseCommand
     * and returns an EditFixedExpenseCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditFixedExpenseCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_EXPENSE_AMOUNT, PREFIX_EXPENSE_CURRENCY,
                PREFIX_EXPENSE_DESCRIPTION, PREFIX_EXPENSE_CATEGORY);

        Index index;
        boolean isOverseasAmount = false;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditFixedExpenseCommand.MESSAGE_USAGE), pe);
        }

        EditFixedExpenseCommand.EditFixedExpenseDescriptor editFixedExpenseDescriptor =
                new EditFixedExpenseCommand.EditFixedExpenseDescriptor();

        if (argMultimap.getValue(PREFIX_EXPENSE_AMOUNT).isPresent()) {

            if (argMultimap.getValue(PREFIX_EXPENSE_CURRENCY).isEmpty()) {
                throw new ParseException(EditFixedExpenseCommand.MESSAGE_CURRENCY_NOT_PRESENT);

            } else if (argMultimap.getValue(PREFIX_EXPENSE_CURRENCY).get().equalsIgnoreCase(Amount.CURRENCY_SGD)) {
                editFixedExpenseDescriptor.setAmount(ParserUtil
                        .parseAmount(argMultimap.getValue(PREFIX_EXPENSE_AMOUNT).get()));
            } else if (argMultimap.getValue(PREFIX_EXPENSE_CURRENCY).get().equalsIgnoreCase(Amount.CURRENCY_OTHER)) {
                isOverseasAmount = true;
                editFixedExpenseDescriptor.setAmount(ParserUtil
                        .parseAmount(argMultimap.getValue(PREFIX_EXPENSE_AMOUNT).get()));
            } else {
                throw new ParseException(EditFixedExpenseCommand.MESSAGE_INVALID_CURRENCY);
            }


        }
        if (argMultimap.getValue(PREFIX_EXPENSE_CATEGORY).isPresent()) {
            editFixedExpenseDescriptor.setFixedExpenseCategory(ParserUtil
                    .parseFixedExpenseCategory(argMultimap.getValue(PREFIX_EXPENSE_CATEGORY).get()));
        }
        if (argMultimap.getValue(PREFIX_EXPENSE_DESCRIPTION).isPresent()) {
            editFixedExpenseDescriptor.setDescription(ParserUtil
                    .parseDescription(argMultimap.getValue(PREFIX_EXPENSE_DESCRIPTION).get()));
        }

        if (!editFixedExpenseDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditFixedExpenseCommand.MESSAGE_NOT_EDITED);
        }

        return new EditFixedExpenseCommand(index, editFixedExpenseDescriptor, isOverseasAmount);
    }
}
