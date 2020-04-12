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

public class UncheckItemCommandTest {
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
        PackingListItem itemToUncheck = model.getFilteredPackingList().get(INDEX_FIRST.getZeroBased());
        PackingListItem uncheckedItem = new PackingListItem(itemToUncheck.getItemName(), itemToUncheck.getQuantity(),
                itemToUncheck.getItemCategory(), false);
        StringBuilder sb = new StringBuilder().append("Unchecked items \n");

        sb.append(uncheckedItem.toString()).append("\n");

        List<Index> indexes = new ArrayList<>();
        indexes.add(INDEX_FIRST);
        UncheckItemCommand uncheckItemCommand = new UncheckItemCommand(indexes);

        String expectedMessage = String.format(UncheckItemCommand.MESSAGE_UNPACKED_ITEM_SUCCESS, sb);

        ModelManager expectedModel = new ModelManager(new TransportBookingManager(),
                new FixedExpenseManager(), model.getPackingListManager(), new ActivityManager(),
                new AccommodationBookingManager(), tripManagerSet,
                new UserPrefs());
        expectedModel.setPackingListItem(itemToUncheck, uncheckedItem);

        assertPackingListItemCommandSuccess(uncheckItemCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Execute valid indexes unfiltered list success.
     */
    @Test
    public void execute_validIndexesUnfilteredList_success() {
        PackingListItem itemToUncheck1 = model.getFilteredPackingList().get(INDEX_FIRST.getZeroBased());
        PackingListItem itemToUncheck2 = model.getFilteredPackingList().get(INDEX_SECOND.getZeroBased());
        PackingListItem itemToUncheck3 = model.getFilteredPackingList().get(INDEX_THIRD.getZeroBased());
        PackingListItem uncheckedItem1 = new PackingListItem(itemToUncheck1.getItemName(), itemToUncheck1.getQuantity(),
                itemToUncheck1.getItemCategory(), false);
        PackingListItem uncheckedItem2 = new PackingListItem(itemToUncheck2.getItemName(), itemToUncheck2.getQuantity(),
                itemToUncheck2.getItemCategory(), false);
        PackingListItem uncheckedItem3 = new PackingListItem(itemToUncheck3.getItemName(), itemToUncheck3.getQuantity(),
                itemToUncheck3.getItemCategory(), false);

        List<Index> indexes = new ArrayList<>();
        indexes.add(INDEX_FIRST);
        indexes.add(INDEX_SECOND);
        indexes.add(INDEX_THIRD);
        UncheckItemCommand uncheckItemCommand = new UncheckItemCommand(indexes);

        StringBuilder sb = new StringBuilder().append("Unchecked items \n");
        sb.append(uncheckedItem1.toString()).append("\n");
        sb.append(uncheckedItem2.toString()).append("\n");
        sb.append(uncheckedItem3.toString()).append("\n");

        String expectedMessage = String.format(UncheckItemCommand.MESSAGE_UNPACKED_ITEM_SUCCESS, sb);

        ModelManager expectedModel = new ModelManager(new TransportBookingManager(),
                new FixedExpenseManager(), model.getPackingListManager(), new ActivityManager(),
                new AccommodationBookingManager(), tripManagerSet,
                new UserPrefs());
        expectedModel.setPackingListItem(itemToUncheck1, uncheckedItem1);
        expectedModel.setPackingListItem(itemToUncheck2, uncheckedItem2);
        expectedModel.setPackingListItem(itemToUncheck3, uncheckedItem3);

        assertPackingListItemCommandSuccess(uncheckItemCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Equals.
     */
    @Test
    public void equals() {
        List<Index> indexList = new ArrayList<>();
        indexList.add(INDEX_FIRST);
        indexList.add(INDEX_SECOND);
        UncheckItemCommand uncheckFirstCommand = new UncheckItemCommand(indexList);

        List<Index> indexList1 = new ArrayList<>();
        indexList1.add(INDEX_THIRD);
        indexList1.add(INDEX_FORTH);

        UncheckItemCommand uncheckSecondCommand = new UncheckItemCommand(indexList1);

        // same object -> returns true
        assertEquals(uncheckFirstCommand, uncheckFirstCommand);

        // same values -> returns true
        UncheckItemCommand uncheckFirstCommandCopy = new UncheckItemCommand(indexList);
        assertEquals(uncheckFirstCommand, uncheckFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, uncheckFirstCommand);

        // null -> returns false
        assertNotEquals(null, uncheckFirstCommand);

        // different person -> returns false
        assertNotEquals(uncheckFirstCommand, uncheckSecondCommand);
    }
}
