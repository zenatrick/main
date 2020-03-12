package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.trip.Trip;
import seedu.address.model.trip.UniqueTripList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueTripList trips;
    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        trips = new UniqueTripList();
    }

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the trip list with {@code trips}.
     * {@code trips} must not contain duplicate trips.
     */
    public void setTrips(List<Trip> trips) {
        this.trips.setTrips(trips);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setTrips(newData.getTripList());
    }

    //// person-level operations

    /**
     * Returns true if a trip with the same identity as {@code person} exists in the address book.
     */
    public boolean hasTrip(Trip trip) {
        requireNonNull(trip);
        return trips.contains(trip);
    }

    /**
     * Adds a trip to the address book.
     * The trip must not already exist in the address book.
     */
    public void addTrip(Trip p) {
        trips.add(p);
    }

    /**
     * Replaces the given Trip {@code target} in the list with {@code editedTrip}.
     * {@code target} must exist in the address book.
     * The trip identity of {@code editedTrip} must not be the same as another existing trip in the address book.
     */
    public void setTrip(Trip target, Trip editedTrip) {
        requireNonNull(editedTrip);

        trips.setTrips(target, editedTrip);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeTrip(Trip key) {
        trips.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return trips.asUnmodifiableObservableList().size() + " trips";
        // TODO: refine later
    }

    @Override
    public ObservableList<Trip> getTripList() {
        return trips.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && trips.equals(((AddressBook) other).trips));
    }

    @Override
    public int hashCode() {
        return trips.hashCode();
    }
}
