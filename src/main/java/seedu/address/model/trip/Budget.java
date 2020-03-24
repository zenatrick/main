package seedu.address.model.trip;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.function.Predicate;


/**
 * Represent a Budget in the Trip.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Budget {

    public static final String MESSAGE_CONSTRAINTS = "Budget should be a whole number greater than 0";

    private static final int MIN_VALUE = 1;
    public static final Predicate<Integer> VALIDATION_PREDICATE = i -> i >= MIN_VALUE;

    private final Integer amount;

    public Budget(Integer amount) {
        requireAllNonNull(amount);
        checkArgument(isValidBudget(amount), MESSAGE_CONSTRAINTS);
        this.amount = amount;
    }

    /**
     * Returns true if a given Integer is a valid budget number.
     */
    public static boolean isValidBudget(Integer test) {
        return VALIDATION_PREDICATE.test(test);
    }

    @Override
    public String toString() {
        return amount.toString();
    }
}
