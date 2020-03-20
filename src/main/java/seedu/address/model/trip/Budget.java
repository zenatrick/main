package seedu.address.model.trip;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represent a Budget in the Trip.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Budget {

    private final String amount;

    public Budget(String amount) {
        requireAllNonNull(amount);
        this.amount = amount;
    }

    public String getBudget() {
        return amount;
    }

    @Override
    public String toString() {
        return " " + amount;
    }
}
