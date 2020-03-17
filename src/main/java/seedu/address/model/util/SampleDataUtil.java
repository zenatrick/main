package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.activity.Activity;
import seedu.address.model.activity.Duration;
import seedu.address.model.activity.Priority;
import seedu.address.model.activity.Title;
import seedu.address.model.accommodationbooking.AccommodationBooking;
import seedu.address.model.accommodationbooking.AccommodationName;
import seedu.address.model.accommodationbooking.Day;
import seedu.address.model.accommodationbooking.Remark;
import seedu.address.model.fixedexpense.Amount;
import seedu.address.model.fixedexpense.Category;
import seedu.address.model.fixedexpense.Description;
import seedu.address.model.fixedexpense.FixedExpense;
import seedu.address.model.listmanager.AccommodationBookingManager;
import seedu.address.model.listmanager.ActivityManager;
import seedu.address.model.listmanager.FixedExpenseManager;
import seedu.address.model.listmanager.ReadOnlyAccommodationBookingManager;
import seedu.address.model.listmanager.ReadOnlyActivityManager;
import seedu.address.model.listmanager.ReadOnlyFixedExpenseManager;
import seedu.address.model.listmanager.ReadOnlyTransportBookingManager;
import seedu.address.model.listmanager.TransportBookingManager;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.transportbooking.Location;
import seedu.address.model.transportbooking.Mode;
import seedu.address.model.transportbooking.Time;
import seedu.address.model.transportbooking.TransportBooking;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
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

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static TransportBooking[] getSampleTransportBookings() {
        return new TransportBooking[]{
            new TransportBooking(new Mode("plane"), new Location("Singapore"), new Location("Japan"), new Time(
                    "2020-01-10T10:15:00"), new Time("2020-01-20T22:00:00"))
        };
    }

    public static ReadOnlyTransportBookingManager getSampleTransportBookingManager() {
        TransportBookingManager sampleTransportBookingManager = new TransportBookingManager();
        for (TransportBooking sampleTransportBooking : getSampleTransportBookings()) {
            sampleTransportBookingManager.addTransportBooking(sampleTransportBooking);
        }
        return sampleTransportBookingManager;
    }

    public static FixedExpense[] getSampleFixedExpenses() {
        return new FixedExpense[] {
            new FixedExpense(new Amount("1000"), new Description("TestDescription"),
                      new Category("TestCategory"))
        };
    }

    public static ReadOnlyFixedExpenseManager getSampleFixedExpenseManager() {
        FixedExpenseManager sampleFixedExpenseManager = new FixedExpenseManager();
        for (FixedExpense sampleFixedExpense: getSampleFixedExpenses()) {
            sampleFixedExpenseManager.addFixedExpense(sampleFixedExpense);
        }
        return sampleFixedExpenseManager;
    }

    public static Activity[] getSampleActivity() {
        return new Activity[] { new Activity(new Title("Hot Spring"),
                        new Priority("1"),
                        new Duration("3"),
                        new Location("Hokkaido"),
                        new HashSet<>())
        };
    }

    public static ReadOnlyActivityManager getSampleActivityManager() {
        ActivityManager sampleActivityManager = new ActivityManager();
        for (Activity sampleActivity : getSampleActivity()) {
            sampleActivityManager.addActivity(sampleActivity);
        }
        return sampleActivityManager;
    }

    public static AccommodationBooking[] getSampleAccommodationBookings() {
        return new AccommodationBooking[]{
                new AccommodationBooking(new AccommodationName("Hyatt Regency"), new Location("Kyoto"),
                        new Day("1"), new Day("4"), new Remark("Check-in at 3pm."))
        };
    }

    public static ReadOnlyAccommodationBookingManager getSampleAccommodationBookingManager() {
        AccommodationBookingManager sampleAccommodationBookingManager = new AccommodationBookingManager();
        for (AccommodationBooking sampleAccommodationBooking : getSampleAccommodationBookings()) {
            sampleAccommodationBookingManager.addAccommodationBooking(sampleAccommodationBooking);
        }
        return sampleAccommodationBookingManager;
    }

}

