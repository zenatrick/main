package seedu.address.logic.parser.packinglist;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.packinglist.FindItemCategoryCommand;
import seedu.address.logic.commands.packinglist.FindItemCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.listmanagers.packinglistitem.ItemCategoryContainsKeywordsPredicate;

/**
 * The type Find item category parser.
 */
public class FindItemCategoryParser implements Parser<FindItemCategoryCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindItemCategoryCommand
     * and returns a FindItemCategoryCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindItemCategoryCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindItemCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindItemCategoryCommand(new ItemCategoryContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
