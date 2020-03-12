package seedu.address.model.trip;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Trip#isSameEt(Trip)
 */
public class UniqueTripList implements Iterable<Trip> {

    private final ObservableList<Trip> internalList = FXCollections.observableArrayList();
    private final ObservableList<Trip> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Trip toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameEt);
    }

    /**
     * Adds a Et to the list.
     * The Et must not already exist in the list.
     */
    public void add(Trip toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the Et {@code target} in the list with {@code editedEt}.
     * {@code target} must exist in the list.
     * The Et identity of {@code editedPerson} must not be the same as another existing Et in the list.
     */
    public void setTrips(Trip target, Trip editedTrip) {
        requireAllNonNull(target, editedTrip);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSameEt(editedTrip) && contains(editedTrip)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedTrip);
    }

    /**
     * Removes the equivalent Et from the list.
     * The Et must exist in the list.
     */
    public void remove(Trip toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setTrips(UniqueTripList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setTrips(List<Trip> trips) {
        requireAllNonNull(trips);
        if (!tripsAreUnique(trips)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(trips);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Trip> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Trip> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTripList // instanceof handles nulls
                && internalList.equals(((UniqueTripList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean tripsAreUnique(List<Trip> trips) {
        for (int i = 0; i < trips.size() - 1; i++) {
            for (int j = i + 1; j < trips.size(); j++) {
                if (trips.get(i).isSameEt(trips.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
