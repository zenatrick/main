package team.easytravel.model.listmanagers.accommodationbooking;

import static java.util.Objects.requireNonNull;

import team.easytravel.commons.util.AppUtil;

/**
 * Represents an Accommodation's remark in the AccommodationBookingManager.
 * Guarantees: immutable; is valid as declared in {@link #isValidRemark(String)}
 */
public class Remark {

    public static final String MESSAGE_CONSTRAINTS = "Remark must be made up of words that is not more than "
            + "than 150 characters long. (Common punctuation characters allowed)";

    // Allows for 150 characters long
    // The characters: !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~ are allowed.
    public static final String VALIDATION_REGEX = "^[\\p{Alnum}\\p{Punct}\\s]{1,150}$";

    public final String value;

    /**
     * Constructs a {@code Remark}.
     *
     * @param remark A valid remark.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        AppUtil.checkArgument(isValidRemark(remark), MESSAGE_CONSTRAINTS);
        value = remark;
    }

    /**
     * Returns true if a given string is a valid remark.
     */
    public static boolean isValidRemark(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && value.equals(((Remark) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
