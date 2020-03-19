//package seedu.address.model.listmanagers.activity;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.testutil.Assert.assertThrows;
//
//import java.util.HashSet;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.address.model.util.attributes.Location;
//
//class ActivityTest {
//1
//    private final Activity activity =
//            new Activity(new Title("Hot Spring"),
//                    new Priority("1"),
//                    new Duration("3"),
//                    new Location("Hokkaido"),
//                    new HashSet<>());
//
//    @Test
//    public void constructorInvalidTitleThrowsIllegalArgumentException() {
//        String invalidTitle = " ";
//        String invalidPriority = "0";
//        String invalidDuration = "0";
//
//        assertThrows(IllegalArgumentException.class, () -> new Activity(new Title(invalidTitle),
//                new Priority(invalidPriority),
//                new Duration(invalidDuration),
//                new Location(null),
//                new HashSet<>()));
//    }
//
//    @Test
//    public void getTitle() {
//
//        // Correct case
//        assertEquals(new Title("Hot Spring"), new Activity(
//                new Title("Hot Spring"),
//                new Priority("1"),
//                new Duration("3"),
//                new Location("Hokkaido"),
//                new HashSet<>()).getTitle());
//
//        //Different case
//        assertNotEquals(new Title("Hot Hot"),
//                        new Activity(new Title("Hot Spring"),
//                        new Priority("1"),
//                        new Duration("3"),
//                        new Location("Hokkaido"),
//                        new HashSet<>()).getTitle());
//
//    }
//
//    @Test
//    public void getDuration() {
//
//        // Correct case
//        assertEquals(new Duration("3"),
//                new Activity(new Title("Hot Spring"),
//                        new Priority("1"),
//                        new Duration("3"),
//                        new Location("Hokkaido"),
//                        new HashSet<>()).getDuration());
//
//        //Different case
//        assertNotEquals(new Duration("2"),
//                new Activity(new Title("Hot Spring"),
//                        new Priority("1"),
//                        new Duration("3"),
//                        new Location("Hokkaido"),
//                        new HashSet<>()).getDuration());
//
//    }
//
//    @Test
//    public void getPriority() {
//
//        // Correct case
//        assertEquals(new Priority("3"),
//                new Activity(new Title("Hot Spring"),
//                        new Priority("3"),
//                        new Duration("3"),
//                        new Location("Hokkaido"),
//                        new HashSet<>()).getPriority());
//
//        //Different case
//        assertNotEquals(new Priority("2"),
//                new Activity(new Title("Hot Spring"),
//                        new Priority("1"),
//                        new Duration("3"),
//                        new Location("Hokkaido"),
//                        new HashSet<>()).getPriority());
//
//    }
//
//    @Test
//    public void isSameActivity() {
//        // same object -> returns true
//        assertTrue(activity.isSameActivity(activity));
//
//        // null -> returns false
//        assertFalse(activity.isSameActivity(null));
//
//        // different name and quantity -> returns false
//        Activity editedActivity = new Activity(new Title("See Deers"),
//                new Priority("1"),
//                new Duration("1"),
//                new Location("Nara"),
//                new HashSet<>());
//        assertFalse(editedActivity.isSameActivity(activity));
//
//        // different name -> returns false
//        Activity secondEditedActivity = new Activity(new Title("Spring"),
//                new Priority("1"),
//                new Duration("3"),
//                new Location("Hokkaido"),
//                new HashSet<>());
//        assertFalse(editedActivity.isSameActivity(secondEditedActivity));
//    }
//
//    @Test
//    public void testEquals() {
//        // Same case
//        Activity editedActivity = new Activity(new Title("Hot Spring"),
//                new Priority("1"),
//                new Duration("3"),
//                new Location("Hokkaido"),
//                new HashSet<>());
//        assertEquals(activity, editedActivity);
//
//        //Different case
//        editedActivity = new Activity(new Title("See Deers"),
//                new Priority("1"),
//                new Duration("1"),
//                new Location("Nara"),
//                new HashSet<>());
//        assertNotEquals(editedActivity, activity);
//    }
//
//    @Test
//    public void testHashCode() {
//
//        //Same case
//        Activity editedActivity = new Activity(new Title("Hot Spring"),
//                new Priority("1"),
//                new Duration("3"),
//                new Location("Hokkaido"),
//                new HashSet<>());
//        assertEquals(editedActivity.hashCode(), activity.hashCode());
//
//        //Different case
//        editedActivity = new Activity(new Title("See Deers"),
//                new Priority("1"),
//                new Duration("1"),
//                new Location("Nara"),
//                new HashSet<>());
//        assertNotEquals(editedActivity.hashCode(), activity.hashCode());
//    }
//
//    @Test
//    public void testToString() {
//        Activity editedActivity =
//                new Activity(new Title("Hot Spring"),
//                        new Priority("1"),
//                        new Duration("3"),
//                        new Location("Hokkaido"),
//                        new HashSet<>());
//        assertEquals(activity.toString(), editedActivity.toString());
//
//        editedActivity =
//                new Activity(new Title("See Deers"),
//                        new Priority("1"),
//                        new Duration("1"),
//                        new Location("Nara"),
//                        new HashSet<>());
//        assertNotEquals(editedActivity.toString(), activity.toString());
//
//    }
//}
