package seedu.address.model.budget;

import javafx.collections.ObservableList;
import seedu.address.model.budget.FixedCost;

/**
 * Unmodifiable view of an FixedCostBook
 */
public interface ReadOnlyFixedCostBook {

    /**
     * Returns an unmodifiable view of the FixedCost list.
     * This list will not contain any duplicate FixedCosts.
     */
    ObservableList<FixedCost> getFixedCostList();

}
