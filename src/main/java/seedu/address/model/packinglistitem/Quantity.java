package seedu.address.model.packinglistitem;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Item's quantity in the packing list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Quantity {
    /**
     * The constant MESSAGE_CONSTRAINTS.
     */
    public static final String MESSAGE_CONSTRAINTS =
            "Quantity should only be a positive integer, from 1 onwards";

    /**
     * The Quantity.
     */
    public final int quantity;

    /**
     * Every field must be present and not null.
     *
     * @param q the q
     */
    public Quantity(int q) {
        requireNonNull(q);
        checkArgument(isValidQuantity(q), MESSAGE_CONSTRAINTS);
        quantity = q;
    }

    /**
     * Returns true if a given integer is a valid quantity number.
     *
     * @param test the test
     * @return the boolean
     */
    public static boolean isValidQuantity(int test) {
        return (test >= 1 && test <= 100);
    }

    @Override
    public String toString() {
        return "" + quantity;
    }
}
