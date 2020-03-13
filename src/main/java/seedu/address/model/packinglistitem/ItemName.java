package seedu.address.model.packinglistitem;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Item's quantity in the packing list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ItemName {

    /**
     * The constant MESSAGE_CONSTRAINTS.
     */
    public static final String MESSAGE_CONSTRAINTS =
            "Item name should only contain letters, and it should have at least 1 letter";

    /**
     * The constant VALIDATION_REGEX.
     */
    public static final String VALIDATION_REGEX = "\\d{1,}";

    /**
     * The Value.
     */
    public final String value;

    /**
     * Every field must be present and not null.
     *
     * @param name the name
     */
    public ItemName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        value = name;
    }

    /**
     * Returns true if a given string is a valid item name.
     *
     * @param test the test
     * @return the boolean
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItemName // instanceof handles nulls
                && value.equals(((ItemName) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
