package team.easytravel.logic.commands.fixedexpense;

import static team.easytravel.logic.commands.CommandTestUtil.assertFixedExpenseCommandSuccess;
import static team.easytravel.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.model.Model;
import team.easytravel.model.ModelManager;
import team.easytravel.model.ModelStub;
import team.easytravel.model.listmanagers.AccommodationBookingManager;
import team.easytravel.model.listmanagers.ActivityManager;
import team.easytravel.model.listmanagers.FixedExpenseManager;
import team.easytravel.model.listmanagers.PackingListManager;
import team.easytravel.model.listmanagers.TransportBookingManager;
import team.easytravel.model.trip.Trip;
import team.easytravel.model.trip.TripManager;
import team.easytravel.model.userprefs.UserPrefs;
import team.easytravel.testutil.activity.TypicalActivity;
import team.easytravel.testutil.trip.TripBuilder;

class ClearFixedExpenseCommandTest {

    @Test
    public void execute_nonTripSet_throwsCommandException() {
        ModelStubNoTripSet modelStub = new ModelStubNoTripSet();
        assertThrows(CommandException.class, () -> new ClearFixedExpenseCommand()
                .execute(modelStub));
    }

    @Test
    public void execute_emptyFixedExpenseManager_success() {
        Trip newTrip = new TripBuilder().build();
        TripManager tripManagerSet = new TripManager();
        tripManagerSet.setTrip(newTrip);

        Model model = new ModelManager(new TransportBookingManager(),
                new FixedExpenseManager(), new PackingListManager(), new ActivityManager(),
                new AccommodationBookingManager(), tripManagerSet,
                new UserPrefs());
        Model expectedModel = new ModelManager(new TransportBookingManager(),
                new FixedExpenseManager(), new PackingListManager(), new ActivityManager(),
                new AccommodationBookingManager(), tripManagerSet,
                new UserPrefs());

        assertFixedExpenseCommandSuccess(new ClearFixedExpenseCommand(), model,
                ClearFixedExpenseCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonFixedExpenseManager_success() {
        Trip newTrip = new TripBuilder().build();
        TripManager tripManagerSet = new TripManager();
        tripManagerSet.setTrip(newTrip);

        Model model = new ModelManager(new TransportBookingManager(),
                new FixedExpenseManager(), new PackingListManager(), new ActivityManager(),
                new AccommodationBookingManager(), tripManagerSet,
                new UserPrefs());

        Model expectedModel = new ModelManager(new TransportBookingManager(),
                new FixedExpenseManager(), new PackingListManager(), new ActivityManager(),
                new AccommodationBookingManager(), tripManagerSet,
                new UserPrefs());
        expectedModel.setActivityManager(TypicalActivity.getTypicalActivityManager());

        assertFixedExpenseCommandSuccess(new ClearFixedExpenseCommand(), model,
                ClearFixedExpenseCommand.MESSAGE_SUCCESS, expectedModel);
    }

    /**
     * A Model stub that always accept the fixed expense being added and is below budget.
     */
    private class ModelStubNoTripSet extends ModelStub {
        @Override
        public boolean hasTrip() {
            return false;
        }
    }
}
