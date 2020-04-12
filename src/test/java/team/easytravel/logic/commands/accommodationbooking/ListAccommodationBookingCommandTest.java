package team.easytravel.logic.commands.accommodationbooking;

import static team.easytravel.logic.commands.CommandTestUtil.assertAccommodationBookingCommandSuccess;
import static team.easytravel.logic.commands.CommandTestUtil.showAccommodationBookingAtIndex;
import static team.easytravel.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import team.easytravel.model.Model;
import team.easytravel.model.ModelManager;
import team.easytravel.model.listmanagers.ActivityManager;
import team.easytravel.model.listmanagers.FixedExpenseManager;
import team.easytravel.model.listmanagers.PackingListManager;
import team.easytravel.model.listmanagers.TransportBookingManager;
import team.easytravel.model.trip.Trip;
import team.easytravel.model.trip.TripManager;
import team.easytravel.model.userprefs.UserPrefs;
import team.easytravel.testutil.accommodationbooking.TypicalAccommodation;
import team.easytravel.testutil.trip.TripBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListAccommodationBookingCommand.
 */
class ListAccommodationBookingCommandTest {

    private Model model;
    private TripManager tripManagerSet;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        Trip newTrip = new TripBuilder().build();
        tripManagerSet = new TripManager();
        tripManagerSet.setTrip(newTrip);
        model = new ModelManager(new TransportBookingManager(),
                new FixedExpenseManager(), new PackingListManager(), new ActivityManager(),
                TypicalAccommodation.getTypicalAccommodationManager(), tripManagerSet,
                new UserPrefs());

        expectedModel = new ModelManager(new TransportBookingManager(),
                new FixedExpenseManager(), new PackingListManager(), new ActivityManager(),
                TypicalAccommodation.getTypicalAccommodationManager(), tripManagerSet,
                new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertAccommodationBookingCommandSuccess(new ListAccommodationBookingCommand(), model,
                ListAccommodationBookingCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showAccommodationBookingAtIndex(model, INDEX_FIRST);
        assertAccommodationBookingCommandSuccess(new ListAccommodationBookingCommand(), model,
                ListAccommodationBookingCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
