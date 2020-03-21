package seedu.address.logic.commands.activitytest;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.activity.AddActivityCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.listmanagers.ActivityManager;
import seedu.address.model.listmanagers.ReadOnlyActivityManager;
import seedu.address.model.listmanagers.activity.Activity;
import seedu.address.model.util.attributes.ModelStub;
import seedu.address.testutil.ActivityBuilder;


public class AddActivityTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddActivityCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingActivityAdded modelStub = new ModelStubAcceptingActivityAdded();
        Activity validActivity = new ActivityBuilder().build();

        CommandResult commandResult = new AddActivityCommand(validActivity).execute(modelStub);

        assertEquals(String.format(AddActivityCommand.MESSAGE_SUCCESS, validActivity),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validActivity), modelStub.activitiesAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Activity validPerson = new ActivityBuilder().build();
        AddActivityCommand addCommand = new AddActivityCommand(validPerson);
        ModelStub modelStub = new ModelStubWithActivity(validPerson);

        assertThrows(CommandException.class, AddActivityCommand.MESSAGE_DUPLICATE_ACTIVITY,
        () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Activity eggHouse = new ActivityBuilder().withTitle("Egg house").build();
        Activity osaka = new ActivityBuilder().withTitle("Osaka").build();
        AddActivityCommand addEggCommand = new AddActivityCommand(eggHouse);
        AddActivityCommand addOsakaCommand = new AddActivityCommand(osaka);

        // same object -> returns true
        assertTrue(addEggCommand.equals(addEggCommand));

        // same values -> returns true
        AddActivityCommand addEggCommandCopy = new AddActivityCommand(eggHouse);
        assertTrue(addEggCommand.equals(addEggCommandCopy));

        // different types -> returns false
        assertFalse(addEggCommand.equals(1));

        // null -> returns false
        assertFalse(addEggCommand.equals(null));

        // different activity -> returns false
        assertFalse(addEggCommand.equals(addOsakaCommand));
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithActivity extends ModelStub {
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

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingActivityAdded extends ModelStub {
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

}
