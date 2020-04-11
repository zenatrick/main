package team.easytravel.logic.commands.packinglist;

import static team.easytravel.logic.commands.CommandTestUtil.assertPackingListItemCommandSuccess;
import static team.easytravel.logic.commands.CommandTestUtil.showPackingListItemAtIndex;
import static team.easytravel.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import team.easytravel.model.Model;
import team.easytravel.model.ModelManager;
import team.easytravel.model.listmanagers.AccommodationBookingManager;
import team.easytravel.model.listmanagers.ActivityManager;
import team.easytravel.model.listmanagers.FixedExpenseManager;
import team.easytravel.model.listmanagers.TransportBookingManager;
import team.easytravel.model.trip.Trip;
import team.easytravel.model.trip.TripManager;
import team.easytravel.model.userprefs.UserPrefs;
import team.easytravel.testutil.packinglist.TypicalPackingListItem;
import team.easytravel.testutil.trip.TripBuilder;

/**
 * The type List item command test.
 */
public class ListItemCommandTest {
    private Model model;
    private TripManager tripManagerSet;
    private Model expectedModel;

    /**
     * Sets up.
     */
    @BeforeEach
    public void setUp() {
        Trip newTrip = new TripBuilder().build();
        tripManagerSet = new TripManager();
        tripManagerSet.setTrip(newTrip);
        model = new ModelManager(new TransportBookingManager(),
                new FixedExpenseManager(), TypicalPackingListItem.getTypicalPackingListManager(), new ActivityManager(),
                new AccommodationBookingManager(), tripManagerSet,
                new UserPrefs());

        expectedModel = new ModelManager(new TransportBookingManager(),
                new FixedExpenseManager(), TypicalPackingListItem.getTypicalPackingListManager(), new ActivityManager(),
                new AccommodationBookingManager(), tripManagerSet,
                new UserPrefs());
    }

    /**
     * Execute list is not filtered shows same list.
     */
    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertPackingListItemCommandSuccess(new ListItemCommand(),
                model, ListItemCommand.MESSAGE_SUCCESS, expectedModel);
    }

    /**
     * Execute list is filtered shows everything.
     */
    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPackingListItemAtIndex(model, INDEX_FIRST);
        assertPackingListItemCommandSuccess(new ListItemCommand(),
                model, ListItemCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
