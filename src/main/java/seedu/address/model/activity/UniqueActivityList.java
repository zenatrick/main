package seedu.address.model.activity;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.activity.exceptions.DuplicateActivityException;
import seedu.address.model.activity.exceptions.ActivityNotFoundException;

/**
 * A list of Activity that enforces uniqueness between its elements and does not allow nulls.
 * A Activity is considered unique by comparing using {@code Activity#equals(Object)}.
 * As such, adding, updating and removal of Activity uses Activity#equals(Object) for equality
 * so as to ensure that the Activity being added, updated or removed is unique in terms of identity in the
 * UniqueActivityList.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Activity#equals(Object)
 */

public class UniqueActivityList implements Iterable<Activity> {

    private final ObservableList<Activity> internalList = FXCollections.observableArrayList();
    private final ObservableList<Activity> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Activity as the given argument.
     */
    public boolean contains(Activity toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a Activity to the list.
     * The Activity must not already exist in the list.
     */
    public void add(Activity toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateActivityException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the Activity {@code target} in the list with {@code editedActivity}.
     * {@code target} must exist in the list.
     * The Activity identity of {@code editedActivity} must not be the same as another existing Activity
     * in the list
     */

    public void setActivity(Activity target, Activity editedActivity) {
        requireAllNonNull(target, editedActivity);
        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ActivityNotFoundException();
        }

        if (!target.equals(editedActivity) && contains(editedActivity)) {
            throw new DuplicateActivityException();
        }

        internalList.set(index, editedActivity);
    }

    /**
     * Removes the equivalent Activity from the list.
     * The Activity must exist in the list.
     */
    public void remove(Activity toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ActivityNotFoundException();
        }
    }

    public void setActivityList(seedu.address.model.activity.UniqueActivityList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    public void setActivityList(List<Activity> activity) {
        requireAllNonNull(activity);
        if (!activitiesAreUnique(activity)) {
            throw new DuplicateActivityException();
        }
        internalList.setAll(activity);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Activity> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Activity> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.activity.UniqueActivityList // instanceof handles nulls
                && internalList.equals(((seedu.address.model.activity.UniqueActivityList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code activity} contains only unique Activity.
     */
    private boolean activitiesAreUnique(List<Activity> activity) {
        for (int i = 0; i < activity.size() - 1; i++) {
            for (int j = i + 1; j < activity.size(); j++) {
                if (activity.get(i).equals(activity.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }


}
