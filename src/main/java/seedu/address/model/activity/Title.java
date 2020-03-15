package seedu.address.model.activity;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Title in Activity.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Title {

    /**
     * The constant MESSAGE_CONSTRAINTS.
     */
    public static final String MESSAGE_CONSTRAINTS = "Title must be made up of alphanumeric words that is less "
            + "than 50 characters long.";

    // todo update regex to match constraints
    // Allows for 50 alphanumeric characters.
    public static final String VALIDATION_REGEX = "^(?!\\s*$)[A-Za-z0-9\\s]{1,50}+";

    /**
     * The Value.
     */
    public final String value;

    /**
     * Every field must be present and not null.
     *
     * @param name a valid name.
     */
    public Title(String name) {
        requireNonNull(name);
        checkArgument(isValidTitle(name), MESSAGE_CONSTRAINTS);
        value = name;
    }

    public static boolean isValidTitle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.activity.Title // instanceof handles nulls
                && value.equals(((seedu.address.model.activity.Title) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

