package seedu.address.storage.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.activity.Activity;
import seedu.address.model.listmanager.ActivityManager;
import seedu.address.model.listmanager.ReadOnlyActivityManager;


/**
 * An Immutable activityManager that is serializable to JSON format.
 */
@JsonRootName(value = "activityManager")
class JsonSerializableActivityManager {

    public static final String MESSAGE_DUPLICATE_ACTIVITY = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedActivityManager> activities = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableActivityManager} with the given activities.
     */
    @JsonCreator
    public JsonSerializableActivityManager(@JsonProperty("activities") List<JsonAdaptedActivityManager> activities) {
        this.activities.addAll(activities);
    }

    /**
     * Converts a given {@code ReadOnlyActivityManager} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableActivityManager}.
     */
    public JsonSerializableActivityManager(ReadOnlyActivityManager source) {
        activities.addAll(source.getActivityList().stream().map(JsonAdaptedActivityManager::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this activityManager into the model's {@code ActivityManager} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ActivityManager toModelType() throws IllegalValueException {
        ActivityManager activityManager = new ActivityManager();
        for (JsonAdaptedActivityManager jsonAdaptedActivityManager : activities) {
            Activity activity = jsonAdaptedActivityManager.toModelType();
            if (activityManager.hasActivity(activity)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ACTIVITY);
            }
            activityManager.addActivity(activity);
        }
        return activityManager;
    }

}
