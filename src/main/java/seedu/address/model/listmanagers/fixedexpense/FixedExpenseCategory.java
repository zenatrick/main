package seedu.address.model.listmanagers.fixedexpense;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a FixedExpense's category in the FixedExpenseManager.
 * Guarantees: immutable; is valid as declared in {@link #isValidCategory(String)}
 */
public class FixedExpenseCategory {
    public static final String MESSAGE_CONSTRAINTS = "Category must be made up of a single alphanumeric word that is "
            + "less than 30 characters long.";

    // todo update regex to match constraints
    public static final String VALIDATION_REGEX = "^(?!\\s*$)[A-Za-z0-9\\s]{1,50}+";

    public final String value;

    public FixedExpenseCategory(String category) {
        requireNonNull(category);
        checkArgument(isValidCategory(category), MESSAGE_CONSTRAINTS);
        value = category;
    }

    public static boolean isValidCategory(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FixedExpenseCategory // instanceof handles nulls
                && value.equals(((FixedExpenseCategory) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
