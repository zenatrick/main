package seedu.address.model.accommodationbooking;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * The type Remark.
 */
public class Remark {

    public static final String MESSAGE_CONSTRAINTS = "Remark must be made up of alphanumeric words that is less "
            + "than 150 characters long.";

    // todo update regex to match constraints
    // Allows for 150 characters long.
    public static final String VALIDATION_REGEX = "^(?!\\s*$)[A-Za-z0-9\\s]{1,150}+";

    /**
     * The accommodation remark.
     */
    private final String accommodationRemark;

    /**
     * Instantiates a new Remark.
     *
     * @param remark the description
     */
    public Remark(String remark) {
        requireNonNull(remark);
        checkArgument(isValidRemark(remark), MESSAGE_CONSTRAINTS);
        accommodationRemark = remark;
    }

    public static boolean isValidRemark(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return accommodationRemark;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.accommodationbooking.Remark // instanceof handles nulls
                && accommodationRemark.equals(((seedu.address.model.accommodationbooking.Remark) other)
                .accommodationRemark)); // state check
    }

    @Override
    public int hashCode() {
        return accommodationRemark.hashCode();
    }

}
