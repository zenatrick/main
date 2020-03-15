package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.activity.Activity;

/**
 * Unmodifiable view of an ActivityManager
 */
public interface ReadOnlyActivityManager {
    /**
     * Returns an unmodifiable view of the activity list.
     * This list will not contain any duplicate activity.
     */
    ObservableList<Activity> getActivityList();
}
