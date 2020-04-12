package team.easytravel.logic.commands.transportbooking;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static team.easytravel.logic.commands.CommandResult.Action.SWITCH_TAB_TRANSPORT;
import static team.easytravel.logic.commands.transportbooking.DeleteTransportBookingCommand.MESSAGE_DELETE_TRANSPORT_BOOKING_SUCCESS;
import static team.easytravel.testutil.Assert.assertThrows;
import static team.easytravel.testutil.TypicalIndexes.INDEX_FIRST;
import static team.easytravel.testutil.TypicalIndexes.INDEX_SECOND;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import team.easytravel.commons.core.index.Index;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.model.ModelStub;
import team.easytravel.model.listmanagers.ReadOnlyTransportBookingManager;
import team.easytravel.model.listmanagers.TransportBookingManager;
import team.easytravel.model.listmanagers.transportbooking.TransportBooking;
import team.easytravel.model.trip.DayScheduleEntry;
import team.easytravel.testutil.transportbooking.TransportBookingBuilder;

public class DeleteTransportBookingCommandTest {


    @Test
    public void execute_tripNotSet_throwsCommandException() {
        ModelStubNoTripSet modelStub = new ModelStubNoTripSet();
        assertThrows(CommandException.class, ()-> new DeleteTransportBookingCommand(Index.fromZeroBased(0))
                .execute(modelStub));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() throws CommandException {
        ModelStub modelStub = new ModelStubAcceptingTransportBookingAdded();
        TransportBooking transportBookingToDelete = modelStub.getFilteredTransportBookingList()
                .get(INDEX_FIRST.getZeroBased());
        CommandResult commandResult = new DeleteTransportBookingCommand(INDEX_FIRST)
                .execute(modelStub);
        String expectedMessage = String.format(String.format(MESSAGE_DELETE_TRANSPORT_BOOKING_SUCCESS,
                transportBookingToDelete),
                SWITCH_TAB_TRANSPORT);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        TransportBooking testTransport = new TransportBookingBuilder().withMode("plane").build();
        TransportBooking testTransporta = new TransportBookingBuilder().withMode("others").build();
        DeleteTransportBookingCommand deleteTransportBookingCommand = new DeleteTransportBookingCommand(INDEX_FIRST);
        AddTransportBookingCommand addTestTransportaCommand = new AddTransportBookingCommand(testTransporta);


        // same object -> returns true
        assertEquals(addTestTransportaCommand, addTestTransportaCommand);

        // same values -> returns true
        DeleteTransportBookingCommand deleteTransportBookingCommandCopy =
                new DeleteTransportBookingCommand(INDEX_FIRST);
        assertEquals(deleteTransportBookingCommandCopy, deleteTransportBookingCommand);

        // different types -> returns false
        assertNotEquals(1, deleteTransportBookingCommand);

        // null -> returns false
        assertNotEquals(null, deleteTransportBookingCommand);

        // different commands -> returns false
        assertNotEquals(addTestTransportaCommand, deleteTransportBookingCommand);
    }


    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        ModelStub modelStub = new ModelStubAcceptingTransportBookingAdded();
        assertThrows(CommandException.class, ()-> new DeleteTransportBookingCommand(INDEX_SECOND).execute(modelStub));
    }

    /**
     * A Model stub that does not have a trip set.
     */
    private class ModelStubNoTripSet extends ModelStub {
        @Override
        public boolean hasTrip() {
            return false;
        }
    }

    /**
     * A Model stub that always accept the transport booking being added.
     */
    private class ModelStubAcceptingTransportBookingAdded extends ModelStub {
        final ArrayList<TransportBooking> transportBookingsAdded = new ArrayList<>();

        @Override
        public boolean hasTransportBooking(TransportBooking transportBooking) {
            requireNonNull(transportBooking);
            return transportBookingsAdded.stream().anyMatch(transportBooking::isSame);
        }

        @Override
        public boolean hasTrip() {
            return true;
        }


        @Override
        public void addTransportBooking(TransportBooking transportBooking) {
            requireNonNull(transportBooking);
            transportBookingsAdded.add(transportBooking);
        }

        @Override
        public void scheduleTransport(TransportBooking transportBooking) {
            requireNonNull(transportBooking);
        }

        @Override
        public ReadOnlyTransportBookingManager getTransportBookingManager() {
            return new TransportBookingManager();
        }

        @Override
        public ObservableList<TransportBooking> getFilteredTransportBookingList() {
            List<TransportBooking> tb = new ArrayList<>();
            tb.add(new TransportBookingBuilder().withMode("plane").build());
            return FXCollections.observableList(tb);
        }

        @Override
        public void unscheduleTransport(DayScheduleEntry toDelete) {
            requireNonNull(toDelete);
        }

        @Override
        public void deleteTransportBooking(TransportBooking toDelete) {
            requireNonNull(toDelete);
            transportBookingsAdded.remove(toDelete);
        }

    }


}
