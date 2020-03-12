package seedu.address.model.budget;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.budget.exceptions.DuplicateFixedCostException;
import seedu.address.model.budget.exceptions.FixedCostNotFoundException;

/**
 * A list of fixedCost that enforces uniqueness between its elements and does not allow nulls.
 * A FixedCost is considered unique by comparing using {@code FixedCost#isSameFixedCost(FixedCost)}.
 * As such, adding and updating of
 * FixedCost uses FixedCost#isSameFixedCost(FixedCost) for equality so as to ensure that the person
 * being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses
 * FixedCost#equals(FixedCost) so
 * as to ensure that the person with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see FixedCost#isSameFixedCost(FixedCost)
 */

public class UniqueFixedCostList implements Iterable<FixedCost> {

    private final ObservableList<FixedCost> internalList = FXCollections.observableArrayList();
    private final ObservableList<FixedCost> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(FixedCost toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameFixedCost);
    }

    /**
     * Adds a FixedCost to the list.
     * The FixedCost must not already exist in the list.
     */
    public void add(FixedCost toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateFixedCostException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the FixedCost {@code target} in the list with {@code editedFixedCost}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedFixedCost} must not be the same as another existing FixedCost in the list
     */

    public void setFixedCost(FixedCost target, FixedCost editedFixedCost) {
        requireAllNonNull(target, editedFixedCost);
        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new FixedCostNotFoundException();
        }

        if (!target.isSameFixedCost(editedFixedCost) && contains(editedFixedCost)) {
            throw new DuplicateFixedCostException();
        }

        internalList.set(index, editedFixedCost);
    }

    /**
     * Removes the equivalent FixedCost from the list.
     * The FixedCost must exist in the list.
     */
    public void remove(FixedCost toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new FixedCostNotFoundException();
        }
    }

    public void setFixedCosts(UniqueFixedCostList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    public void setFixedCosts(List<FixedCost> fixedCosts) {
        requireAllNonNull(fixedCosts);
        if (!fixedCostsareUnique(fixedCosts)) {
            throw new DuplicateFixedCostException();
        }
        internalList.setAll(fixedCosts);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<FixedCost> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<FixedCost> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueFixedCostList // instanceof handles nulls
                && internalList.equals(((UniqueFixedCostList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean fixedCostsareUnique(List<FixedCost> fixedCosts) {
        for (int i = 0; i < fixedCosts.size() - 1; i++) {
            for (int j = i + 1; j < fixedCosts.size(); j++) {
                if (fixedCosts.get(i).isSameFixedCost(fixedCosts.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }


}
