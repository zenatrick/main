package team.easytravel.logic.commands.packinglist;

import static team.easytravel.logic.commands.CommandTestUtil.assertPackingListItemCommandSuccess;

import org.junit.jupiter.api.Test;

import team.easytravel.model.Model;
import team.easytravel.model.ModelManager;
import team.easytravel.model.listmanagers.AccommodationBookingManager;
import team.easytravel.model.listmanagers.ActivityManager;
import team.easytravel.model.listmanagers.FixedExpenseManager;
import team.easytravel.model.listmanagers.PackingListManager;
import team.easytravel.model.listmanagers.TransportBookingManager;
import team.easytravel.model.trip.Trip;
import team.easytravel.model.trip.TripManager;
import team.easytravel.model.userprefs.UserPrefs;
import team.easytravel.testutil.trip.TripBuilder;

public class ClearItemCommandTest {
    @Test
    void execute() {
    }

    @Test
    public void execute_emptyItemManager_success() {
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

        assertPackingListItemCommandSuccess(new ClearItemCommand(), model,
            ClearItemCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonItemManager_success() {
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
        expectedModel.setPackingListManager(new PackingListManager());

        assertPackingListItemCommandSuccess(new ClearItemCommand(), model,
            ClearItemCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
