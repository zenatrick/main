package team.easytravel.logic.commands.packinglist;

import static java.util.Objects.requireNonNull;

import java.util.List;

import team.easytravel.commons.core.Messages;
import team.easytravel.commons.core.index.Index;
import team.easytravel.logic.commands.Command;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.model.Model;
import team.easytravel.model.listmanagers.packinglistitem.PackingListItem;

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
