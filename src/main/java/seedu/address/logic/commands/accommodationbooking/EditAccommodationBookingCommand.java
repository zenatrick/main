package seedu.address.logic.commands.accommodationbooking;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACCOMMODATION_END_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACCOMMODATION_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACCOMMODATION_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACCOMMODATION_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACCOMMODATION_START_DAY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ACCOMMODATION_BOOKINGS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.listmanagers.accommodationbooking.AccommodationBooking;
import seedu.address.model.listmanagers.accommodationbooking.AccommodationName;
import seedu.address.model.listmanagers.accommodationbooking.Day;
import seedu.address.model.listmanagers.accommodationbooking.Remark;
import seedu.address.model.util.attributes.Location;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditAccommodationBookingCommand extends Command {

    public static final String COMMAND_WORD = "editacc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the accommodation booking "
            + "identified by the index number used in the displayed accommodation booking list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_ACCOMMODATION_NAME + "NAME] "
            + "[" + PREFIX_ACCOMMODATION_LOCATION + "LOCATION] "
            + "[" + PREFIX_ACCOMMODATION_START_DAY + "START_DAY] "
            + "[" + PREFIX_ACCOMMODATION_END_DAY + "END_DAY] "
            + "[" + PREFIX_ACCOMMODATION_REMARK + "REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ACCOMMODATION_NAME + "Casa del Rio "
            + PREFIX_ACCOMMODATION_LOCATION + "Malacca";

    public static final String MESSAGE_EDIT_ACCOMMODATION_BOOKING_SUCCESS = "Edited Accommodation Booking: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ACCOMMODATION_BOOKING = "This Accommodation Booking already exists "
            + "in the accommodation booking list.";

    private final Index index;
    private final EditAccommodationBookingDescriptor editAccommodationBookingDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editAccommodationBookingDescriptor details to edit the person with
     */
    public EditAccommodationBookingCommand(Index index,
                                           EditAccommodationBookingDescriptor editAccommodationBookingDescriptor) {
        requireNonNull(index);
        requireNonNull(editAccommodationBookingDescriptor);

        this.index = index;
        this.editAccommodationBookingDescriptor = new EditAccommodationBookingDescriptor(
                editAccommodationBookingDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<AccommodationBooking> lastShownList = model.getFilteredAccommodationBookingList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ACCOMMODATION_BOOKING_DISPLAYED_INDEX);
        }

        AccommodationBooking accommodationBookingToEdit = lastShownList.get(index.getZeroBased());
        AccommodationBooking editedAccommodationBooking= createEditedAccommodationBooking(accommodationBookingToEdit,
                editAccommodationBookingDescriptor);

        if (!accommodationBookingToEdit.isSame(editedAccommodationBooking) &&
                model.hasAccommodationBooking(editedAccommodationBooking)) {
            throw new CommandException(MESSAGE_DUPLICATE_ACCOMMODATION_BOOKING);
        }

        model.setAccommodationBooking(accommodationBookingToEdit, editedAccommodationBooking);
        model.updateFilteredAccommodationBookingList(PREDICATE_SHOW_ALL_ACCOMMODATION_BOOKINGS);
        return new CommandResult(String.format(MESSAGE_EDIT_ACCOMMODATION_BOOKING_SUCCESS, editedAccommodationBooking));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static AccommodationBooking createEditedAccommodationBooking(
            AccommodationBooking accommodationBookingToEdit,
            EditAccommodationBookingDescriptor editAccommodationBookingDescriptor) {
        assert accommodationBookingToEdit != null;

        AccommodationName updatedName = editAccommodationBookingDescriptor.getAccommodationName()
                .orElse(accommodationBookingToEdit.getAccommodationName());
        Location updatedLocation = editAccommodationBookingDescriptor.getLocation().orElse(accommodationBookingToEdit
                .getLocation());
        Day updatedStartDay= editAccommodationBookingDescriptor.getStartDay().orElse(accommodationBookingToEdit
                .getStartDay());
        Day updatedEndDay = editAccommodationBookingDescriptor.getEndDay().orElse(accommodationBookingToEdit
                .getEndDay());
        Remark updatedRemark = editAccommodationBookingDescriptor.getRemark().orElse(accommodationBookingToEdit
                .getRemark());

        return new AccommodationBooking(updatedName, updatedLocation, updatedStartDay, updatedEndDay, updatedRemark);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditAccommodationBookingCommand e = (EditAccommodationBookingCommand) other;
        return index.equals(e.index)
                && editAccommodationBookingDescriptor.equals(e.editAccommodationBookingDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditAccommodationBookingDescriptor {
        private AccommodationName accommodationName;
        private Location accommodationLocation;
        private Day accommodationStartDay;
        private Day accommodationEndDay;
        private Remark accommodationRemark;

        public EditAccommodationBookingDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditAccommodationBookingDescriptor(EditAccommodationBookingDescriptor toCopy) {
            setAccommodationName(toCopy.accommodationName);
            setLocation(toCopy.accommodationLocation);
            setStartDay(toCopy.accommodationStartDay);
            setEndDay(toCopy.accommodationEndDay);
            setRemark(toCopy.accommodationRemark);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(accommodationName, accommodationLocation, accommodationStartDay,
                    accommodationEndDay, accommodationRemark);
        }

        public void setAccommodationName(AccommodationName accommodationName) {
            this.accommodationName = accommodationName;
        }

        public Optional<AccommodationName> getAccommodationName() {
            return Optional.ofNullable(accommodationName);
        }

        public void setLocation(Location accommodationLocation) {
            this.accommodationLocation = accommodationLocation;
        }

        public Optional<Location> getLocation() {
            return Optional.ofNullable(accommodationLocation);
        }

        public void setStartDay(Day accommodationStartDay) {
            this.accommodationStartDay = accommodationStartDay;
        }

        public Optional<Day> getStartDay() {
            return Optional.ofNullable(accommodationStartDay);
        }

        public void setEndDay(Day accommodationEndDay) {
            this.accommodationEndDay = accommodationEndDay;
        }

        public Optional<Day> getEndDay() {
            return Optional.ofNullable(accommodationEndDay);
        }

        public void setRemark(Remark accommodationRemark) {
            this.accommodationRemark = accommodationRemark;
        }

        public Optional<Remark> getRemark() {
            return Optional.ofNullable(accommodationRemark);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditAccommodationBookingDescriptor)) {
                return false;
            }

            // state check
            EditAccommodationBookingDescriptor e = (EditAccommodationBookingDescriptor) other;

            return getAccommodationName().equals(e.getAccommodationName())
                    && getLocation().equals(e.getLocation())
                    && getStartDay().equals(e.getStartDay())
                    && getEndDay().equals(e.getEndDay())
                    && getRemark().equals(e.getRemark());
        }
    }

}
