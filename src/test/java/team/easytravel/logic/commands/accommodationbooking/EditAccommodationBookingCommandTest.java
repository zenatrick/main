package team.easytravel.logic.commands.accommodationbooking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static team.easytravel.logic.commands.CommandTestUtil.DESC_ACC_JW;
import static team.easytravel.logic.commands.CommandTestUtil.DESC_ACC_RITZ;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ACCOMMODATION_JW_END_DAY;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ACCOMMODATION_JW_START_DAY;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ACCOMMODATION_JW_TITLE;
import static team.easytravel.logic.commands.CommandTestUtil.assertAccommodationBookingCommandFailure;
import static team.easytravel.logic.commands.CommandTestUtil.assertAccommodationBookingCommandSuccess;
import static team.easytravel.logic.commands.CommandTestUtil.showAccommodationBookingAtIndex;
import static team.easytravel.testutil.TypicalIndexes.INDEX_FIRST;
import static team.easytravel.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import team.easytravel.commons.core.Messages;
import team.easytravel.commons.core.index.Index;
import team.easytravel.model.Model;
import team.easytravel.model.ModelManager;
import team.easytravel.model.listmanagers.AccommodationBookingManager;
import team.easytravel.model.listmanagers.ActivityManager;
import team.easytravel.model.listmanagers.FixedExpenseManager;
import team.easytravel.model.listmanagers.PackingListManager;
import team.easytravel.model.listmanagers.TransportBookingManager;
import team.easytravel.model.listmanagers.accommodationbooking.AccommodationBooking;
import team.easytravel.model.trip.Trip;
import team.easytravel.model.trip.TripManager;
import team.easytravel.model.userprefs.UserPrefs;
import team.easytravel.testutil.TypicalAccommodation;
import team.easytravel.testutil.accommodationbooking.AccommodationBookingBuilder;
import team.easytravel.testutil.accommodationbooking.EditAccommodationBookingDescriptorBuilder;
import team.easytravel.testutil.trip.TripBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditAccommodationBookingCommand.
 */
class EditAccommodationBookingCommandTest {

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
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        AccommodationBooking editedAccommodationBooking = new AccommodationBookingBuilder().build();
        EditAccommodationBookingCommand.EditAccommodationBookingDescriptor descriptor =
                new EditAccommodationBookingDescriptorBuilder().build();

