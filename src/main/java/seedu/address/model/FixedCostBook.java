package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.budget.FixedCost;
import seedu.address.model.budget.UniqueFixedCostList;

/**
 * Wraps all data at the FixedCost-book level
 * Duplicates are not allowed (by.isSameFixedCost comparison)
 */
public class FixedCostBook implements ReadOnlyFixedCostBook {

    //The replacement of AddressBook

    private final UniqueFixedCostList uniqueFixedCostLists;

    {
        uniqueFixedCostLists = new UniqueFixedCostList();
    }

    public FixedCostBook() {
    }

    /**
     * Creates an FixedCostBook using the FixedCosts in the {@code} toBeCopied}
     */
    public FixedCostBook(ReadOnlyFixedCostBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the fixedCosts list with {@code fixedCosts}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setFixedCosts(List<FixedCost> fixedCosts) {
        this.uniqueFixedCostLists.setFixedCosts(fixedCosts);
    }

    /**
     * Resets the existing data of this {@code FixedCostBook} with {@code newData}
     */
    public void resetData(ReadOnlyFixedCostBook newData) {
        requireNonNull(newData);
        setFixedCosts(newData.getFixedCostList());
    }

    /**
     * Returns true if a fixedCost with the same identity as {@code fixedCost} exists in the FixedCostBook.
     */
    public boolean hasFixedCost(FixedCost fixedCost) {
        requireNonNull(fixedCost);
        return uniqueFixedCostLists.contains(fixedCost);
    }

    /**
     * Adds a fixedCost to the fixedCostBook.
     * The person must not already exist in the fixedCostBook.
     */
    public void addPerson(FixedCost f) {
        uniqueFixedCostLists.add(f);
    }

    /**
     * Replaces the given fixedCost {@code target} in the list with {@code editedFixedCost}.
     * {@code target} must exist in the fixedCostBook.
     * The person identity of {@code editedFixedCost} must not be the same as another existing fixedCost
     * in the address book.
     */
    public void setFixedCost(FixedCost target, FixedCost editedFixedCost) {
        requireNonNull(editedFixedCost);

        uniqueFixedCostLists.setFixedCost(target, editedFixedCost);
    }

    /**
     * Removes {@code key} from this {@code FixedCostBook}.
     * {@code key} must exist in the fixedCost book.
     */
    public void removeFixedCost(FixedCost key) {
        uniqueFixedCostLists.remove(key);
    }

    @Override
    public String toString() {
        return uniqueFixedCostLists.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<FixedCost> getFixedCostList() {
        return uniqueFixedCostLists.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FixedCostBook // instanceof handles nulls
                && uniqueFixedCostLists.equals(((FixedCostBook) other).uniqueFixedCostLists));
    }

    @Override
    public int hashCode() {
        return uniqueFixedCostLists.hashCode();
    }







}
