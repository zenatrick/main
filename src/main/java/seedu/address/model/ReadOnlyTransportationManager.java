package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.transportation.Transportation;

/**
 * Unmodifiable view of a transportation manager.
 */
public interface ReadOnlyTransportationManager {

    /**
     * Returns an unmodifiable view of the transportation list.
     * This list will not contain any duplicate transportation.
     */
    ObservableList<Transportation> getTransportationList();

}
