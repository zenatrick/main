package team.easytravel.logic.commands.activity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static team.easytravel.commons.core.Messages.MESSAGE_ITEMS_LISTED_OVERVIEW;
import static team.easytravel.logic.commands.CommandTestUtil.assertActivityCommandSuccess;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import team.easytravel.model.Model;
import team.easytravel.model.ModelManager;
import team.easytravel.model.listmanagers.AccommodationBookingManager;
import team.easytravel.model.listmanagers.FixedExpenseManager;
import team.easytravel.model.listmanagers.PackingListManager;
import team.easytravel.model.listmanagers.TransportBookingManager;
import team.easytravel.model.listmanagers.activity.ActivityTagContainsPredicate;
import team.easytravel.model.trip.Trip;
import team.easytravel.model.trip.TripManager;
import team.easytravel.model.userprefs.UserPrefs;
import team.easytravel.testutil.activity.TypicalActivity;
import team.easytravel.testutil.trip.TripBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code FindActivityTagCommand}.
 */
public class FindActivityTagCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        Trip newTrip = new TripBuilder().build();
        TripManager tripManagerSet = new TripManager();
        tripManagerSet.setTrip(newTrip);
        model = new ModelManager(new TransportBookingManager(),
                new FixedExpenseManager(), new PackingListManager(), TypicalActivity.getTypicalActivityManager(),
                new AccommodationBookingManager(), tripManagerSet,
                new UserPrefs());

        expectedModel = new ModelManager(new TransportBookingManager(),
                new FixedExpenseManager(), new PackingListManager(), TypicalActivity.getTypicalActivityManager(),
                new AccommodationBookingManager(), tripManagerSet,
                new UserPrefs());
    }

    @Test
    public void equals() {
        ActivityTagContainsPredicate firstPredicate =
                new ActivityTagContainsPredicate(Collections.singletonList("first"));
        ActivityTagContainsPredicate secondPredicate =
                new ActivityTagContainsPredicate(Collections.singletonList("second"));

        FindActivityTagCommand findFirstCommand = new FindActivityTagCommand(firstPredicate);
        FindActivityTagCommand findSecondCommand = new FindActivityTagCommand(secondPredicate);

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindActivityTagCommand findFirstCommandCopy = new FindActivityTagCommand(firstPredicate);
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // different person -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    @Test
    public void execute_zeroKeywords_noActivityFound() {
        String expectedMessage = String.format(MESSAGE_ITEMS_LISTED_OVERVIEW, 0, "activities")
                + String.format("\nUse the \"%s\" command to show all activities", ListActivityCommand.COMMAND_WORD);
        ActivityTagContainsPredicate predicate = preparePredicate(" ");
        FindActivityTagCommand command = new FindActivityTagCommand(predicate);
        expectedModel.updateFilteredActivityList(predicate);
        assertActivityCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredActivityList());
    }

    @Test
    public void execute_multipleKeywords_multipleActivityFound() {
        String expectedMessage = String.format(MESSAGE_ITEMS_LISTED_OVERVIEW, 1, "activities")
                + String.format("\nUse the \"%s\" command to show all activities", ListActivityCommand.COMMAND_WORD);
        ActivityTagContainsPredicate predicate = preparePredicate("relaxation");
        FindActivityTagCommand command = new FindActivityTagCommand(predicate);
        expectedModel.updateFilteredActivityList(predicate);
        assertActivityCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(TypicalActivity.ACTIVITY_DISNEYLAND), model.getFilteredActivityList());
    }

    /**
     * Parses {@code userInput} into a {@code ActivityTagContainsPredicate}.
     */
    private ActivityTagContainsPredicate preparePredicate(String userInput) {
        return new ActivityTagContainsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
