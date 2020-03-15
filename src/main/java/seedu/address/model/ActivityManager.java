package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.activity.Activity;
import seedu.address.model.activity.UniqueActivityList;


/**
 * Wraps all data at the ActivityManager level
 * Duplicates are not allowed (by.equals comparison)
 */
public class ActivityManager implements ReadOnlyActivityManager {

    private final UniqueActivityList uniqueActivityLists;

    {
        uniqueActivityLists = new UniqueActivityList();
    }

    public ActivityManager() {
    }

    /**
     * Creates an ActivityManager using the Activity in the {@code} toBeCopied}
     */
    public ActivityManager(ReadOnlyActivityManager toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the Activity list with {@code Activity}.
     * {@code Activity} must not contain duplicate activity.
     */
    public void setActivity(List<Activity> Activity) {
        this.uniqueActivityLists.setActivity(Activity);
    }

    /**
     * Resets the existing data of this {@code ActivityManager} with {@code newData}
     */
    public void resetData(ReadOnlyActivityManager newData) {
        requireNonNull(newData);
        setActivity(newData.getActivityList());
    }

    /**
     * Returns true if a activity with the same identity as {@code activity} exists in the ActivityManager.
     */
    public boolean hasActivity(Activity activity) {
        requireNonNull(activity);
        return uniqueActivityLists.contains(activity);
    }

    /**
     * Adds a fixed activity to the ActivityManager.
     * The fixed activity must not already exist in the ActivityManager.
     */
    public void addActivity(Activity f) {
        uniqueActivityLists.add(f);
    }

    /**
     * Replaces the given fixed activity {@code target} in the list with {@code editedActivity}.
     * {@code target} must exist in the ActivityManager.
     * The Activity identity of {@code editedActivity} must not be the same as another existing fixed activity
     * in the ActivityManager.
     */
    public void setActivity(Activity target, Activity editedActivity) {
        requireNonNull(editedActivity);

        uniqueActivityLists.setActivity(target, editedActivity);
    }

    /**
     * Removes {@code key} from this {@code ActivityManager}.
     * {@code key} must exist in the ActivityManager.
     */
    public void removeActivity(Activity key) {
        uniqueActivityLists.remove(key);
    }

    @Override
    public String toString() {
        return uniqueActivityLists.asUnmodifiableObservableList().size() + "  activities";
        // TODO: refine later
    }

    public ObservableList<Activity> getActivityList() {
        return uniqueActivityLists.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ActivityManager // instanceof handles nulls
                && uniqueActivityLists.equals(((ActivityManager) other).uniqueActivityLists));
    }

    @Override
    public int hashCode() {
        return uniqueActivityLists.hashCode();
    }







}
