package seedu.address.model.packinglistitem;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an PackingListItem's quantity in the packing list.
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
    public final Integer quantity;

    /**
     * Every field must be present and not null.
     *
     * @param quantity a valid quantity.
     */
    public Quantity(Integer quantity) {
        requireNonNull(quantity);
        checkArgument(isValidQuantity(quantity), MESSAGE_CONSTRAINTS);
        this.quantity = quantity;
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

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Quantity // instanceof handles nulls
                && quantity.equals(((Quantity) other).quantity)); // state check
    }

    @Override
    public int hashCode() {
        return quantity.hashCode();
    }
}
