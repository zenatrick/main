package team.easytravel.model.listmanagers.packinglistitem;

import static java.util.Objects.requireNonNull;
import static team.easytravel.commons.util.AppUtil.checkArgument;

/**
 * Represents a PackingListItem's category in the PackingListManager.
 * Guarantees: immutable; is valid as declared in {@link #isValidItemCategory(String)}
 */
public class ItemCategory {

    public static final String MESSAGE_CONSTRAINTS = "Category must be made up of alphanumeric words that is "
            + "less than 30 characters long. It will always be in lowercase";

    public static final String VALIDATION_REGEX = "^(?!\\s*$)[\\p{Alnum}\\s]{1,30}$";

    public final String value;

    /**
     * Instantiates a new Item category.
     *
     * @param category the category
     */
    public ItemCategory(String category) {
        requireNonNull(category);
        checkArgument(isValidItemCategory(category), MESSAGE_CONSTRAINTS);
        value = category.toLowerCase();
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
