package seedu.address.model.activity;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.transportbooking.Location;
/**
 * Represents a Activity in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Activity {

    // Identity fields
    private final Title title;
    private final Priority priority;
    private final Duration duration;
    private final Location location;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Activity(Title title, Priority priority, Duration duration, Location location, Set<Tag> tags) {
        requireAllNonNull(title, priority, duration, location, tags);
        this.title = title;
        this.priority = priority;
        this.duration = duration;
        this.location = location;
        this.tags.addAll(tags);
    }

    public Title getTitle() {
        return title;
    }

    public Priority getPriority() {
        return priority;
    }

    public Duration getDuration() {
        return duration;
    }

    public Location getLocation() {
        return location;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both Activities of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two Activities.
     */
    public boolean isSameActivity(seedu.address.model.activity.Activity activity) {
        if (activity == this) {
            return true;
        }

        return activity != null
                && activity.getTitle().equals(getTitle())
                && (activity.getPriority().equals(getPriority()) || activity.getDuration().equals(getDuration()));
    }

    /**
     * Returns true if both Activities have the same identity and data fields.
     * This defines a stronger notion of equality between two Activities.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof seedu.address.model.activity.Activity)) {
            return false;
        }

        seedu.address.model.activity.Activity otherActivity = (seedu.address.model.activity.Activity) other;
        return otherActivity.getTitle().equals(getTitle())
                && otherActivity.getPriority().equals(getPriority())
                && otherActivity.getDuration().equals(getDuration())
                && otherActivity.getLocation().equals(getLocation())
                && otherActivity.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, priority, duration, location, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append(" Title: ")
                .append(getPriority())
                .append(" Priority: ")
                .append(getDuration())
                .append(" Duration: ")
                .append(getLocation())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}

