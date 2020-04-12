package team.easytravel.logic.commands.transportbooking;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static team.easytravel.commons.util.CollectionUtil.requireAllNonNull;
import static team.easytravel.logic.commands.CommandResult.Action.SWITCH_TAB_TRANSPORT;
import static team.easytravel.logic.commands.transportbooking.EditTransportBookingCommand.MESSAGE_EDIT_TRANSPORT_BOOKING_SUCCESS;
import static team.easytravel.testutil.Assert.assertThrows;
import static team.easytravel.testutil.TypicalIndexes.INDEX_FIRST;
import static team.easytravel.testutil.TypicalIndexes.INDEX_SECOND;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.logic.commands.transportbooking.EditTransportBookingCommand.EditTransportBookingDescriptor;
import team.easytravel.model.ModelStub;
import team.easytravel.model.listmanagers.ReadOnlyTransportBookingManager;
import team.easytravel.model.listmanagers.TransportBookingManager;
import team.easytravel.model.listmanagers.transportbooking.TransportBooking;
import team.easytravel.model.trip.DayScheduleEntry;
import team.easytravel.model.trip.Trip;
import team.easytravel.model.trip.TripManager;
import team.easytravel.testutil.transportbooking.EditTransportBookingDescriptorBuilder;
import team.easytravel.testutil.transportbooking.TransportBookingBuilder;
import team.easytravel.testutil.trip.TripBuilder;

public class EditTransportBookingCommandTest {


    private TripManager tripManagerSet;

    @BeforeEach
    public void setUp() {
        Trip newTrip = new TripBuilder().build();
        tripManagerSet = new TripManager();
        tripManagerSet.setTrip(newTrip);
    }


    @Test
    public void execute_tripNotSet_throwsCommandException() {
        TransportBooking editedTransportBooking = new TransportBookingBuilder().build();
        EditTransportBookingDescriptor descriptor = new EditTransportBookingDescriptorBuilder(editedTransportBooking)
                .build();
        ModelStubNoTripSet modelStub = new ModelStubNoTripSet();
        assertThrows(CommandException.class, () -> new EditTransportBookingCommand(INDEX_FIRST, descriptor)
                .execute(modelStub));
    }

    @Test
    public void execute_noDescriptorProvided_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditTransportBookingCommand(INDEX_FIRST,
                null));
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() throws CommandException {
        ModelStubAcceptingTransportBookingAdded modelStub =
                new ModelStubAcceptingTransportBookingAdded();
        TransportBooking editedTransportBooking = new TransportBookingBuilder().build();
        EditTransportBookingDescriptor descriptor = new EditTransportBookingDescriptorBuilder(editedTransportBooking)
                .build();
        CommandResult commandResult = new EditTransportBookingCommand(INDEX_FIRST, descriptor)
                .execute(modelStub);

        String expectedMessage =
                String.format(MESSAGE_EDIT_TRANSPORT_BOOKING_SUCCESS, editedTransportBooking,
                        SWITCH_TAB_TRANSPORT);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(Collections.singletonList(editedTransportBooking), modelStub.transportBookingsAdded);
    }


    @Test
    public void execute_allFieldsIndexMoreThanListSize_throwsCommandException() {
        ModelStubAcceptingTransportBookingAdded modelStub =
                new ModelStubAcceptingTransportBookingAdded();
        TransportBooking editedTransportBooking = new TransportBookingBuilder().build();
        EditTransportBookingDescriptor descriptor = new EditTransportBookingDescriptorBuilder(editedTransportBooking)
                .build();

        assertThrows(CommandException.class, () -> new EditTransportBookingCommand(INDEX_SECOND, descriptor)
                .execute(modelStub));
    }

    @Test
    public void equals() {
        TransportBooking testTransportBookingPlane = new TransportBookingBuilder().withMode("plane").build();
        TransportBooking testTransportBookingOthers = new TransportBookingBuilder().withMode("others").build();
        EditTransportBookingDescriptor descriptor =
                new EditTransportBookingDescriptorBuilder(testTransportBookingPlane)
                        .build();
        EditTransportBookingDescriptor descriptorOthers =
                new EditTransportBookingDescriptorBuilder(testTransportBookingOthers)
                        .build();
        EditTransportBookingCommand editTransportBookingCommand =
                new EditTransportBookingCommand(INDEX_FIRST, descriptor);
        EditTransportBookingCommand editTransportBookingCommandOthers =
                new EditTransportBookingCommand(INDEX_SECOND, descriptorOthers);

        // same object -> returns true
        assertTrue(editTransportBookingCommand.equals(editTransportBookingCommand));

        assertEquals(descriptor, descriptor);

        assertNotEquals(descriptor, descriptorOthers);

        assertEquals(descriptor, descriptor);

        // different types -> returns false
        assertNotEquals(1, editTransportBookingCommand);

        // null -> returns false
        assertNotEquals(null, editTransportBookingCommand);

        // different command -> returns false
        assertNotEquals(editTransportBookingCommand, editTransportBookingCommandOthers);
    }


    /**
     * A Model stub has no trip set.
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
        public void setTransportBooking(TransportBooking target, TransportBooking edited) {
            requireAllNonNull(target, edited);
            transportBookingsAdded.remove(target);
            transportBookingsAdded.add(edited);
        }

        @Override
        public void updateFilteredTransportBookingList(Predicate<TransportBooking> predicate) {
            requireNonNull(predicate);
        }
    }


}
