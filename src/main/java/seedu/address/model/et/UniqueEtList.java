package seedu.address.model.et;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
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
 * @see Et#isSameEt(Et)
 */
public class UniqueEtList implements Iterable<Et> {

    private final ObservableList<Et> internalList = FXCollections.observableArrayList();
    private final ObservableList<Et> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Et toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameEt);
    }

    /**
     * Adds a Et to the list.
     * The Et must not already exist in the list.
     */
    public void add(Et toAdd) {
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
    public void setEt(Et target, Et editedEt) {
        requireAllNonNull(target, editedEt);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSameEt(editedEt) && contains(editedEt)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedEt);
    }

    /**
     * Removes the equivalent Et from the list.
     * The Et must exist in the list.
     */
    public void remove(Et toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setEts(UniqueEtList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setEts(List<Et> ets) {
        requireAllNonNull(ets);
        if (!etsAreUnique(ets)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(ets);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Et> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Et> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueEtList // instanceof handles nulls
                && internalList.equals(((UniqueEtList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean etsAreUnique(List<Et> ets) {
        for (int i = 0; i < ets.size() - 1; i++) {
            for (int j = i + 1; j < ets.size(); j++) {
                if (ets.get(i).isSameEt(ets.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
