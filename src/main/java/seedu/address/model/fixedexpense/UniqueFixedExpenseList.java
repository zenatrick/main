package seedu.address.model.fixedexpense;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.fixedexpense.exceptions.DuplicateFixedExpenseException;
import seedu.address.model.fixedexpense.exceptions.FixedExpenseNotFoundException;

/**
 * A list of FixedExpenses that enforces uniqueness between its elements and does not allow nulls.
 * A FixedExpense is considered unique by comparing using {@code FixedExpense#equals(Object)}.
 * As such, adding, updating and removal of FixedExpense uses FixedExpense#equals(Object) for equality
 * so as to ensure that the FixedExpense being added, updated or removed is unique in terms of identity in the
 * UniqueFixedExpenseList.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see FixedExpense#equals(Object)
 */

public class UniqueFixedExpenseList implements Iterable<FixedExpense> {

    private final ObservableList<FixedExpense> internalList = FXCollections.observableArrayList();
    private final ObservableList<FixedExpense> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent FixedExpense as the given argument.
     */
    public boolean contains(FixedExpense toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a FixedExpense to the list.
     * The FixedExpense must not already exist in the list.
     */
    public void add(FixedExpense toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateFixedExpenseException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the FixedExpense {@code target} in the list with {@code editedFixedExpense}.
     * {@code target} must exist in the list.
     * The FixedExpense identity of {@code editedFixedExpense} must not be the same as another existing FixedExpense
     * in the list
     */

    public void setFixedExpense(FixedExpense target, FixedExpense editedFixedExpense) {
        requireAllNonNull(target, editedFixedExpense);
        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new FixedExpenseNotFoundException();
        }

        if (!target.equals(editedFixedExpense) && contains(editedFixedExpense)) {
            throw new DuplicateFixedExpenseException();
        }

        internalList.set(index, editedFixedExpense);
    }

    /**
     * Removes the equivalent FixedExpense from the list.
     * The FixedExpense must exist in the list.
     */
    public void remove(FixedExpense toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new FixedExpenseNotFoundException();
        }
    }

    public void setFixedExpenses(UniqueFixedExpenseList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    public void setFixedExpenses(List<FixedExpense> fixedExpenses) {
        requireAllNonNull(fixedExpenses);
        if (!fixedExpensesAreUnique(fixedExpenses)) {
            throw new DuplicateFixedExpenseException();
        }
        internalList.setAll(fixedExpenses);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<FixedExpense> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<FixedExpense> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueFixedExpenseList // instanceof handles nulls
                && internalList.equals(((UniqueFixedExpenseList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code fixedExpenses} contains only unique fixed expenses.
     */
    private boolean fixedExpensesAreUnique(List<FixedExpense> fixedExpenses) {
        for (int i = 0; i < fixedExpenses.size() - 1; i++) {
            for (int j = i + 1; j < fixedExpenses.size(); j++) {
                if (fixedExpenses.get(i).equals(fixedExpenses.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }


}
