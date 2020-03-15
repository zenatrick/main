package seedu.address.model.packinglistitem.exceptions;

/**
 * Signals that the operation will result in duplicate PackingListItems (PackingListItems are considered duplicates if
 * they have the same identity).
 */
public class DuplicatePackingListItemException extends RuntimeException {
    public DuplicatePackingListItemException() {
        super("Operation would result in duplicate packing list items");
    }
}
