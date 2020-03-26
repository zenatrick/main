package seedu.address.logic.parser.fixedexpense;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.fixedexpense.EditFixedExpenseCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditFixedExpenseCommand.
 */
public class EditFixedExpenseCommandParser implements Parser<EditFixedExpenseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditFixedExpenseCommand
     * and returns an EditFixedExpenseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditFixedExpenseCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_AMOUNT, PREFIX_DESCRIPTION, PREFIX_CATEGORY);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditFixedExpenseCommand.MESSAGE_USAGE), pe);
        }

        EditFixedExpenseCommand.EditFixedExpenseDescriptor editFixedExpenseDescriptor =
                new EditFixedExpenseCommand.EditFixedExpenseDescriptor();

        if (argMultimap.getValue(PREFIX_AMOUNT).isPresent()) {
            editFixedExpenseDescriptor.setAmount(ParserUtil
                    .parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get()));
        }
        if (argMultimap.getValue(PREFIX_CATEGORY).isPresent()) {
            editFixedExpenseDescriptor.setFixedExpenseCategory(ParserUtil
                    .parseCategory(argMultimap.getValue(PREFIX_CATEGORY).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editFixedExpenseDescriptor.setDescription(ParserUtil
                    .parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }

        if (!editFixedExpenseDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditFixedExpenseCommand.MESSAGE_NOT_EDITED);
        }

        return new EditFixedExpenseCommand(index, editFixedExpenseDescriptor);
    }
}
