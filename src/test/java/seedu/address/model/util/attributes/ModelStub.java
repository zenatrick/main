package seedu.address.model.util.attributes;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.listmanagers.ReadOnlyAccommodationBookingManager;
import seedu.address.model.listmanagers.ReadOnlyActivityManager;
import seedu.address.model.listmanagers.ReadOnlyFixedExpenseManager;
import seedu.address.model.listmanagers.ReadOnlyPackingListManager;
import seedu.address.model.listmanagers.ReadOnlyTransportBookingManager;
import seedu.address.model.listmanagers.ReadOnlyUserPrefs;
import seedu.address.model.listmanagers.accommodationbooking.AccommodationBooking;
import seedu.address.model.listmanagers.activity.Activity;
import seedu.address.model.listmanagers.fixedexpense.FixedExpense;
import seedu.address.model.listmanagers.packinglistitem.PackingListItem;
import seedu.address.model.listmanagers.transportbooking.TransportBooking;
import seedu.address.model.person.Person;

/**
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getAddressBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return null;
    }

    @Override
    public boolean hasPerson(Person person) {
        return false;
    }

    @Override
    public void deletePerson(Person target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return null;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyTransportBookingManager getTransportBookingManager() {
        return null;
    }

    @Override
    public void setTransportBookingManager(ReadOnlyTransportBookingManager transportBookingManager) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasTransportBooking(TransportBooking target) {
        return false;
    }

    @Override
    public void deleteTransportBooking(TransportBooking toDelete) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addTransportBooking(TransportBooking toAdd) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setTransportBooking(TransportBooking target, TransportBooking edited) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<TransportBooking> getFilteredTransportBookingList() {
        return null;
    }

    @Override
    public void updateFilteredTransportBookingList(Predicate<TransportBooking> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyFixedExpenseManager getFixedExpenseManager() {
        return null;
    }

    @Override
    public void setFixedExpenseManager(ReadOnlyFixedExpenseManager fixedExpenseManager) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasFixedExpense(FixedExpense target) {
        return false;
    }

    @Override
    public void deleteFixedExpense(FixedExpense toDelete) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addFixedExpense(FixedExpense toAdd) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setFixedExpense(FixedExpense target, FixedExpense edited) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void sortFixedExpenseList(Comparator<FixedExpense> cmp) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<FixedExpense> getFilteredFixedExpenseList() {
        return null;
    }

    @Override
    public void updateFilteredFixedExpenseList(Predicate<FixedExpense> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyPackingListManager getPackingListManager() {
        return null;
    }

    @Override
    public void setPackingListManager(ReadOnlyPackingListManager packingListManager) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasPackingListItem(PackingListItem target) {
        return false;
    }

    @Override
    public void deletePackingListItem(PackingListItem toDelete) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addPackingListItem(PackingListItem toAdd) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPackingListItem(PackingListItem target, PackingListItem edited) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<PackingListItem> getFilteredPackingList() {
        return null;
    }

    @Override
    public void updateFilteredPackingList(Predicate<PackingListItem> predicate) {
        throw new AssertionError("This method should not be called.");
    }


    @Override
    public void addActivity(Activity activity) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setActivityManager(ReadOnlyActivityManager newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyActivityManager getActivityManager() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasActivity(Activity activity) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteActivity(Activity target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setActivity(Activity target, Activity editActivity) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Activity> getFilteredActivityList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredActivityList(Predicate<Activity> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyAccommodationBookingManager getAccommodationBookingManager() {
        return null;
    }

    @Override
    public void setAccommodationBookingManager(ReadOnlyAccommodationBookingManager accommodationBookingManager) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasAccommodationBooking(AccommodationBooking target) {
        return false;
    }

    @Override
    public void deleteAccommodationBooking(AccommodationBooking toDelete) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addAccommodationBooking(AccommodationBooking toAdd) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAccommodationBooking(AccommodationBooking target, AccommodationBooking edited) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<AccommodationBooking> getFilteredAccommodationBookingList() {
        return null;
    }

    @Override
    public void updateFilteredAccommodationBookingList(Predicate<AccommodationBooking> predicate) {
        throw new AssertionError("This method should not be called.");
    }
}
