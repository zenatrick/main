package team.easytravel.logic.commands.accommodationbooking;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static team.easytravel.logic.commands.CommandTestUtil.assertAccommodationBookingCommandFailure;
import static team.easytravel.logic.commands.CommandTestUtil.assertAccommodationBookingCommandSuccess;
import static team.easytravel.logic.commands.CommandTestUtil.showAccommodationBookingAtIndex;
import static team.easytravel.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import team.easytravel.commons.core.Messages;
import team.easytravel.commons.core.index.Index;
import team.easytravel.model.Model;
import team.easytravel.model.ModelManager;
import team.easytravel.model.listmanagers.ActivityManager;
import team.easytravel.model.listmanagers.FixedExpenseManager;
import team.easytravel.model.listmanagers.PackingListManager;
import team.easytravel.model.listmanagers.TransportBookingManager;
import team.easytravel.model.listmanagers.UserPrefs;
import team.easytravel.model.listmanagers.accommodationbooking.AccommodationBooking;
import team.easytravel.model.trip.Trip;
import team.easytravel.model.trip.TripManager;
import team.easytravel.testutil.TypicalAccommodation;
import team.easytravel.testutil.trip.TripBuilder;

/**
 * Contains integration tests (interaction with the Model and unit tests for
 * {@code DeleteAccommodationBookingCommand}.
 */
class DeleteAccommodationBookingCommandTest {

    private Model model;
    private TripManager tripManagerSet;

    @BeforeEach
    public void setUp() {
        Trip newTrip = new TripBuilder().build();
        tripManagerSet = new TripManager();
        tripManagerSet.setTrip(newTrip);
        model = new ModelManager(new TransportBookingManager(),
                new FixedExpenseManager(), new PackingListManager(), new ActivityManager(),
                TypicalAccommodation.getTypicalAccommodationManager(), tripManagerSet, new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        AccommodationBooking accommodationBookingToDelete = model.getFilteredAccommodationBookingList()
                .get(INDEX_FIRST.getZeroBased());
        DeleteAccommodationBookingCommand deleteAccommodationBookingCommand =
                new DeleteAccommodationBookingCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteAccommodationBookingCommand
                .MESSAGE_DELETE_ACCOMMODATION_BOOKING_SUCCESS, accommodationBookingToDelete);

        ModelManager expectedModel = new ModelManager(new TransportBookingManager(),
                new FixedExpenseManager(), new PackingListManager(), new ActivityManager(),
                model.getAccommodationBookingManager(), tripManagerSet, new UserPrefs());

        expectedModel.deleteAccommodationBooking(accommodationBookingToDelete);

        assertAccommodationBookingCommandSuccess(deleteAccommodationBookingCommand, model, expectedMessage,
                expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAccommodationBookingList().size() + 1);
        DeleteAccommodationBookingCommand deleteAccommodationBookingCommand =
                new DeleteAccommodationBookingCommand(outOfBoundIndex);

        String expectedMessage = String.format(Messages.MESSAGE_INVALID_DISPLAYED_INDEX_FORMAT,
                "accommodation booking");

        assertAccommodationBookingCommandFailure(deleteAccommodationBookingCommand, model,
                expectedMessage);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showAccommodationBookingAtIndex(model, INDEX_FIRST);

        AccommodationBooking accommodationBookingToDelete = model.getFilteredAccommodationBookingList()
                .get(INDEX_FIRST.getZeroBased());
        DeleteAccommodationBookingCommand deleteAccommodationBookingCommand =
                new DeleteAccommodationBookingCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteAccommodationBookingCommand
                .MESSAGE_DELETE_ACCOMMODATION_BOOKING_SUCCESS, accommodationBookingToDelete);

        Model expectedModel = new ModelManager(new TransportBookingManager(),
                new FixedExpenseManager(), new PackingListManager(), new ActivityManager(),
                model.getAccommodationBookingManager(), tripManagerSet, new UserPrefs());

        expectedModel.deleteAccommodationBooking(accommodationBookingToDelete);
        showNoAccommodationBooking(expectedModel);

        assertAccommodationBookingCommandSuccess(deleteAccommodationBookingCommand, model, expectedMessage,
                expectedModel);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoAccommodationBooking(Model model) {
        model.updateFilteredAccommodationBookingList(p -> false);

        assertTrue(model.getFilteredAccommodationBookingList().isEmpty());
    }

}
