package seedu.address.logic.commands.activitytest;

import static java.util.Objects.requireNonNull;

import seedu.address.model.listmanagers.activity.Activity;
import seedu.address.model.util.attributes.ModelStub;

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
