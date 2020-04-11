package team.easytravel.model.util.sampledata;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import team.easytravel.commons.core.time.Date;
import team.easytravel.commons.core.time.DateTime;
import team.easytravel.model.listmanagers.AccommodationBookingManager;
import team.easytravel.model.listmanagers.ActivityManager;
import team.easytravel.model.listmanagers.FixedExpenseManager;
import team.easytravel.model.listmanagers.PackingListManager;
import team.easytravel.model.listmanagers.ReadOnlyAccommodationBookingManager;
import team.easytravel.model.listmanagers.ReadOnlyActivityManager;
import team.easytravel.model.listmanagers.ReadOnlyFixedExpenseManager;
import team.easytravel.model.listmanagers.ReadOnlyPackingListManager;
import team.easytravel.model.listmanagers.ReadOnlyTransportBookingManager;
import team.easytravel.model.listmanagers.TransportBookingManager;
import team.easytravel.model.listmanagers.accommodationbooking.AccommodationBooking;
import team.easytravel.model.listmanagers.accommodationbooking.AccommodationName;
import team.easytravel.model.listmanagers.accommodationbooking.Day;
import team.easytravel.model.listmanagers.accommodationbooking.Remark;
import team.easytravel.model.listmanagers.activity.Activity;
import team.easytravel.model.listmanagers.activity.Duration;
import team.easytravel.model.listmanagers.activity.Tag;
import team.easytravel.model.listmanagers.fixedexpense.Amount;
import team.easytravel.model.listmanagers.fixedexpense.Description;
import team.easytravel.model.listmanagers.fixedexpense.FixedExpense;
import team.easytravel.model.listmanagers.fixedexpense.FixedExpenseCategory;
import team.easytravel.model.listmanagers.packinglistitem.ItemCategory;
import team.easytravel.model.listmanagers.packinglistitem.ItemName;
import team.easytravel.model.listmanagers.packinglistitem.PackingListItem;
import team.easytravel.model.listmanagers.packinglistitem.Quantity;
import team.easytravel.model.listmanagers.transportbooking.Mode;
import team.easytravel.model.listmanagers.transportbooking.TransportBooking;
import team.easytravel.model.trip.Budget;
import team.easytravel.model.trip.ExchangeRate;
import team.easytravel.model.trip.Trip;
import team.easytravel.model.trip.TripManager;
import team.easytravel.model.util.attributes.Location;
import team.easytravel.model.util.attributes.Title;

/**
 * Contains utility methods for populating Easy Travel with sample data.
 */
public class SampleDataUtil {
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
            new TransportBooking(new Mode("plane"), new Location("Singapore"), new Location("Osaka Japan"),
                    DateTime.fromString("28-09-2020 10:15"), DateTime.fromString("28-09-2020 17:00")),
            new TransportBooking(new Mode("train"), new Location("Osaka"), new Location("Kyoto"),
                    DateTime.fromString("29-09-2020 12:30"), DateTime.fromString("29-09-2020 12:50"))
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
        return new FixedExpense[]{
            new FixedExpense(new Amount("788.54"), new Description("Japan Rail Pass"),
                    new FixedExpenseCategory("transport")),
            new FixedExpense(new Amount("600"), new Description("2 way Fight Tickets"),
                    new FixedExpenseCategory("transport")),
            new FixedExpense(new Amount("326.56"), new Description("Hyatt Regency Hotel Booking"),
                    new FixedExpenseCategory("accommodations")),
            new FixedExpense(new Amount("580"), new Description("Shinjuku Granbell Hotel Booking"),
                    new FixedExpenseCategory("accommodations")),
            new FixedExpense(new Amount("70.44"), new Description("Disneyland Starlight Passport"),
                    new FixedExpenseCategory("activities"))
        };
    }

    public static ReadOnlyFixedExpenseManager getSampleFixedExpenseManager() {
        FixedExpenseManager sampleFixedExpenseManager = new FixedExpenseManager();
        for (FixedExpense sampleFixedExpense : getSampleFixedExpenses()) {
            sampleFixedExpenseManager.addFixedExpense(sampleFixedExpense);
        }
        return sampleFixedExpenseManager;
    }

    public static Activity[] getSampleActivity() {
        return new Activity[]{
            new Activity(new Title("Hot Spring"), new Duration(3), new Location("Hokkaido"),
                    getTagSet("relaxation"), Optional.empty()),
            new Activity(new Title("Skiing"), new Duration(4), new Location("Hokkaido"),
                    getTagSet("sports", "winter", "fun"), Optional.empty()),
            new Activity(new Title("Osaka Castle View"), new Duration(1), new Location("Osakajo Osaka"),
                    getTagSet("sightseeing", "historic"), Optional.empty()),
            new Activity(new Title("Shopping"), new Duration(2), new Location("Tokyo"),
                    getTagSet("shop"), Optional.of(DateTime.fromString("02-10-2020 18:00"))),
            new Activity(new Title("Shopping"), new Duration(3), new Location("Osaka"),
                    getTagSet("shop"), Optional.of(DateTime.fromString("28-09-2020 17:00"))),
            new Activity(new Title("Visit Pokemon Center"), new Duration(1), new Location("Tokyo"),
                    getTagSet("shop"), Optional.of(DateTime.fromString("02-10-2020 19:00")))
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
            new AccommodationBooking(new AccommodationName("Hyatt Regency"), new Location("Kyoto"), new Day(2),
                    new Day(3), new Remark("Checkin at 3pm")),
            new AccommodationBooking(new AccommodationName("Shinjuku Granbell Hotel"), new Location("Tokyo"),
                    new Day(5), new Day(10), new Remark(" "))
        };
    }

    public static ReadOnlyAccommodationBookingManager getSampleAccommodationBookingManager() {
        AccommodationBookingManager sampleAccommodationBookingManager = new AccommodationBookingManager();
        for (AccommodationBooking sampleAccommodationBooking : getSampleAccommodationBookings()) {
            sampleAccommodationBookingManager.addAccommodationBooking(sampleAccommodationBooking);
        }
        return sampleAccommodationBookingManager;
    }

    public static PackingListItem[] getSamplePackingListItems() {
        return new PackingListItem[]{
            new PackingListItem(new ItemName("Tshirt"), new Quantity(7), new ItemCategory("basics"), false),
            new PackingListItem(new ItemName("Jeans"), new Quantity(5), new ItemCategory("basics"), false),
            new PackingListItem(new ItemName("Underwear"), new Quantity(7), new ItemCategory("essentials"), false),
            new PackingListItem(new ItemName("Shampoo"), new Quantity(1), new ItemCategory("toiletries"), false),
            new PackingListItem(new ItemName("Conditioner"), new Quantity(1), new ItemCategory("toiletries"), false)
        };
    }

    public static ReadOnlyPackingListManager getSamplePackingListManager() {
        PackingListManager samplePackingListManager = new PackingListManager();
        for (PackingListItem samplePackingListItem : getSamplePackingListItems()) {
            samplePackingListManager.addPackingListItem(samplePackingListItem);
        }
        return samplePackingListManager;
    }

    public static Trip getSampleTrip() {
        return new Trip(new Title("Graduation Trip"), Date.fromString("28-09-2020"),
                Date.fromString("26-10-2020"), new Budget(5000), new ExchangeRate(76d));
    }

    public static TripManager getSampleTripManager() {
        TripManager tripManager = new TripManager();
        tripManager.setTrip(getSampleTrip());
        return tripManager;
    }
}

