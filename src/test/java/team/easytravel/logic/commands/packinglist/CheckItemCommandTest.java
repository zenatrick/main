package team.easytravel.logic.commands.packinglist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static team.easytravel.logic.commands.CommandTestUtil.assertPackingListItemCommandSuccess;
import static team.easytravel.testutil.TypicalIndexes.INDEX_FIRST;
import static team.easytravel.testutil.TypicalIndexes.INDEX_FORTH;
import static team.easytravel.testutil.TypicalIndexes.INDEX_SECOND;
import static team.easytravel.testutil.TypicalIndexes.INDEX_THIRD;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        StringBuilder sb = new StringBuilder().append("Checked items \n");

        sb.append(checkedItem.toString()).append("\n");

        List<Index> indexes = new ArrayList<>();
        indexes.add(INDEX_FIRST);
        CheckItemCommand checkItemCommand = new CheckItemCommand(indexes);

        String expectedMessage = String.format(CheckItemCommand.MESSAGE_PACKED_ITEM_SUCCESS, sb);

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

        StringBuilder sb = new StringBuilder().append("Checked items \n");
        sb.append(checkedItem1.toString()).append("\n");
        sb.append(checkedItem2.toString()).append("\n");
        sb.append(checkedItem3.toString()).append("\n");

        String expectedMessage = String.format(CheckItemCommand.MESSAGE_PACKED_ITEM_SUCCESS, sb);

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
        assertEquals(checkFirstCommand, checkFirstCommand);

        // same values -> returns true
        CheckItemCommand checkFirstCommandCopy = new CheckItemCommand(indexList);
        assertEquals(checkFirstCommand, checkFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, checkFirstCommand);

        // null -> returns false
        assertNotEquals(null, checkFirstCommand);

        // different person -> returns false
        assertNotEquals(checkFirstCommand, checkSecondCommand);
    }
}
