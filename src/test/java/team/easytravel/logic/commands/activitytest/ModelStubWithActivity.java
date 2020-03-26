package team.easytravel.logic.commands.activitytest;

import static java.util.Objects.requireNonNull;

import team.easytravel.model.ModelStub;
import team.easytravel.model.listmanagers.activity.Activity;

/**
 * A Model stub that contains a single person.
 */
public class ModelStubWithActivity extends ModelStub {
    private final Activity activity;

    ModelStubWithActivity(Activity activity) {
        requireNonNull(activity);
        this.activity = activity;
    }

    @Override
    public boolean hasActivity(Activity activity) {
        requireNonNull(activity);
        return this.activity.isSame(activity);
    }
}
