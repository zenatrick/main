package seedu.address.model.trip;

import javafx.beans.value.ObservableObjectValue;

/**
 * Unmodifiable view of a Packing List
 */
public interface ReadOnlyTripManager {
    /**
     * Returns an unmodifiable view of the Trip.
     * This list will not contain any duplicate items.
     */
    ObservableObjectValue<Trip> getTrip();
}
