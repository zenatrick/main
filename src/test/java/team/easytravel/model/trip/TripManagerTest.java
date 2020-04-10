package team.easytravel.model.trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static team.easytravel.testutil.Assert.assertThrows;
import static team.easytravel.testutil.activity.TypicalActivity.ACTIVITY_DISNEYLAND;
import static team.easytravel.testutil.activity.TypicalActivity.getTypicalActivityManager;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import team.easytravel.model.listmanagers.ActivityManager;
import team.easytravel.model.listmanagers.ReadOnlyActivityManager;
import team.easytravel.model.listmanagers.activity.Activity;
import team.easytravel.model.util.uniquelist.exceptions.DuplicateElementException;


class ActivityManagerTest {

    private final TripManager tripManager = new TripManager();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), tripManager.getDayScheduleEntryLists());
    }

    @Test
    public void resetDataNullThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tripManager.resetData(null));
    }

    @Test
    public void resetDataWithValidReadOnlyActivityManagerReplacesData() {
        TripManager newData = getTypicalTripManager();
        tripManager.resetData(newData);
        assertEquals(newData, tripManager);
    }

    @Test
    public void resetDataWithDuplicateActivityThrowsDuplicateElementException() {
        List<Activity> newActivity = Arrays.asList(ACTIVITY_DISNEYLAND,
                ACTIVITY_DISNEYLAND);
        team.easytravel.model.listmanagers.ActivityManagerTest.ActivityManagerStub newData = new team.easytravel.model.listmanagers.ActivityManagerTest.ActivityManagerStub(newActivity);
        assertThrows(DuplicateElementException.class, () -> tripManager.resetData(newData));
    }

    @Test
    public void hasActivityNullActivity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tripManager
                .hasActivity(null));
    }

    @Test
    public void hasActivityNotInActivityManagerReturnsFalse() {
        assertFalse(tripManager.hasActivity(ACTIVITY_DISNEYLAND));
    }

    @Test
    public void hasActivityManagerInActivityManagerReturnsTrue() {
        tripManager.addActivity(ACTIVITY_DISNEYLAND);
        assertTrue(tripManager.hasActivity(ACTIVITY_DISNEYLAND));
    }

    @Test
    public void getActivityModifyListThrowsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> tripManager
                .getActivityList().remove(0));
    }


    /**
     * A stub ReadOnlyActivityManager whose activity list can violate interface constraints.
     */
    private static class ActivityManagerStub implements ReadOnlyActivityManager {
        private final ObservableList<Activity> activityBookings =
                FXCollections.observableArrayList();

        ActivityManagerStub(Collection<Activity> activities) {
            this.activityBookings.setAll(activities);
        }

        @Override
        public ObservableList<Activity> getActivityList() {
            return activityBookings;
        }
    }
}
