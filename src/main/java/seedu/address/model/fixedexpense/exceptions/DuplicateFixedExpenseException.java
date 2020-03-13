package seedu.address.model.fixedexpense.exceptions;

/**
 * Signals that the operation will result in duplicate FixedExpenses (FixedExpenses are considered duplicates if they
 * have the same identity).
 */
public class DuplicateFixedExpenseException extends RuntimeException {
    public DuplicateFixedExpenseException() {
        super("Operation would result in duplicate fixed expenses");
    }
}
