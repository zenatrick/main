package team.easytravel.logic.commands.packinglist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import static team.easytravel.commons.core.Messages.MESSAGE_ITEMS_LISTED_OVERVIEW;
import static team.easytravel.logic.commands.CommandTestUtil.assertPackingListItemCommandSuccess;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import team.easytravel.model.Model;
import team.easytravel.model.ModelManager;
import team.easytravel.model.listmanagers.AccommodationBookingManager;
import team.easytravel.model.listmanagers.ActivityManager;
import team.easytravel.model.listmanagers.FixedExpenseManager;
import team.easytravel.model.listmanagers.TransportBookingManager;
import team.easytravel.model.listmanagers.packinglistitem.ItemCategoryContainsKeywordsPredicate;
import team.easytravel.model.trip.Trip;
import team.easytravel.model.trip.TripManager;
import team.easytravel.model.userprefs.UserPrefs;
import team.easytravel.testutil.packinglist.TypicalPackingListItem;
import team.easytravel.testutil.trip.TripBuilder;

public class FindItemCategoryCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        Trip newTrip = new TripBuilder().build();
        TripManager tripManagerSet = new TripManager();
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

    @Test
    public void equals() {
        ItemCategoryContainsKeywordsPredicate firstPredicate =
                new ItemCategoryContainsKeywordsPredicate(Collections.singletonList("first"));
        ItemCategoryContainsKeywordsPredicate secondPredicate =
                new ItemCategoryContainsKeywordsPredicate(Collections.singletonList("second"));

        FindItemCategoryCommand findFirstCommand = new FindItemCategoryCommand(firstPredicate);
        FindItemCategoryCommand findSecondCommand = new FindItemCategoryCommand(secondPredicate);

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindItemCategoryCommand findFirstCommandCopy = new FindItemCategoryCommand(firstPredicate);
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(findFirstCommand, 1);

        // null -> returns false
        assertNotEquals(findFirstCommand, null);

        // different person -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    @Test
    public void execute_zeroKeywords_noItemFound() {
        String expectedMessage = String.format(MESSAGE_ITEMS_LISTED_OVERVIEW, 0, "item(s)")
                + String.format("\nUse the \"%s\" command to show all packing list items",
                ListItemCommand.COMMAND_WORD);
        ItemCategoryContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindItemCategoryCommand command = new FindItemCategoryCommand(predicate);
        expectedModel.updateFilteredPackingList(predicate);
        assertPackingListItemCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPackingList());

    }

    @Test
    public void execute_multipleKeywords_multipleItemsFound() {
        String expectedMessage = String.format(MESSAGE_ITEMS_LISTED_OVERVIEW, 5, "item(s)")
                + String.format("\nUse the \"%s\" command to show all packing list items",
                ListItemCommand.COMMAND_WORD);
        ItemCategoryContainsKeywordsPredicate predicate = preparePredicate("toiletries clothes");
        FindItemCategoryCommand command = new FindItemCategoryCommand(predicate);
        expectedModel.updateFilteredPackingList(predicate);
        assertPackingListItemCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalPackingListItem.PACKING_LIST_UNDERWEAR,
                TypicalPackingListItem.PACKING_LIST_SHIRT,
                TypicalPackingListItem.PACKING_LIST_JEANS,
                TypicalPackingListItem.PACKING_LIST_CONDITIONER,
                TypicalPackingListItem.PACKING_LIST_SHAMPOO), model.getFilteredPackingList());
    }

    /**
     * Parses {@code userInput} into a {@code ItemCategoryContainsKeywordsPredicate}.
     */
    private ItemCategoryContainsKeywordsPredicate preparePredicate(String userInput) {
        return new ItemCategoryContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
