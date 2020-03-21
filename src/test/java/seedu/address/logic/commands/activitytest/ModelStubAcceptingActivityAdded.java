package seedu.address.logic.commands.activitytest;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.model.listmanagers.ActivityManager;
import seedu.address.model.listmanagers.ReadOnlyActivityManager;
import seedu.address.model.listmanagers.activity.Activity;
import seedu.address.model.util.attributes.ModelStub;

/**
 * A Model stub that always accept the person being added.
 */
public class ModelStubAcceptingActivityAdded extends ModelStub {
    final ArrayList<Activity> activitiesAdded = new ArrayList<>();

    @Override
    public boolean hasActivity(Activity activity) {
        requireNonNull(activity);
        return activitiesAdded.stream().anyMatch(activity::isSame);
    }

    @Override
    public void addActivity(Activity activity) {
        requireNonNull(activity);
        activitiesAdded.add(activity);
    }

    @Override
    public ReadOnlyActivityManager getActivityManager() {
        return new ActivityManager();
    }
}
