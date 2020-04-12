package team.easytravel.commons.core.time;

import static java.util.Objects.requireNonNull;
import static team.easytravel.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * Represents a Time object in ET which wraps around the java.time.LocalTime and provides basic
 * functionality such as comparision between Time.
 */
public class Time {
    public static final String MESSAGE_CONSTRAINTS = "Time entered must be in the format of <HH:mm>.";

    private static final DateTimeFormatter FORMAT_INPUT = DateTimeFormatter.ofPattern("H:m");
    private static final DateTimeFormatter FORMAT_STORAGE = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter FORMAT_OUTPUT = DateTimeFormatter.ofPattern("hh:mma");

    protected final LocalTime time;

    /**
     * Time can only be created by calling {@link #fromString(String)}.
     */
    protected Time(LocalTime time) {
        this.time = time;
    }

    /**
     * Compare this time with another time.
     * <p>
     * The comparison is primarily based on the time, from earliest to latest. It is consistent with equals(),
     * as defined by Comparable.
     */
    public int compareTo(Time other) {
        return time.compareTo(other.time);
    }

    /**
     * Returns this time in the user input format.
     */
    public String getStorageFormat() {
        return time.format(FORMAT_STORAGE);
    }

    /**
     * Creates a new {@code Time} from the given properly formatted {@code timeString}.
     */
    public static Time fromString(String timeString) {
        requireNonNull(timeString);
        checkArgument(isValidTime(timeString), MESSAGE_CONSTRAINTS);
        return new Time(LocalTime.parse(timeString, FORMAT_INPUT));
    }

    /**
     * Returns true if a given string is a valid Time format.
     */
    public static boolean isValidTime(String test) {
        try {
            LocalTime.parse(test, FORMAT_INPUT);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Time // instanceof handles nulls
                && time.equals(((Time) other).time)); // state check
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }

    @Override
    public String toString() {
        return time.format(FORMAT_OUTPUT);
    }
}
