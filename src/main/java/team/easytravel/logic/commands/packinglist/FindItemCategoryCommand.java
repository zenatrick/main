package team.easytravel.logic.commands.packinglist;

import static java.util.Objects.requireNonNull;

import team.easytravel.model.listmanagers.packinglistitem.ItemCategoryContainsKeywordsPredicate;
import team.easytravel.commons.core.Messages;
import team.easytravel.logic.commands.Command;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.model.Model;

/**
 * The type Find item category command.
 */
public class FindItemCategoryCommand extends Command {
    /**
     * The constant COMMAND_WORD.
     */
    public static final String COMMAND_WORD = "finditemcategory";

    /**
     * The constant MESSAGE_USAGE.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all items whose category contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " swimming clothes essentials";

    private final ItemCategoryContainsKeywordsPredicate predicate;

    /**
     * Instantiates a new Find item category command.
     *
     * @param predicate the predicate
     */
    public FindItemCategoryCommand(ItemCategoryContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPackingList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ITEMS_LISTED_OVERVIEW, model.getFilteredPackingList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindItemCategoryCommand // instanceof handles nulls
                && predicate.equals(((FindItemCategoryCommand) other).predicate)); // state check
    }
}
