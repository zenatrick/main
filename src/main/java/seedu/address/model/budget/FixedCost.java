package seedu.address.model.budget;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represent a FixedCost in the trip.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class FixedCost {

    //This will be my person.

    private final Cost cost;
    private final Description description;
    private final Set<Tag> tags = new HashSet<>();

    public FixedCost(Cost cost, Description description, Set<Tag> tags) {
        this.cost = cost;
        this.description = description;
        this.tags.addAll(tags);
    }

    public Cost getCost() {
        return cost;
    }

    public Description getDescription() {
        return description;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }


    /**
     * Returns true if both fixedCost of the same description have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */

    public boolean isSameFixedCost(FixedCost otherFixedCost) {
        if (otherFixedCost == this) {
            return true;
        }

        return otherFixedCost != null
                && otherFixedCost.getDescription().equals(getDescription())
                && otherFixedCost.getCost().equals(getCost());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FixedCost)) {
            return false;
        }

        FixedCost otherFixedCost = (FixedCost) other;
        return otherFixedCost.getDescription().equals(getDescription())
                && otherFixedCost.getCost().equals(getCost())
                && otherFixedCost.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, cost, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription())
                .append(" Description: ")
                .append(getCost())
                .append(" Cost: ")
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
