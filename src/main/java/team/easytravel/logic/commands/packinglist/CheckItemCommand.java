package team.easytravel.logic.commands.packinglist;

import static java.util.Objects.requireNonNull;
import static team.easytravel.model.Model.PREDICATE_SHOW_ALL_PACKING_LIST_ITEMS;

import java.util.List;

import team.easytravel.commons.core.Messages;
import team.easytravel.commons.core.index.Index;
import team.easytravel.logic.commands.Command;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.model.Model;
import team.easytravel.model.listmanagers.packinglistitem.PackingListItem;
import team.easytravel.model.trip.TripManager;

/**
 * Marks a packing list item as checked.
 */
public class CheckItemCommand extends Command {
    public static final String COMMAND_WORD = "checkitem";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the item identified by the index number used in the displayed packing list as packed.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_PACKED_ITEM_SUCCESS = "Packed Item: %1$s";

    private final Index index;

    /**
     * Instantiates a new Check item command.
     *
     * @param index of the Item in the filtered Item list to check
     */
    public CheckItemCommand(Index index) {
        requireNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTrip()) {
            throw new CommandException(TripManager.MESSAGE_ERROR_NO_TRIP);
        }

        List<PackingListItem> lastShownList = model.getFilteredPackingList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
        }

        PackingListItem itemToCheck = lastShownList.get(index.getZeroBased());
        PackingListItem checkedItem = new PackingListItem(itemToCheck.getItemName(), itemToCheck.getQuantity(),
                itemToCheck.getItemCategory(), true);

        model.setPackingListItem(itemToCheck, checkedItem);
        model.updateFilteredPackingList(PREDICATE_SHOW_ALL_PACKING_LIST_ITEMS);
        return new CommandResult(String.format(MESSAGE_PACKED_ITEM_SUCCESS, checkedItem));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CheckItemCommand // instanceof handles nulls
                && index.equals(((CheckItemCommand) other).index)); // state check
    }
}
