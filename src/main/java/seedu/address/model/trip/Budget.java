package seedu.address.model.trip;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represent a Budget in the Trip.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Budget {

    private final Integer amount;
    public static final String MESSAGE_CONSTRAINTS =
            "Budget should be a whole number greater than 0";

    public Budget(Integer amount) {
        requireAllNonNull(amount);
        checkArgument(isValidBudget(amount), MESSAGE_CONSTRAINTS);
        this.amount = amount;
    }

    public Integer getBudget() {
        return amount;
    }

    /**
     * Returns true if a given Integer is a valid budget number.
     */
    public static boolean isValidBudget(Integer test) {
        return test > 0;
    }

    @Override
    public String toString() {
        return " " + amount;
    }
}
