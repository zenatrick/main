package seedu.address.model.trip;

import static java.util.Objects.requireNonNull;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableObjectValue;

/**
 * Wraps all data at the Trip level
 * Duplicates are not allowed (by.equals comparison)
 */
public class TripManager implements ReadOnlyTripManager {

    private static boolean tripExist;
    private static ObservableObjectValue<Trip> trip = new SimpleObjectProperty<>();


    /**
     * Instantiates a new trip.
     */
    public TripManager() {
    }

    /**
     * Creates an TripManager using the Trip in the {@code} toBeCopied}
     */
    public TripManager(ReadOnlyTripManager toBeCopied) {
        //resetData(toBeCopied);
    }

    /**
     * Replaces the current trip with {@code trip}.
     */
    public void setTrip(Trip trip) {
        tripExist = true;
        this.trip = new SimpleObjectProperty<>(trip);;
    }

    /**
     * Resets the existing data of this {@code TripManager} with {@code newData}
     */
    public void resetData(ReadOnlyTripManager newData) {
       // requireNonNull(newData.getTrip());
        setTrip(newData.getTrip().get());
    }

    // Trip-level operations

    /**
     * Returns true if there exist a trip
     */
    public boolean hasTrip() {
        return tripExist;
    }


    /**
     * Remove a the trip from the entire program
     * trip must exist in the program.
     */
    public void removeTrip() {
        this.trip = null;
        this.tripExist = false;
    }

    // Util methods

    @Override
    public String toString() {
        return trip.get().toString();
        // TODO: refine later
    }


    @Override
    public int hashCode() {
        return trip.hashCode();
    }


    @Override
    public ObservableObjectValue<Trip> getTrip() {
        return this.trip;
    }
}

