package seedu.address.model.util.sampledata;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.time.DateTime;
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
import seedu.address.model.util.attributes.Location;
import seedu.address.model.util.attributes.Title;
import seedu.address.model.util.attributes.tag.Tag;

/**
 * Contains utility methods for populating Easy Travel with sample data.
 */
public class SampleDataUtil {
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
                    getTagSet("relaxation"), Optional.empty())
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
        return new PackingListItem[]{
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

