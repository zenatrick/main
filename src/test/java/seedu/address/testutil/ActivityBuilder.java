package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.listmanagers.activity.Activity;
import seedu.address.model.listmanagers.activity.Duration;
import seedu.address.model.listmanagers.activity.Priority;
import seedu.address.model.util.attributes.Location;
import seedu.address.model.util.attributes.Title;
import seedu.address.model.util.attributes.tag.Tag;
import seedu.address.model.util.sampledata.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class ActivityBuilder {

    public static final String DEFAULT_TITLE = "CHEESELAND OWO";
    public static final Integer DEFAULT_PRIORITY = 1;
    public static final Integer DEFAULT_DURATION = 2;
    public static final String DEFAULT_LOCATION = "LAND OF CHEESE";

    private Title title;
    private Priority priority;
    private Duration duration;
    private Location location;
    private Set<Tag> tags;

    public ActivityBuilder() {
        title = new Title(DEFAULT_TITLE);
        priority = new Priority(DEFAULT_PRIORITY);
        duration = new Duration(DEFAULT_DURATION);
        location = new Location(DEFAULT_LOCATION);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code activity}.
     */
    public ActivityBuilder(Activity activity) {
        title = activity.getTitle();
        priority = activity.getPriority();
        duration = activity.getDuration();
        location = activity.getLocation();
        tags = new HashSet<>(activity.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public ActivityBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Activity} that we are building.
     */
    public ActivityBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Activity} that we are building.
     */
    public ActivityBuilder withLocation(String address) {
        this.location = new Location(address);
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code Activity} that we are building.
     */
    public ActivityBuilder withPriority(Integer priority) {
        this.priority = new Priority(priority);
        return this;
    }

    /**
     * Sets the {@code Duration} of the {@code Activity} that we are building.
     */
    public ActivityBuilder withDuration(Integer duration) {
        this.duration = new Duration(duration);
        return this;
    }

    public Activity build() {
        return new Activity(title, priority, duration, location, tags);
    }

}
