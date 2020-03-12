package seedu.address.model.budget.exceptions;

/**
 * For duplicate costs involved.
 */
public class DuplicateFixedCostException extends RuntimeException {
    public DuplicateFixedCostException() {
        super("Operation would result in duplicate FixedCosts");
    }
}
