//package seedu.address.testutil;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.List;
//
//import seedu.address.model.listmanagers.ActivityManager;
//import seedu.address.model.listmanagers.activity.Activity;
//import seedu.address.model.listmanagers.activity.Duration;
//import seedu.address.model.listmanagers.activity.Priority;
//import seedu.address.model.listmanagers.activity.Title;
//import seedu.address.model.util.attributes.Location;
//
///**
// * A utility class containing a list of {@code Activity} objects to be used in tests.
// */
//public class TypicalActivity {
//
//
//    public static final Activity ACTIVITY_DISNEYLAND = new Activity(
//            new Title("DisneyLand"), new Priority(1), new Duration(3), new Location("Paris"),
//            new HashSet<>());
//
//    public static final Activity ACTIVITY_UNIVERSALSTUDIOS = new Activity(
//            new Title("Universal Studios"), new Priority(2), new Duration(10), new Location("Florida"),
//            new HashSet<>());
//
//    public static final Activity ACTIVITY_PEAK = new Activity(
//            new Title("Peak"), new Priority(3), new Duration(1), new Location("Hong Kong"),
//            new HashSet<>()
//    );
//
//    private TypicalActivity() {
//    }
//
//    /**
//     * Returns an {@code TypicalActivity} with all the typical Activity.
//     */
//
//    public static ActivityManager getTypicalActivityManager() {
//        ActivityManager am = new ActivityManager();
//        for (Activity activity : getTypicalActivity()) {
//            am.addActivity(activity);
//        }
//        return am;
//    }
//
//    public static List<Activity> getTypicalActivity() {
//        return new ArrayList<>(Arrays.asList(ACTIVITY_DISNEYLAND, ACTIVITY_PEAK,
//                ACTIVITY_UNIVERSALSTUDIOS));
//    }
//
//
//}
