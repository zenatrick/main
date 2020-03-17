package seedu.address.model.listmanagers.activity;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.util.attributes.Location;
import seedu.address.model.util.attributes.tag.Tag;
import seedu.address.model.util.uniquelist.UniqueListElement;

/**
 * Represents a Activity in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Activity implements UniqueListElement {

    // Identity fields
    private final Title title;
    private final Duration duration;
    private final Location location;

    // Data fields
    private final Priority priority;
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
     * Returns true if both activities of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two Activities.
     */
    public boolean isSameActivity(seedu.address.model.listmanagers.activity.Activity activity) {
        if (activity == this) {
            return true;
        }

        return activity != null
                && activity.getTitle().equals(getTitle())
                && (activity.getPriority().equals(getPriority()) || activity.getDuration().equals(getDuration()));
    }

    /**
     * Returns true if both accommodation bookings has the same identity fields.
     * This defines a weaker notion of equality between two accommodation bookings.
     */
    @Override
    public boolean isSame(UniqueListElement other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Activity)) {
            return false;
        }

        Activity otherActivity = (Activity) other;
        return title.equals(otherActivity.title)
                && duration.equals(otherActivity.duration)
                && location.equals(otherActivity.location);
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

        if (!(other instanceof Activity)) {
            return false;
        }

        Activity otherActivity = (Activity) other;
        return title.equals(otherActivity.title)
                && duration.equals(otherActivity.duration)
                && location.equals(otherActivity.location)
                && priority.equals(otherActivity.priority)
                && getTags().equals(otherActivity.getTags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, priority, duration, location, tags);
    }

    @Override
    public String toString() {
        return "Activity - Title: " + title
                + " Priority: " + priority
                + " Duration: " + duration
                + " Tags: "
                + getTags()
                .stream()
                .map(Tag::toString)
                .collect(Collectors.joining(" "));
    }
}

