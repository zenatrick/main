package team.easytravel.logic.commands.fixedexpense;

import static team.easytravel.logic.commands.CommandTestUtil.assertFixedExpenseCommandSuccess;
import static team.easytravel.testutil.Assert.assertThrows;
import static team.easytravel.testutil.TypicalFixedExpense.getTypicalFixedExpenseManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.model.Model;
import team.easytravel.model.ModelManager;
import team.easytravel.model.ModelStub;
import team.easytravel.model.listmanagers.AccommodationBookingManager;
import team.easytravel.model.listmanagers.ActivityManager;
import team.easytravel.model.listmanagers.PackingListManager;
import team.easytravel.model.listmanagers.TransportBookingManager;
import team.easytravel.model.trip.Trip;
import team.easytravel.model.trip.TripManager;
import team.easytravel.model.userprefs.UserPrefs;
import team.easytravel.testutil.trip.TripBuilder;


class ListFixedExpenseCommandTest {

    private Model model;
    private TripManager tripManagerSet;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        Trip newTrip = new TripBuilder().build();
        tripManagerSet = new TripManager();
        tripManagerSet.setTrip(newTrip);
        model = new ModelManager(new TransportBookingManager(),
                getTypicalFixedExpenseManager(), new PackingListManager(), new ActivityManager(),
                new AccommodationBookingManager(), tripManagerSet,
                new UserPrefs());

        expectedModel = new ModelManager(new TransportBookingManager(),
                getTypicalFixedExpenseManager(), new PackingListManager(), new ActivityManager(),
                new AccommodationBookingManager(), tripManagerSet,
                new UserPrefs());
    }

    @Test
    public void execute_listFixedExpenseWithoutTripSet_throwsCommandException() {
        ModelStubNoTripSet modelStub = new ModelStubNoTripSet();
        assertThrows(CommandException.class, () -> new ListFixedExpenseCommand()
                .execute(modelStub));
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertFixedExpenseCommandSuccess(new ListFixedExpenseCommand(),
                model, ListFixedExpenseCommand.MESSAGE_SUCCESS, expectedModel);
    }


    /**
     * A Model stub has no trip set.
     */
    private class ModelStubNoTripSet extends ModelStub {
        @Override
        public boolean hasTrip() {
            return false;
        }
    }
}
