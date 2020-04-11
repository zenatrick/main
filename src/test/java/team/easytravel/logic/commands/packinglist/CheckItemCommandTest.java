package team.easytravel.logic.commands.packinglist;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static team.easytravel.logic.commands.CommandTestUtil.assertPackingListItemCommandFailure;
import static team.easytravel.logic.commands.CommandTestUtil.assertPackingListItemCommandSuccess;
import static team.easytravel.logic.commands.CommandTestUtil.showPackingListItemAtIndex;
import static team.easytravel.testutil.TypicalIndexes.INDEX_FIRST;
import static team.easytravel.testutil.TypicalIndexes.INDEX_FORTH;
import static team.easytravel.testutil.TypicalIndexes.INDEX_SECOND;
import static team.easytravel.testutil.TypicalIndexes.INDEX_THIRD;

import java.util.ArrayList;
import java.util.List;

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
import team.easytravel.model.listmanagers.UserPrefs;
import team.easytravel.model.listmanagers.packinglistitem.PackingListItem;
import team.easytravel.model.trip.Trip;
import team.easytravel.model.trip.TripManager;
import team.easytravel.testutil.packinglist.TypicalPackingListItem;
import team.easytravel.testutil.trip.TripBuilder;

/**
 * The type Check item command test.
 */
public class CheckItemCommandTest {
    private Model model;
    private TripManager tripManagerSet;

    /**
     * Sets up.
     */
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

    /**
     * Execute valid index unfiltered list success.
     */
    @Test
    public void execute_validIndexUnfilteredList_success() {
        PackingListItem itemToCheck = model.getFilteredPackingList().get(INDEX_FIRST.getZeroBased());
        PackingListItem checkedItem = new PackingListItem(itemToCheck.getItemName(), itemToCheck.getQuantity(),
                itemToCheck.getItemCategory(), true);
        List<Index> indexes = new ArrayList<>();
        indexes.add(INDEX_FIRST);
        CheckItemCommand checkItemCommand = new CheckItemCommand(indexes);

        String expectedMessage = String.format(CheckItemCommand.MESSAGE_PACKED_ITEM_SUCCESS, checkedItem);

        ModelManager expectedModel = new ModelManager(new TransportBookingManager(),
                new FixedExpenseManager(), model.getPackingListManager(), new ActivityManager(),
                new AccommodationBookingManager(), tripManagerSet,
                new UserPrefs());
        expectedModel.setPackingListItem(itemToCheck, checkedItem);

        assertPackingListItemCommandSuccess(checkItemCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Execute valid indexes unfiltered list success.
     */
    @Test
    public void execute_validIndexesUnfilteredList_success() {
        PackingListItem itemToCheck1 = model.getFilteredPackingList().get(INDEX_FIRST.getZeroBased());
        PackingListItem itemToCheck2 = model.getFilteredPackingList().get(INDEX_SECOND.getZeroBased());
        PackingListItem itemToCheck3 = model.getFilteredPackingList().get(INDEX_THIRD.getZeroBased());
        PackingListItem checkedItem1 = new PackingListItem(itemToCheck1.getItemName(), itemToCheck1.getQuantity(),
            itemToCheck1.getItemCategory(), true);
        PackingListItem checkedItem2 = new PackingListItem(itemToCheck2.getItemName(), itemToCheck2.getQuantity(),
            itemToCheck2.getItemCategory(), true);
        PackingListItem checkedItem3 = new PackingListItem(itemToCheck3.getItemName(), itemToCheck3.getQuantity(),
            itemToCheck3.getItemCategory(), true);

        List<Index> indexes = new ArrayList<>();
        indexes.add(INDEX_FIRST);
        indexes.add(INDEX_SECOND);
        indexes.add(INDEX_THIRD);
        CheckItemCommand checkItemCommand = new CheckItemCommand(indexes);

        StringBuilder sb = new StringBuilder();
        sb.append(checkedItem1.toString()).append("\n");
        sb.append(checkedItem2.toString()).append("\n");
        sb.append(checkedItem3.toString()).append("\n");

        String expectedMessage = String.format(CheckItemCommand.MESSAGE_PACKED_ITEM_SUCCESS, sb.toString());

        ModelManager expectedModel = new ModelManager(new TransportBookingManager(),
            new FixedExpenseManager(), model.getPackingListManager(), new ActivityManager(),
            new AccommodationBookingManager(), tripManagerSet,
            new UserPrefs());
        expectedModel.setPackingListItem(itemToCheck1, checkedItem1);
        expectedModel.setPackingListItem(itemToCheck2, checkedItem2);
        expectedModel.setPackingListItem(itemToCheck3, checkedItem3);

        assertPackingListItemCommandSuccess(checkItemCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Execute invalid index filtered list throws command exception.
     */
    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPackingListItemAtIndex(model, INDEX_THIRD);

        Index outOfBoundIndex = INDEX_FORTH;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPackingListManager().
            getUniquePackingList().size());

        List<Index> indexes = new ArrayList<>();
        indexes.add(outOfBoundIndex);

        CheckItemCommand checkItemCommand = new CheckItemCommand(indexes);
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_DISPLAYED_INDEX_FORMAT, "Item");

        assertPackingListItemCommandFailure(checkItemCommand, model, expectedMessage);
    }

    /**
     * Equals.
     */
    @Test
    public void equals() {
        List<Index> indexList = new ArrayList<>();
        indexList.add(INDEX_FIRST);
        indexList.add(INDEX_SECOND);
        CheckItemCommand checkFirstCommand = new CheckItemCommand(indexList);

        List<Index> indexList1 = new ArrayList<>();
        indexList1.add(INDEX_THIRD);
        indexList1.add(INDEX_FORTH);

        CheckItemCommand checkSecondCommand = new CheckItemCommand(indexList1);

        // same object -> returns true
        assertTrue(checkFirstCommand.equals(checkFirstCommand));

        // same values -> returns true
        CheckItemCommand checkFirstCommandCopy = new CheckItemCommand(indexList);
        assertTrue(checkFirstCommand.equals(checkFirstCommandCopy));

        // different types -> returns false
        assertFalse(checkFirstCommand.equals(1));

        // null -> returns false
        assertFalse(checkFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(checkFirstCommand.equals(checkSecondCommand));
    }
}
