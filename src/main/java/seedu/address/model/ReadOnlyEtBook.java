package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.et.Et;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyEtBook {

    /**
     * Returns an unmodifiable view of the Et list.
     * The list will not contain any duplicate Ets.
     */

    ObservableList<Et> getEtList();
}
