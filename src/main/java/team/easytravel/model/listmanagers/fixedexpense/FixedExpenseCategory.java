package team.easytravel.model.listmanagers.fixedexpense;

import static java.util.Objects.requireNonNull;

import team.easytravel.commons.util.AppUtil;

/**
 * Represents a FixedExpense's category in the FixedExpenseManager.
 * Guarantees: immutable; is valid as declared in {@link #isValidCategory(String)}
 */
public class FixedExpenseCategory {

    public static final String CATEGORY_ACTIVITY = "activities";
    public static final String CATEGORY_ACCOMMODATION = "accommodations";
    public static final String CATEGORY_TRANSPORT = "transport";
    public static final String CATEGORY_OTHER = "others";

    public static final String MESSAGE_CONSTRAINTS = "Category can take one of the following values: "
            + "\"" + CATEGORY_ACTIVITY + "\", \"" + CATEGORY_ACCOMMODATION + "\", \"" + CATEGORY_TRANSPORT + "\" or \""
            + CATEGORY_OTHER + "\".";

    public static final String VALIDATION_REGEX = String.format("^(%s|%s|%s|%s)$", CATEGORY_ACTIVITY,
            CATEGORY_ACCOMMODATION, CATEGORY_TRANSPORT, CATEGORY_OTHER);

    public final String value;

    public FixedExpenseCategory(String category) {
        requireNonNull(category);
        AppUtil.checkArgument(isValidCategory(category.toLowerCase()), MESSAGE_CONSTRAINTS);
        value = category.toLowerCase();
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
