package seedu.address.model.budget.exceptions;

public class DuplicateFixedCostException extends RuntimeException {
    public DuplicateFixedCostException() {
        super("Operation would result in duplicate FixedCosts");
    }
}
