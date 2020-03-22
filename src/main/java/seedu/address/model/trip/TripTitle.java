package seedu.address.model.trip;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Trip's name in the application.
 * Guarantees: immutable; is valid as declared in {@link #isValidTitle(String)}
 */
public class TripTitle {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String title;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public TripTitle(String name) {
        requireNonNull(name);
        checkArgument(isValidTitle(name), MESSAGE_CONSTRAINTS);
        title = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidTitle(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TripTitle // instanceof handles nulls
                && title.equals(((TripTitle) other).title)); // state check
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

}
