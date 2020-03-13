package seedu.address.model.fixedexpense;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a FixedExpense's amount in the FixedExpenseManager.
 * Guarantees: immutable; is valid as declared in {@link #isValidAmount(String)}
 */
public class Amount {
    public static final String MESSAGE_CONSTRAINTS = "Amount must be a valid number with at most 2 decimal places.";

    // todo update regex
    // Done already, allows 10 digits, and at most 2 decimal places.
    public static final String VALIDATION_REGEX = "^([\\d]{1,30})(\\.[\\d]{1,2})?$";

    private String value;

    public Amount(String amount) {
        requireNonNull(amount);
        checkArgument(isValidAmount(amount), MESSAGE_CONSTRAINTS);
        value = amount;
    }

    /**
     * Returns true if a given string is a valid amount.
     */
    public static boolean isValidAmount(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Amount // instanceof handles nulls
                && value.equals(((Amount) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
