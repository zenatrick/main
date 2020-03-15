package seedu.address.model.activity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.activity.exceptions.ActivityNotFoundException;
import seedu.address.model.activity.exceptions.DuplicateActivityException;
import seedu.address.model.transportbooking.Location;

class UniqueActivityListTest {

    //They have a personBuilder, but since we dont have, improvise by using this for now (Thanks cat
    public static final Activity FIXED_ACTIVITY_1 =
            new Activity(new Title("Hot Spring"), new Priority("1"),
                    new Duration("3"),
                    new Location("Hokkaido"), new HashSet<>());

    public static final Activity FIXED_ACTIVITY_2 =
            new Activity(new Title("See Deers"),
                    new Priority("1"),new Duration("1"),
                    new Location("Nara"), new HashSet<>());

    private static final Activity FIXED_ACTIVITY_3 =
            new Activity(new Title("Eat Mochi"), new Priority("2"),
                    new Duration("1"),
                    new Location("Dazaifu"), new HashSet<>());

    private final UniqueActivityList uniqueActivityList = new UniqueActivityList();

    @Test
    public void containsNullActivityListThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueActivityList.contains(null));
    }

    @Test
    public void containsActivityListNotInListReturnsFalse() {
        assertFalse(uniqueActivityList.contains(FIXED_ACTIVITY_1));
    }

    @Test
    public void containsActivityListInListReturnsTrue() {
        uniqueActivityList.add(FIXED_ACTIVITY_1);
        assertTrue(uniqueActivityList.contains(FIXED_ACTIVITY_1));
    }

    @Test
    public void containsActivityListWithSameIdentifyFieldsInListReturnsTrue() {
        //Slightly different from UniquePersonListTest, due to different
        //contains convention used for AB3 and for ours.
        uniqueActivityList.add(FIXED_ACTIVITY_1);
        Activity activity = new Activity(new Title("Hot Spring"),
                new Priority("1"),new Duration("3"),
                new Location("Hokkaido"), new HashSet<>());
        assertTrue(uniqueActivityList.contains(activity));
    }

    @Test
    public void addNullActivityListThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueActivityList.add(null));
    }

    @Test
    public void addDuplicateActivityListThrowsDuplicateActivityListException() {
        uniqueActivityList.add(FIXED_ACTIVITY_1);
        assertThrows(DuplicateActivityException.class, () -> uniqueActivityList.add(FIXED_ACTIVITY_1));
    }

    @Test
    public void setActivityListNullTargetActivityListThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueActivityList.setActivity(null,
                FIXED_ACTIVITY_1));
    }

    @Test
    public void setActivityListNullActivityListThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueActivityList
                .setActivity(FIXED_ACTIVITY_1,
                        null));
    }

    @Test
    public void setActivityListTargetActivityListNotInListThrowsActivityListNotFoundException() {
        assertThrows(ActivityNotFoundException.class, () -> uniqueActivityList.setActivity(
                FIXED_ACTIVITY_1, FIXED_ACTIVITY_1));
    }

    @Test
    public void setActivityListEditedActivityListIsSameActivityListSuccess() {
        uniqueActivityList.add(FIXED_ACTIVITY_1);
        uniqueActivityList.setActivity(FIXED_ACTIVITY_1, FIXED_ACTIVITY_1);
        UniqueActivityList expectedActivityList = new UniqueActivityList();
        expectedActivityList.add(FIXED_ACTIVITY_1);
        assertEquals(expectedActivityList, uniqueActivityList);
    }

    @Test
    public void setActivityListHasSameIdentitySuccess() {
        uniqueActivityList.add(FIXED_ACTIVITY_1);
        Activity activity = new Activity(new Title("Hot Spring"), new Priority("1"),
                new Duration("3"),
                new Location("Hokkaido"), new HashSet<>());
        uniqueActivityList.setActivity(FIXED_ACTIVITY_1, activity);
        UniqueActivityList expectedUniqueActivityList = new UniqueActivityList();
        expectedUniqueActivityList.add(activity);
        assertEquals(expectedUniqueActivityList, this.uniqueActivityList);
    }

    @Test
    public void setActivityListEditedActivityListHasDifferentActivityListSuccess() {
        uniqueActivityList.add(FIXED_ACTIVITY_1);
        uniqueActivityList.setActivity(FIXED_ACTIVITY_1, FIXED_ACTIVITY_2);
        UniqueActivityList expectedUniqueActivityList = new UniqueActivityList();
        expectedUniqueActivityList.add(FIXED_ACTIVITY_2);
        assertEquals(expectedUniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setActivityListEditedActivityListHasNonUniqueIdentityThrowsDuplicateActivityListException() {
        uniqueActivityList.add(FIXED_ACTIVITY_1);
        uniqueActivityList.add(FIXED_ACTIVITY_2);
        assertThrows(DuplicateActivityException.class, () -> uniqueActivityList.setActivity(
                FIXED_ACTIVITY_1, FIXED_ACTIVITY_2));
    }

    @Test
    public void removeNullActivityListThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueActivityList.remove(null));
    }

    @Test
    public void removeActivityListDoesNotExistThrowsActivityListNotFoundException() {
        assertThrows(ActivityNotFoundException.class, () -> uniqueActivityList
                .remove(FIXED_ACTIVITY_3));
    }

    @Test
    public void removeExistingActivityListRemovesActivityList() {
        uniqueActivityList.add(FIXED_ACTIVITY_1);
        uniqueActivityList.remove(FIXED_ACTIVITY_1);
        UniqueActivityList expectedActivityList = new UniqueActivityList();
        assertEquals(expectedActivityList, uniqueActivityList);
    }

    @Test
    public void setActivityListNullUniqueActivityListThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueActivityList
                .setActivity((UniqueActivityList) null));
    }

    @Test
    public void setActivityListUniqueActivityListReplacesOwnListWithProvidedUniqueActivityList() {
        uniqueActivityList.add(FIXED_ACTIVITY_3);
        UniqueActivityList expectedUniqueActivityList = new UniqueActivityList();
        uniqueActivityList.add(FIXED_ACTIVITY_1);
        uniqueActivityList.setActivity(expectedUniqueActivityList);
        assertEquals(expectedUniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setUniqueActivityListNullListThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueActivityList
                .setActivity((List<Activity>) null));
    }

    @Test
    public void setUniqueActivityListListReplacesOwnListWithProvidedList() {
        uniqueActivityList.add(FIXED_ACTIVITY_1);
        List<Activity> activityLists = Collections.singletonList(FIXED_ACTIVITY_3);
        uniqueActivityList.setActivity(activityLists);
        UniqueActivityList expectedUniqueActivityList = new UniqueActivityList();
        expectedUniqueActivityList.add(FIXED_ACTIVITY_3);
        assertEquals(expectedUniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setActivityListWithDuplicateActivityThrowsDuplicateActivityException() {
        List<Activity> listWithDuplicateActivity = Arrays
                .asList(FIXED_ACTIVITY_1, FIXED_ACTIVITY_1);
        assertThrows(DuplicateActivityException.class, ()
                -> uniqueActivityList.setActivity(listWithDuplicateActivity));
    }

    @Test
    public void asUnmodifiableObservableListModifyListThrowsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueActivityList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void testEquals() {
        UniqueActivityList expectedUniqueActivityList = new UniqueActivityList();
        expectedUniqueActivityList.add(FIXED_ACTIVITY_1);
        uniqueActivityList.add(FIXED_ACTIVITY_1);
        assertEquals(uniqueActivityList, expectedUniqueActivityList);

    }

    @Test
    public void testHashCode() {
        //Same Hash Code
        uniqueActivityList.add(FIXED_ACTIVITY_1);
        UniqueActivityList list = new UniqueActivityList();
        list.add(FIXED_ACTIVITY_1);
        assertEquals(list.hashCode(), uniqueActivityList.hashCode());

        //Different Hash code
        UniqueActivityList diffPl = new UniqueActivityList();
        diffPl.add(FIXED_ACTIVITY_3);
        assertNotEquals(diffPl.hashCode(), uniqueActivityList.hashCode());
    }
}
