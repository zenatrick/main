package team.easytravel.logic.commands.transportbooking;

import static team.easytravel.logic.commands.CommandTestUtil.assertTransportBookingCommandSuccess;
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
import team.easytravel.testutil.transportbooking.TypicalTransportBooking;
import team.easytravel.testutil.trip.TripBuilder;

public class ClearTransportBookingCommandTest {

    @Test
    public void execute_nonTripSet_throwsCommandException() {
        ModelStubNoTripSet modelStub = new ModelStubNoTripSet();
        assertThrows(CommandException.class, () -> new ClearTransportBookingCommand()
                .execute(modelStub));
    }

    @Test
    public void execute_emptyTransportBookingManager_success() {
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

        assertTransportBookingCommandSuccess(new ClearTransportBookingCommand(), model,
                ClearTransportBookingCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonTransportBookingManager_success() {
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
        expectedModel.setTransportBookingManager(TypicalTransportBooking.getTypicalTransportBookingManager());

        assertTransportBookingCommandSuccess(new ClearTransportBookingCommand(), model,
                ClearTransportBookingCommand.MESSAGE_SUCCESS, expectedModel);
    }

    /**
     * A Model stub that does not have a trip set.
     */
    private class ModelStubNoTripSet extends ModelStub {
        @Override
        public boolean hasTrip() {
            return false;
        }
    }

}