        EditAccommodationBookingCommand editAccommodationBookingCommand =
                new EditAccommodationBookingCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditAccommodationBookingCommand
                .MESSAGE_EDIT_ACCOMMODATION_BOOKING_SUCCESS, editedAccommodationBooking);

        ModelManager expectedModel = new ModelManager(new TransportBookingManager(),
                new FixedExpenseManager(), new PackingListManager(), new ActivityManager(),
                model.getAccommodationBookingManager(), tripManagerSet, new UserPrefs());

        expectedModel.setAccommodationBooking(model.getFilteredAccommodationBookingList().get(0),
                editedAccommodationBooking);

        assertAccommodationBookingCommandSuccess(editAccommodationBookingCommand, model, expectedMessage,
                expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastAccommodationBooking = Index.fromOneBased(model.getFilteredAccommodationBookingList().size());
        AccommodationBooking lastAccommodationBooking = model.getFilteredAccommodationBookingList()
                .get(indexLastAccommodationBooking.getZeroBased());

        AccommodationBookingBuilder accommodationBookingInList =
                new AccommodationBookingBuilder(lastAccommodationBooking);

        AccommodationBooking editedAccommodationBooking = accommodationBookingInList
                .withAccommodationName(VALID_ACCOMMODATION_JW_TITLE)
                .withStartDay(VALID_ACCOMMODATION_JW_START_DAY)
                .withEndDay(VALID_ACCOMMODATION_JW_END_DAY).build();

        EditAccommodationBookingCommand.EditAccommodationBookingDescriptor descriptor =
                new EditAccommodationBookingDescriptorBuilder().withAccommodationName(VALID_ACCOMMODATION_JW_TITLE)
                        .withStartDay(VALID_ACCOMMODATION_JW_START_DAY)
                        .withEndDay(VALID_ACCOMMODATION_JW_END_DAY).build();

        EditAccommodationBookingCommand editAccommodationBookingCommand =
                new EditAccommodationBookingCommand(indexLastAccommodationBooking, descriptor);

        String expectedMessage = String.format(EditAccommodationBookingCommand
                .MESSAGE_EDIT_ACCOMMODATION_BOOKING_SUCCESS, editedAccommodationBooking);

        ModelManager expectedModel = new ModelManager(new TransportBookingManager(),
                new FixedExpenseManager(), new PackingListManager(), new ActivityManager(),
                model.getAccommodationBookingManager(), tripManagerSet, new UserPrefs());

        expectedModel.setAccommodationBooking(lastAccommodationBooking, editedAccommodationBooking);

        assertAccommodationBookingCommandSuccess(editAccommodationBookingCommand, model, expectedMessage,
                expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditAccommodationBookingCommand editAccommodationBookingCommand =
                new EditAccommodationBookingCommand(INDEX_FIRST,
                        new EditAccommodationBookingCommand.EditAccommodationBookingDescriptor());

        AccommodationBooking editedAccommodationBooking = model.getFilteredAccommodationBookingList()
                .get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditAccommodationBookingCommand
                .MESSAGE_EDIT_ACCOMMODATION_BOOKING_SUCCESS, editedAccommodationBooking);

        Model expectedModel = new ModelManager(new TransportBookingManager(), new FixedExpenseManager(),
                new PackingListManager(), new ActivityManager(),
                new AccommodationBookingManager(model.getAccommodationBookingManager()),
                tripManagerSet, new UserPrefs());

        assertAccommodationBookingCommandSuccess(editAccommodationBookingCommand, model, expectedMessage,
                expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showAccommodationBookingAtIndex(model, INDEX_FIRST);

        AccommodationBooking accommodationBookingInFilteredList =
                model.getFilteredAccommodationBookingList().get(INDEX_FIRST.getZeroBased());
        AccommodationBooking editedAccommodationBooking =
                new AccommodationBookingBuilder(accommodationBookingInFilteredList)
                        .withAccommodationName(VALID_ACCOMMODATION_JW_TITLE).build();
        EditAccommodationBookingCommand editAccommodationBookingCommand = new EditAccommodationBookingCommand(
                INDEX_FIRST, new EditAccommodationBookingDescriptorBuilder()
                        .withAccommodationName(VALID_ACCOMMODATION_JW_TITLE).build());

        String expectedMessage = String.format(EditAccommodationBookingCommand
                .MESSAGE_EDIT_ACCOMMODATION_BOOKING_SUCCESS, editedAccommodationBooking);

        Model expectedModel = new ModelManager(new TransportBookingManager(), new FixedExpenseManager(),
                new PackingListManager(), new ActivityManager(),
                new AccommodationBookingManager(model.getAccommodationBookingManager()),
                tripManagerSet, new UserPrefs());

        expectedModel.setAccommodationBooking(model.getFilteredAccommodationBookingList().get(0),
                editedAccommodationBooking);

        assertAccommodationBookingCommandSuccess(editAccommodationBookingCommand, model, expectedMessage,
                expectedModel);
    }

    @Test
    public void execute_duplicateAccommodationBookingUnfilteredList_failure() {
        AccommodationBooking firstAccommodationBooking = model.getFilteredAccommodationBookingList()
                .get(INDEX_FIRST.getZeroBased());
        EditAccommodationBookingCommand.EditAccommodationBookingDescriptor descriptor =
                new EditAccommodationBookingDescriptorBuilder(firstAccommodationBooking).build();
        EditAccommodationBookingCommand editAccommodationBookingCommand =
                new EditAccommodationBookingCommand(INDEX_SECOND, descriptor);

        assertAccommodationBookingCommandFailure(editAccommodationBookingCommand, model,
                EditAccommodationBookingCommand.MESSAGE_DUPLICATE_ACCOMMODATION_BOOKING);
    }

    @Test
    public void execute_duplicateAccommodationBookingFilteredList_failure() {
        showAccommodationBookingAtIndex(model, INDEX_FIRST);

        // edit accommodation booking in filtered list into a duplicate in accommodation booking manager
        AccommodationBooking accommodationBookingInList = model.getAccommodationBookingManager()
                .getAccommodationBookingList().get(INDEX_SECOND.getZeroBased());
        EditAccommodationBookingCommand editAccommodationBookingCommand =
                new EditAccommodationBookingCommand(INDEX_FIRST,
                        new EditAccommodationBookingDescriptorBuilder(accommodationBookingInList).build());

        assertAccommodationBookingCommandFailure(editAccommodationBookingCommand, model,
                EditAccommodationBookingCommand.MESSAGE_DUPLICATE_ACCOMMODATION_BOOKING);
    }

    @Test
    public void execute_invalidAccommodationBookingIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAccommodationBookingList().size() + 1);
        EditAccommodationBookingCommand.EditAccommodationBookingDescriptor descriptor =
                new EditAccommodationBookingDescriptorBuilder()
                        .withAccommodationName(VALID_ACCOMMODATION_JW_TITLE).build();
        EditAccommodationBookingCommand editAccommodationBookingCommand =
                new EditAccommodationBookingCommand(outOfBoundIndex, descriptor);
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_DISPLAYED_INDEX_FORMAT,
                "accommodation booking");

        assertAccommodationBookingCommandFailure(editAccommodationBookingCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        final EditAccommodationBookingCommand standardCommand =
                new EditAccommodationBookingCommand(INDEX_FIRST, DESC_ACC_JW);

        // same values -> returns true
        EditAccommodationBookingCommand.EditAccommodationBookingDescriptor copyDescriptor =
                new EditAccommodationBookingCommand.EditAccommodationBookingDescriptor(DESC_ACC_JW);
        EditAccommodationBookingCommand commandWithSameValues =
                new EditAccommodationBookingCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearAccommodationBookingCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditAccommodationBookingCommand(INDEX_SECOND, DESC_ACC_RITZ)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditAccommodationBookingCommand(INDEX_FIRST, DESC_ACC_RITZ)));
    }

}
