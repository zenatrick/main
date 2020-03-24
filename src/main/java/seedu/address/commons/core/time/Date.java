package seedu.address.commons.core.time;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Date object in ET which wraps around the java.time.LocalDate and provides basic
 * functionality such as comparision between Date.
 */
public class Date {
    public static final String MESSAGE_CONSTRAINTS = "Date entered must be in the format of <dd-MM-yyyy>.";

    private static final DateTimeFormatter FORMAT_INPUT = DateTimeFormatter.ofPattern("d-M-yyyy");
    private static final DateTimeFormatter FORMAT_STORAGE = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter FORMAT_OUTPUT = DateTimeFormatter.ofPattern("dd MMM yyyy");

    private static final String MESSAGE_ERROR_UNTIL = "End date must not be earlier than current date.";

    private final LocalDate date;

    /**
     * Date can only be created by calling {@link #fromString(String)}.
     */
    private Date(LocalDate date) {
        this.date = date;
    }

    /**
     * Compare this date with another date.
     * <p>
     * The comparison is primarily based on the date, from earliest to latest. It is consistent with equals(),
     * as defined by Comparable.
     */
    public int compareTo(Date other) {
        return date.compareTo(other.date);
    }

    /**
     * Returns this Date in the user input format.
     */
    public String getStorageFormat() {
        return date.format(FORMAT_STORAGE);
    }

    /**
     * Returns the number of days from this date till the specified end {@code Date} (inclusive).
     */
    public int daysUntilInclusive(Date endInclusive) {
        if (date.compareTo(endInclusive.date) > 0) {
            throw new AssertionError(MESSAGE_ERROR_UNTIL);
        }
        return date.until(endInclusive.date).getDays() + 1;
    }

    /**
     * Creates a new {@code Date} from the given properly formatted {@code dateString}.
     */
    public static Date fromString(String dateString) {
        requireNonNull(dateString);
        checkArgument(isValidDate(dateString), MESSAGE_CONSTRAINTS);
        return new Date(LocalDate.parse(dateString, FORMAT_INPUT));
    }

    /**
     * Returns true if a given string is a valid Date format.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test, FORMAT_INPUT);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && date.equals(((Date) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

    @Override
    public String toString() {
        return date.format(FORMAT_OUTPUT);
    }
}
