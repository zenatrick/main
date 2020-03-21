package seedu.address.logic.commands.activitytest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showActivityAtIndex;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.activity.DeleteActivityCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.listmanagers.UserPrefs;

import seedu.address.model.listmanagers.ActivityManager;
import seedu.address.model.listmanagers.AccommodationBookingManager;
import seedu.address.model.listmanagers.FixedExpenseManager;
import seedu.address.model.listmanagers.PackingListManager;
import seedu.address.model.listmanagers.TransportBookingManager;
import seedu.address.model.listmanagers.activity.Activity;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteActivityTest {

    private Model model = new ModelManager(getTypicalAddressBook(),
            new TransportBookingManager(), new FixedExpenseManager(), new PackingListManager(),
            new ActivityManager(),
            new AccommodationBookingManager(),
            new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Activity activityToDelete = model.getFilteredActivityList().get(INDEX_FIRST.getZeroBased());
        DeleteActivityCommand deleteCommand = new DeleteActivityCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteActivityCommand.MESSAGE_DELETE_ACTIVITY_SUCCESS,
                activityToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(),
                model.getTransportBookingManager(), model.getFixedExpenseManager(),
                model.getPackingListManager(), new ActivityManager(),
                model.getAccommodationBookingManager(),new UserPrefs());

        expectedModel.deleteActivity(activityToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredActivityList().size() + 1);
        DeleteActivityCommand deleteCommand = new DeleteActivityCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showActivityAtIndex(model, INDEX_FIRST);

        Activity activityToDelete = model.getFilteredActivityList().get(INDEX_FIRST.getZeroBased());
        DeleteActivityCommand deleteCommand = new DeleteActivityCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteActivityCommand.MESSAGE_DELETE_ACTIVITY_SUCCESS, activityToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(),
                model.getTransportBookingManager(), model.getFixedExpenseManager(),
                model.getPackingListManager(), new ActivityManager(),
                model.getAccommodationBookingManager(),new UserPrefs());

        expectedModel.deleteActivity(activityToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showActivityAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getActivityManager().getActivityList().size());

        DeleteActivityCommand deleteCommand = new DeleteActivityCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteActivityCommand deleteFirstCommand = new DeleteActivityCommand(INDEX_FIRST);
        DeleteActivityCommand deleteSecondCommand = new DeleteActivityCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteActivityCommand deleteFirstCommandCopy = new DeleteActivityCommand(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredActivityList(p -> false);

        assertTrue(model.getFilteredActivityList().isEmpty());
    }
}

