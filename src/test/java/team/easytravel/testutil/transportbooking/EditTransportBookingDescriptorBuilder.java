package team.easytravel.testutil.transportbooking;

import team.easytravel.logic.commands.transportbooking.EditTransportBookingCommand.EditTransportBookingDescriptor;
import team.easytravel.model.listmanagers.transportbooking.TransportBooking;

/**
 * A utility class to help with building EditTransportBookingDescriptor objects.
 */
public class EditTransportBookingDescriptorBuilder {

    private EditTransportBookingDescriptor descriptor;

    /**
     * Returns an {@code EditTransportBookingDescriptor} with fields containing {@code transport booking}'s details
     */
    public EditTransportBookingDescriptorBuilder(TransportBooking transportBooking) {
        descriptor = new EditTransportBookingDescriptor();
        descriptor.setMode(transportBooking.getMode());
        descriptor.setStartLocation(transportBooking.getStartLocation());
        descriptor.setEndLocation(transportBooking.getEndLocation());
        descriptor.setStartDateTime(transportBooking.getStartDateTime());
        descriptor.setEndDateTime(transportBooking.getEndDateTime());
    }


    public EditTransportBookingDescriptor build() {
        return descriptor;
    }

}
