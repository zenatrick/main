package team.easytravel.logic.commands.packinglist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static team.easytravel.logic.commands.CommandTestUtil.assertPackingListItemCommandFailure;
import static team.easytravel.logic.commands.CommandTestUtil.assertPackingListItemCommandSuccess;
import static team.easytravel.logic.commands.CommandTestUtil.showPackingListItemAtIndex;
import static team.easytravel.testutil.TypicalIndexes.INDEX_FIFTH;
import static team.easytravel.testutil.TypicalIndexes.INDEX_FIRST;
import static team.easytravel.testutil.TypicalIndexes.INDEX_SECOND;
import static team.easytravel.testutil.TypicalIndexes.INDEX_SIXTH;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import team.easytravel.commons.core.Messages;
import team.easytravel.commons.core.index.Index;
import team.easytravel.model.Model;
import team.easytravel.model.ModelManager;
import team.easytravel.model.listmanagers.AccommodationBookingManager;
import team.easytravel.model.listmanagers.ActivityManager;
import team.easytravel.model.listmanagers.FixedExpenseManager;
import team.easytravel.model.listmanagers.TransportBookingManager;
import team.easytravel.model.listmanagers.packinglistitem.PackingListItem;
import team.easytravel.model.trip.Trip;
import team.easytravel.model.trip.TripManager;
import team.easytravel.model.userprefs.UserPrefs;
import team.easytravel.testutil.packinglist.TypicalPackingListItem;
import team.easytravel.testutil.trip.TripBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteItemCommand}.
 */
public class DeleteItemCommandTest {

    private Model model;
    private TripManager tripManagerSet;

    @BeforeEach
    public void setUp() {
        Trip newTrip = new TripBuilder().build();
        tripManagerSet = new TripManager();
        tripManagerSet.setTrip(newTrip);
        model = new ModelManager(new TransportBookingManager(),
                new FixedExpenseManager(), TypicalPackingListItem.getTypicalPackingListManager(),
                new ActivityManager(), new AccommodationBookingManager(), tripManagerSet,
                new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        PackingListItem itemToDelete = model.getFilteredPackingList().get(INDEX_FIRST.getZeroBased());
        DeleteItemCommand deleteItemCommand = new DeleteItemCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteItemCommand.MESSAGE_DELETE_ITEM_SUCCESS, itemToDelete);

        ModelManager expectedModel = new ModelManager(new TransportBookingManager(),
            new FixedExpenseManager(), model.getPackingListManager(), new ActivityManager(),
            new AccommodationBookingManager(), tripManagerSet,
            new UserPrefs());
        expectedModel.deletePackingListItem(itemToDelete);

        assertPackingListItemCommandSuccess(deleteItemCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPackingList().size() + 1);
        DeleteItemCommand deleteCommand = new DeleteItemCommand(outOfBoundIndex);
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_DISPLAYED_INDEX_FORMAT, "item");

        assertPackingListItemCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPackingListItemAtIndex(model, INDEX_FIRST);

        PackingListItem itemToDelete = model.getFilteredPackingList().get(INDEX_FIRST.getZeroBased());
        DeleteItemCommand deleteCommand = new DeleteItemCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteItemCommand.MESSAGE_DELETE_ITEM_SUCCESS, itemToDelete);

        ModelManager expectedModel = new ModelManager(new TransportBookingManager(),
                new FixedExpenseManager(), model.getPackingListManager(), new ActivityManager(),
                new AccommodationBookingManager(), tripManagerSet,
                new UserPrefs());

        expectedModel.deletePackingListItem(itemToDelete);
        showNoItem(expectedModel);

        assertPackingListItemCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPackingListItemAtIndex(model, INDEX_FIFTH);

        Index outOfBoundIndex = INDEX_SIXTH;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPackingListManager()
            .getUniquePackingList().size());

        DeleteItemCommand deleteCommand = new DeleteItemCommand(outOfBoundIndex);
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_DISPLAYED_INDEX_FORMAT, "item");

        assertPackingListItemCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        DeleteItemCommand deleteFirstCommand = new DeleteItemCommand(INDEX_FIRST);
        DeleteItemCommand deleteSecondCommand = new DeleteItemCommand(INDEX_SECOND);

        // same object -> returns true
        assertEquals(deleteFirstCommand, deleteFirstCommand);

        // same values -> returns true
        DeleteItemCommand deleteFirstCommandCopy = new DeleteItemCommand(INDEX_FIRST);
        assertEquals(deleteFirstCommand, deleteFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, deleteFirstCommand);

        // null -> returns false
        assertNotEquals(null, deleteFirstCommand);

        // different person -> returns false
        assertNotEquals(deleteFirstCommand, deleteSecondCommand);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoItem(Model model) {
        model.updateFilteredPackingList(p -> false);

        assertTrue(model.getFilteredPackingList().isEmpty());
    }
}

