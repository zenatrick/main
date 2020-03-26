package team.easytravel.model.listmanagers;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import team.easytravel.model.listmanagers.fixedexpense.FixedExpense;
import team.easytravel.model.util.uniquelist.UniqueList;

/**
 * Wraps all data at the FixedExpenseManager level
 * Duplicates are not allowed (by.equals comparison)
 */
public class FixedExpenseManager implements ReadOnlyFixedExpenseManager {
    private final UniqueList<FixedExpense> uniqueFixedExpenseLists;

    /**
     * Instantiates a new FixedExpenseManager.
     */
    public FixedExpenseManager() {
        uniqueFixedExpenseLists = new UniqueList<>();
    }

    /**
     * Creates an FixedExpenseManager using the FixedExpenses in the {@code} toBeCopied}
     */
    public FixedExpenseManager(ReadOnlyFixedExpenseManager toBeCopied) {
        uniqueFixedExpenseLists = new UniqueList<>();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the fixed expense list with {@code fixedExpenses}.
     * {@code fixedExpenses} must not contain duplicate fixed expenses.
     */
    public void setFixedExpenses(List<FixedExpense> fixedExpenses) {
        this.uniqueFixedExpenseLists.setElements(fixedExpenses);
    }


    // List overwrite operations

    /**
     * Resets the existing data of this {@code FixedExpenseManager} with {@code newData}
     */
    public void resetData(ReadOnlyFixedExpenseManager newData) {
        requireNonNull(newData);
        setFixedExpenses(newData.getFixedExpenseList());
    }

    // FixedExpense-level operations

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

        uniqueFixedExpenseLists.setElement(target, editedFixedExpense);
    }


    /**
     * Remove a single fixed expense from the fixed expense list
     * Removes {@code key} from this {@code FixedExpenseManager}.
     * {@code key} must exist in the FixedExpenseManager.
     */
    public void removeFixedExpense(FixedExpense key) {
        uniqueFixedExpenseLists.remove(key);
    }

    /**
     * Sorts the fixed expense list
     * @param cmp
     * @return the sorted fixedexpense list
     */
    public ObservableList<FixedExpense> sortFixedExpenseList(Comparator<FixedExpense> cmp) {
        uniqueFixedExpenseLists.sort(cmp);
        return uniqueFixedExpenseLists.asUnmodifiableObservableList();
    }


    // Util methods

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
