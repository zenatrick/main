package seedu.address.model.listmanagers.packinglistitem;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * The type Item category.
 */
public class ItemCategory {
    /**
     * The constant MESSAGE_CONSTRAINTS.
     */
    public static final String MESSAGE_CONSTRAINTS = "Category must be made up of a single alphanumeric word that is "
            + "less than 30 characters long.";

    /**
     * The constant VALIDATION_REGEX.
     */
    // todo update regex to match constraints
    public static final String VALIDATION_REGEX = "\\p{Alnum}{1,30}+";

    public final String value;

    /**
     * Instantiates a new Item category.
     *
     * @param category the category
     */
    public ItemCategory(String category) {
        requireNonNull(category);
        checkArgument(isValidItemCategory(category), MESSAGE_CONSTRAINTS);
        value = category;
    }

    /**
     * Is valid item category boolean.
     *
     * @param test the test
     * @return the boolean
     */
    public static boolean isValidItemCategory(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItemCategory // instanceof handles nulls
                && value.equals(((ItemCategory) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}