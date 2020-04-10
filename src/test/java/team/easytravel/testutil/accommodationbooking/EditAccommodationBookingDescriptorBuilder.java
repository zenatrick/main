package team.easytravel.testutil.accommodationbooking;

import team.easytravel.logic.commands.accommodationbooking.EditAccommodationBookingCommand;
import team.easytravel.model.listmanagers.accommodationbooking.AccommodationBooking;
import team.easytravel.model.listmanagers.accommodationbooking.AccommodationName;
import team.easytravel.model.listmanagers.accommodationbooking.Day;
import team.easytravel.model.listmanagers.accommodationbooking.Remark;
import team.easytravel.model.util.attributes.Location;

/**
 * A utility class to help with building EditAccommodationBookingDescriptor objects.
 */
public class EditAccommodationBookingDescriptorBuilder {

    private EditAccommodationBookingCommand.EditAccommodationBookingDescriptor descriptor;

    public EditAccommodationBookingDescriptorBuilder() {
        descriptor = new EditAccommodationBookingCommand.EditAccommodationBookingDescriptor();
    }

    public EditAccommodationBookingDescriptorBuilder(EditAccommodationBookingCommand
                                                             .EditAccommodationBookingDescriptor descriptor) {
        this.descriptor = new EditAccommodationBookingCommand.EditAccommodationBookingDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditAccommodationBookingDescriptor} with fields containing {@code activity}'s details
     */
    public EditAccommodationBookingDescriptorBuilder(AccommodationBooking accommodationBooking) {
        descriptor = new EditAccommodationBookingCommand.EditAccommodationBookingDescriptor();
        descriptor.setAccommodationName(accommodationBooking.getAccommodationName());
        descriptor.setLocation(accommodationBooking.getLocation());
        descriptor.setStartDay(accommodationBooking.getStartDay());
        descriptor.setEndDay(accommodationBooking.getEndDay());
        descriptor.setRemark(accommodationBooking.getRemark());
    }

    /**
     * Sets the {@code accommodationName} of the {@code EditAccommodationBookingDescriptor} that we are building.
     */
    public EditAccommodationBookingDescriptorBuilder withAccommodationName(String accommodationName) {
        descriptor.setAccommodationName(new AccommodationName(accommodationName));
        return this;
    }

    /**
     * Sets the {@code location} of the {@code EditAccommodationBookingDescriptor} that we are building.
     */
    public EditAccommodationBookingDescriptorBuilder withLocation(String location) {
        descriptor.setLocation(new Location(location));
        return this;
    }

    /**
     * Sets the {@code startDay} of the {@code EditAccommodationBookingDescriptor} that we are building.
     */
    public EditAccommodationBookingDescriptorBuilder withStartDay(Integer startDay) {
        descriptor.setStartDay(new Day(startDay));
        return this;
    }

    /**
     * Sets the {@code endDay} of the {@code EditAccommodationBookingDescriptor} that we are building.
     */
    public EditAccommodationBookingDescriptorBuilder withEndDay(Integer endDay) {
        descriptor.setEndDay(new Day(endDay));
        return this;
    }

    /**
     * Sets the {@code remark} of the {@code EditAccommodationBookingDescriptor} that we are building.
     */
    public EditAccommodationBookingDescriptorBuilder withRemark(String remark) {
        descriptor.setRemark(new Remark(remark));
        return this;
    }


    public EditAccommodationBookingCommand.EditAccommodationBookingDescriptor build() {
        return descriptor;
    }

}
