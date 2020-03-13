package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.packinglistitem.Item;

/**
 * Unmodifiable view of a Packing List
 */
public interface ReadOnlyPackingList {
    /**
     * Returns an unmodifiable view of the packing list.
     * This list will not contain any duplicate items.
     */
    ObservableList<Item> getPackingList();
}
