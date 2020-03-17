package seedu.address.model.listmanagers;

import javafx.collections.ObservableList;
import seedu.address.model.listmanagers.packinglistitem.PackingListItem;

/**
 * Unmodifiable view of a Packing List
 */
public interface ReadOnlyPackingListManager {
    /**
     * Returns an unmodifiable view of the packing list.
     * This list will not contain any duplicate items.
     */
    ObservableList<PackingListItem> getPackingList();
}
