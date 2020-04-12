package team.easytravel.testutil.transportbooking;

import team.easytravel.commons.core.time.DateTime;
import team.easytravel.logic.commands.transportbooking.EditTransportBookingCommand.EditTransportBookingDescriptor;
import team.easytravel.model.listmanagers.transportbooking.Mode;
import team.easytravel.model.listmanagers.transportbooking.TransportBooking;
import team.easytravel.model.util.attributes.Location;

/**
 * A utility class to help with building EditTransportBookingDescriptor objects.
 */
public class EditTransportBookingDescriptorBuilder {

    private EditTransportBookingDescriptor descriptor;

    public EditTransportBookingDescriptorBuilder() {
        descriptor = new EditTransportBookingDescriptor();
    }

    public EditTransportBookingDescriptorBuilder(EditTransportBookingDescriptor descriptor) {
        this.descriptor = new EditTransportBookingDescriptor(descriptor);
    }

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

    /**
     * Sets the {@code mode} of the {@code EditTransportBookingDescriptor} that we are building.
     */
    public EditTransportBookingDescriptorBuilder withMode(String mode) {
        descriptor.setMode(new Mode(mode));
        return this;
    }

    /**
     * Sets the {@code startLocation} of the {@code EditTransportBookingDescriptor} that we are building.
     */
    public EditTransportBookingDescriptorBuilder withStartLocation(String startLocation) {
        descriptor.setStartLocation(new Location(startLocation));
        return this;
    }

    /**
     * Sets the {@code endLocation} of the {@code EditTransportBookingDescriptor} that we are building.
     */
    public EditTransportBookingDescriptorBuilder withEndLocation(String endLocation) {
        descriptor.setEndLocation(new Location(endLocation));
        return this;
    }

    /**
     * Sets the {@code startDateTime} of the {@code EditTransportBookingDescriptor} that we are building.
     */
    public EditTransportBookingDescriptorBuilder withStartDateTime(String startDateTime) {
        descriptor.setStartDateTime(DateTime.fromString(startDateTime));
        return this;
    }

    /**
     * Sets the {@code endDateTime} of the {@code EditTransportBookingDescriptor} that we are building.
     */
    public EditTransportBookingDescriptorBuilder withEndDateTime(String endDateTime) {
        descriptor.setEndDateTime(DateTime.fromString(endDateTime));
        return this;
    }



    public EditTransportBookingDescriptor build() {
        return descriptor;
    }

}
