package seedu.address.model.util.sampledata;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.time.DateTime;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.listmanagers.AccommodationBookingManager;
import seedu.address.model.listmanagers.ActivityManager;
import seedu.address.model.listmanagers.FixedExpenseManager;
import seedu.address.model.listmanagers.PackingListManager;
import seedu.address.model.listmanagers.ReadOnlyAccommodationBookingManager;
import seedu.address.model.listmanagers.ReadOnlyActivityManager;
import seedu.address.model.listmanagers.ReadOnlyFixedExpenseManager;
import seedu.address.model.listmanagers.ReadOnlyPackingListManager;
import seedu.address.model.listmanagers.ReadOnlyTransportBookingManager;
import seedu.address.model.listmanagers.TransportBookingManager;
import seedu.address.model.listmanagers.accommodationbooking.AccommodationBooking;
import seedu.address.model.listmanagers.accommodationbooking.AccommodationName;
import seedu.address.model.listmanagers.accommodationbooking.Day;
import seedu.address.model.listmanagers.accommodationbooking.Remark;
import seedu.address.model.listmanagers.activity.Activity;
import seedu.address.model.listmanagers.activity.Duration;
import seedu.address.model.listmanagers.fixedexpense.Amount;
import seedu.address.model.listmanagers.fixedexpense.Description;
import seedu.address.model.listmanagers.fixedexpense.FixedExpense;
import seedu.address.model.listmanagers.fixedexpense.FixedExpenseCategory;
import seedu.address.model.listmanagers.packinglistitem.ItemCategory;
import seedu.address.model.listmanagers.packinglistitem.ItemName;
import seedu.address.model.listmanagers.packinglistitem.PackingListItem;
import seedu.address.model.listmanagers.packinglistitem.Quantity;
import seedu.address.model.listmanagers.transportbooking.Mode;
import seedu.address.model.listmanagers.transportbooking.TransportBooking;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.util.attributes.Location;
import seedu.address.model.util.attributes.Title;
import seedu.address.model.util.attributes.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    /**
     * Get sample persons person [ ].
     *
     * @return the person [ ]
     */
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getTagSet("colleagues"))
        };
    }

    /**
     * Gets sample address book.
     *
     * @return the sample address book
     */
    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     *
     * @param strings the strings
     * @return the tag set
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Get sample transport bookings transport booking [ ].
     *
     * @return the transport booking [ ]
     */
    public static TransportBooking[] getSampleTransportBookings() {
        return new TransportBooking[]{
            new TransportBooking(new Mode("plane"), new Location("Singapore"), new Location("Japan"),
                    DateTime.fromString("10-01-2020 10:15"), DateTime.fromString("20-01-2020 22:00"))
        };
    }

    /**
     * Gets sample transport booking manager.
     *
     * @return the sample transport booking manager
     */
    public static ReadOnlyTransportBookingManager getSampleTransportBookingManager() {
        TransportBookingManager sampleTransportBookingManager = new TransportBookingManager();
        for (TransportBooking sampleTransportBooking : getSampleTransportBookings()) {
            sampleTransportBookingManager.addTransportBooking(sampleTransportBooking);
        }
        return sampleTransportBookingManager;
    }

    /**
     * Get sample fixed expenses fixed expense [ ].
     *
     * @return the fixed expense [ ]
     */
    public static FixedExpense[] getSampleFixedExpenses() {
        return new FixedExpense[]{
            new FixedExpense(new Amount("1000"), new Description("TestDescription"),
                    new FixedExpenseCategory("TestCategory")),
            new FixedExpense(new Amount("2000"), new Description("TestDescription"),
                    new FixedExpenseCategory("TestCategory")),
            new FixedExpense(new Amount("3000"), new Description("TestDescription"),
                    new FixedExpenseCategory("TestCategory")),
            new FixedExpense(new Amount("4000"), new Description("TestDescription"),
                    new FixedExpenseCategory("TestCategory"))
        };
    }

    /**
     * Gets sample fixed expense manager.
     *
     * @return the sample fixed expense manager
     */
    public static ReadOnlyFixedExpenseManager getSampleFixedExpenseManager() {
        FixedExpenseManager sampleFixedExpenseManager = new FixedExpenseManager();
        for (FixedExpense sampleFixedExpense : getSampleFixedExpenses()) {
            sampleFixedExpenseManager.addFixedExpense(sampleFixedExpense);
        }
        return sampleFixedExpenseManager;
    }

    /**
     * Get sample activity activity [ ].
     *
     * @return the activity [ ]
     */
    public static Activity[] getSampleActivity() {
        return new Activity[]{
            new Activity(new Title("Hot Spring"), new Duration(3), new Location("Hokkaido"),
                    new HashSet<>(), Optional.empty())
        };
    }

    /**
     * Gets sample activity manager.
     *
     * @return the sample activity manager
     */
    public static ReadOnlyActivityManager getSampleActivityManager() {
        ActivityManager sampleActivityManager = new ActivityManager();
        for (Activity sampleActivity : getSampleActivity()) {
            sampleActivityManager.addActivity(sampleActivity);
        }
        return sampleActivityManager;
    }

    /**
     * Get sample accommodation bookings accommodation booking [ ].
     *
     * @return the accommodation booking [ ]
     */
    public static AccommodationBooking[] getSampleAccommodationBookings() {
        return new AccommodationBooking[]{
            new AccommodationBooking(new AccommodationName("Hyatt Regency"), new Location("Kyoto"), new Day(1),
                    new Day(3), new Remark("Checkin at 3pm"))
        };
    }

    /**
     * Gets sample accommodation booking manager.
     *
     * @return the sample accommodation booking manager
     */
    public static ReadOnlyAccommodationBookingManager getSampleAccommodationBookingManager() {
        AccommodationBookingManager sampleAccommodationBookingManager = new AccommodationBookingManager();
        for (AccommodationBooking sampleAccommodationBooking : getSampleAccommodationBookings()) {
            sampleAccommodationBookingManager.addAccommodationBooking(sampleAccommodationBooking);
        }
        return sampleAccommodationBookingManager;
    }

    /**
     * Get sample packing list items [ ].
     *
     * @return the packing list items [ ]
     */
    public static PackingListItem[] getSamplePackingListItems() {
        return new PackingListItem[] {
            new PackingListItem(new ItemName("Tshirt"), new Quantity(7), new ItemCategory("basics"), false),
            new PackingListItem(new ItemName("Jeans"), new Quantity(5), new ItemCategory("basics"), false),
            new PackingListItem(new ItemName("Underwear"), new Quantity(7), new ItemCategory("essentials"), false),
            new PackingListItem(new ItemName("Shampoo"), new Quantity(1), new ItemCategory("toiletries"), false),
            new PackingListItem(new ItemName("Conditioner"), new Quantity(1), new ItemCategory("toiletries"), false)
        };
    }

    /**
     * Gets sample packing list manager.
     *
     * @return the sample packing list manager
     */
    public static ReadOnlyPackingListManager getSamplePackingListManager() {
        PackingListManager samplePackingListManager = new PackingListManager();
        for (PackingListItem samplePackingListItem : getSamplePackingListItems()) {
            samplePackingListManager.addPackingListItem(samplePackingListItem);
        }
        return samplePackingListManager;
    }
}

