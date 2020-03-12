package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.trip.Trip;

/**
 * Returns an unmodifiable view of the persons list.
 * This list will not contain any duplicate persons.
 */

public interface ReadOnlyTripBook {



    ObservableList<Trip> getTripList();

}
