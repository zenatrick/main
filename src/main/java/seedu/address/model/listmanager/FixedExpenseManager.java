package seedu.address.model.listmanager;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.fixedexpense.FixedExpense;
import seedu.address.model.fixedexpense.UniqueFixedExpenseList;

/**
 * Wraps all data at the FixedExpenseManager level
 * Duplicates are not allowed (by.equals comparison)
 */
public class FixedExpenseManager implements ReadOnlyFixedExpenseManager {

    private final UniqueFixedExpenseList uniqueFixedExpenseLists;

    {
        uniqueFixedExpenseLists = new UniqueFixedExpenseList();
    }

    public FixedExpenseManager() {
    }

    /**
     * Creates an FixedExpenseManager using the FixedExpenses in the {@code} toBeCopied}
     */
    public FixedExpenseManager(ReadOnlyFixedExpenseManager toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the fixedExpenses list with {@code fixedExpenses}.
     * {@code fixedExpenses} must not contain duplicate fixed expenses.
     */
    public void setFixedExpenses(List<FixedExpense> fixedExpenses) {
        this.uniqueFixedExpenseLists.setFixedExpenses(fixedExpenses);
    }

    /**
     * Resets the existing data of this {@code FixedExpenseManager} with {@code newData}
     */
    public void resetData(ReadOnlyFixedExpenseManager newData) {
        requireNonNull(newData);
        setFixedExpenses(newData.getFixedExpenseList());
    }

    /**
     * Returns true if a fixedExpense with the same identity as {@code fixedExpense} exists in the FixedExpenseManager.
     */
    public boolean hasFixedExpense(FixedExpense fixedExpense) {
        requireNonNull(fixedExpense);
        return uniqueFixedExpenseLists.contains(fixedExpense);
    }

    /**
     * Adds a fixed expense to the FixedExpenseManager.
     * The fixed expense must not already exist in the FixedExpenseManager.
     */
    public void addFixedExpense(FixedExpense f) {
        uniqueFixedExpenseLists.add(f);
    }

    /**
     * Replaces the given fixed expense {@code target} in the list with {@code editedFixedExpense}.
     * {@code target} must exist in the FixedExpenseManager.
     * The person identity of {@code editedFixedExpense} must not be the same as another existing fixed expense
     * in the FixedExpenseManager.
     */
    public void setFixedExpense(FixedExpense target, FixedExpense editedFixedExpense) {
        requireNonNull(editedFixedExpense);

        uniqueFixedExpenseLists.setFixedExpense(target, editedFixedExpense);
    }

    /**
     * Removes {@code key} from this {@code FixedExpenseManager}.
     * {@code key} must exist in the FixedExpenseManager.
     */
    public void removeFixedExpense(FixedExpense key) {
        uniqueFixedExpenseLists.remove(key);
    }

    @Override
    public String toString() {
        return uniqueFixedExpenseLists.asUnmodifiableObservableList().size() + " fixed expenses";
        // TODO: refine later
    }

    @Override
    public ObservableList<FixedExpense> getFixedExpenseList() {
        return uniqueFixedExpenseLists.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FixedExpenseManager // instanceof handles nulls
                && uniqueFixedExpenseLists.equals(((FixedExpenseManager) other).uniqueFixedExpenseLists));
    }

    @Override
    public int hashCode() {
        return uniqueFixedExpenseLists.hashCode();
    }







}
