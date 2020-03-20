package seedu.address.logic.commands.packinglist;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PACKING_LIST_ITEMS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;

import seedu.address.model.Model;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.listmanagers.packinglistitem.ItemCategory;
import seedu.address.model.listmanagers.packinglistitem.ItemName;
import seedu.address.model.listmanagers.packinglistitem.PackingListItem;
import seedu.address.model.listmanagers.packinglistitem.Quantity;

/**
 * The type Check item command.
 */
public class CheckItemCommand extends Command {
    /**
     * The constant COMMAND_WORD.
     */
    public static final String COMMAND_WORD = "checkitem";

    /**
     * The constant MESSAGE_USAGE.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the item identified by the index number used in the displayed packing list as packed.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    /**
     * The constant MESSAGE_DELETE_ITEM_SUCCESS.
     */
    public static final String MESSAGE_PACKED_ITEM_SUCCESS = "Packed Item: %1$s";

    /**
     * The constant MESSAGE_DUPLICATE_ITEM.
     */
    public static final String MESSAGE_DUPLICATE_ITEM = "This item already exists in the packing list.";

    private final Index index;
    private final CheckItemDescriptor checkItemDescriptor;

    /**
     * Instantiates a new Edit item command.
     *
     * @param index               of the Item in the filtered Item list to edit
     * @param checkItemDescriptor details to edit the Item with
     */
    public CheckItemCommand(Index index, CheckItemDescriptor checkItemDescriptor) {
        requireNonNull(index);
        requireNonNull(checkItemDescriptor);

        this.index = index;
        this.checkItemDescriptor = new CheckItemDescriptor(checkItemDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<PackingListItem> lastShownList = model.getFilteredPackingList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
        }

        PackingListItem itemToCheck = lastShownList.get(index.getZeroBased());
        PackingListItem checkItem = createCheckItem(itemToCheck, checkItemDescriptor);

        if (!itemToCheck.isSame(checkItem) && model.hasPackingListItem(checkItem)) {
            throw new CommandException(MESSAGE_DUPLICATE_ITEM);
        }

        model.setPackingListItem(itemToCheck, checkItem);
        model.updateFilteredPackingList(PREDICATE_SHOW_ALL_PACKING_LIST_ITEMS);
        return new CommandResult(String.format(MESSAGE_PACKED_ITEM_SUCCESS, checkItem));
    }

    private static PackingListItem createCheckItem(PackingListItem itemToCheck, CheckItemDescriptor checkItemDescriptor) {
        assert itemToCheck != null;

        ItemName updatedName = itemToCheck.getItemName();
        Quantity updatedQuantity = itemToCheck.getQuantity();
        ItemCategory updatedCategory = itemToCheck.getItemCategory();

        Boolean isCheck = checkItemDescriptor.getPacked().get();
        boolean isPacked = isCheck.booleanValue();

        return new PackingListItem(updatedName, updatedQuantity, updatedCategory, isPacked);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CheckItemCommand)) {
            return false;
        }

        // state check
        CheckItemCommand e = (CheckItemCommand) other;
        return index.equals(e.index)
                && checkItemDescriptor.equals(e.checkItemDescriptor);
    }

    /**
     * The type Check item descriptor.
     */
    public static class CheckItemDescriptor {
        private boolean isPacked = true;

        /**
         * Instantiates a new Check item descriptor.
         */
        public CheckItemDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         *
         * @param toCopy the to copy
         */
        public CheckItemDescriptor(CheckItemDescriptor toCopy) {
            setCheck(toCopy.isPacked);
        }

        /**
         * Sets check.
         *
         * @param isPacked the is packed
         */
        public void setCheck(boolean isPacked) {
            this.isPacked = isPacked;
        }

        /**
         * Gets packed.
         *
         * @return the packed
         */
        public Optional<Boolean> getPacked() {
            return Optional.ofNullable(isPacked);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof CheckItemDescriptor)) {
                return false;
            }

            // state check
            CheckItemDescriptor e = (CheckItemDescriptor) other;
            return getPacked().equals(e.getPacked());
        }
    }
}
