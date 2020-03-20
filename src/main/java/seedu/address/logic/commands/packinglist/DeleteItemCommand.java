package seedu.address.logic.commands.packinglist;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.listmanagers.packinglistitem.PackingListItem;

/**
 * The type Delete item command.
 */
public class DeleteItemCommand extends Command {
    /**
     * The constant COMMAND_WORD.
     */
    public static final String COMMAND_WORD = "deleteitem";

    /**
     * The constant MESSAGE_USAGE.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the item identified by the index number used in the displayed packing list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    /**
     * The constant MESSAGE_DELETE_ITEM_SUCCESS.
     */
    public static final String MESSAGE_DELETE_ITEM_SUCCESS = "Deleted Item: %1$s";

    private final Index targetIndex;

    /**
     * Instantiates a new Delete item command.
     *
     * @param targetIndex the target index
     */
    public DeleteItemCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<PackingListItem> lastShownList = model.getFilteredPackingList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
        }

        PackingListItem packingListItemToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePackingListItem(packingListItemToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_ITEM_SUCCESS, packingListItemToDelete));
    }
}
