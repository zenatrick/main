package team.easytravel.logic.commands.transportbooking;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static team.easytravel.logic.commands.CommandResult.Action.SWITCH_TAB_TRANSPORT;
import static team.easytravel.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import team.easytravel.logic.commands.CommandResult;
import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.model.ModelStub;
import team.easytravel.model.listmanagers.ReadOnlyTransportBookingManager;
import team.easytravel.model.listmanagers.TransportBookingManager;
import team.easytravel.model.listmanagers.transportbooking.TransportBooking;
import team.easytravel.testutil.transportbooking.TransportBookingBuilder;

public class AddTransportBookingCommandTest {

    @Test
    public void constructor_nullTransportBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTransportBookingCommand(null));
    }

    @Test

    public void execute_addTransportBookingWithoutTripSet_throwsCommandException() {
        ModelStubNoTripSet modelStub = new ModelStubNoTripSet();
        TransportBooking validTransportBooking = new TransportBookingBuilder().build();
        assertThrows(CommandException.class, () -> new AddTransportBookingCommand(validTransportBooking)
                .execute(modelStub));

    }

    @Test
    public void execute_transportBookingAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTransportBookingAdded modelStub = new ModelStubAcceptingTransportBookingAdded();
        TransportBooking validTransportBooking = new TransportBookingBuilder().build();

        CommandResult commandResult = new AddTransportBookingCommand(validTransportBooking)
                .execute(modelStub);



        assertEquals(String.format(AddTransportBookingCommand.MESSAGE_SUCCESS, validTransportBooking,
                SWITCH_TAB_TRANSPORT),
                commandResult.getFeedbackToUser());
        assertEquals(Collections.singletonList(validTransportBooking), modelStub.transportBookingsAdded);
    }


    @Test
    public void execute_duplicateTransportBooking_throwsCommandException() {
        TransportBooking validTransportBooking = new TransportBookingBuilder().build();
        ModelStubAcceptingTransportBookingAdded modelStub = new ModelStubAcceptingTransportBookingAdded();
        modelStub.addTransportBooking(validTransportBooking);
        assertThrows(CommandException.class, () -> new AddTransportBookingCommand(validTransportBooking)
                .execute(modelStub));
    }

    @Test
    public void equals() {
        TransportBooking testTransportPlane = new TransportBookingBuilder().withMode("plane").build();
        TransportBooking testTransportOthers = new TransportBookingBuilder().withMode("others").build();
        AddTransportBookingCommand addTestTransportCommand = new AddTransportBookingCommand(testTransportPlane);
        AddTransportBookingCommand addTestTransportOthersCommand = new AddTransportBookingCommand(testTransportOthers);


        // same object -> returns true
        assertEquals(addTestTransportCommand, addTestTransportCommand);

        // same values -> returns true
        AddTransportBookingCommand addTestTransportCommandCopy = new AddTransportBookingCommand(testTransportPlane);
        assertEquals(addTestTransportCommandCopy, addTestTransportCommand);

        // different types -> returns false
        assertNotEquals(1, addTestTransportCommand);

        // null -> returns false
        assertNotEquals(null, addTestTransportCommand);

        // different person -> returns false
        assertNotEquals(addTestTransportOthersCommand, addTestTransportCommand);
    }

    /**
     * A Model stub that has no trip set.
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
    }
}
