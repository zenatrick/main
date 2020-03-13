package seedu.address.model.trip;

import java.util.Objects;

import seedu.address.model.budget.FixedCost;

/**
 * This represents the "Person" of our address book.
 * Main Class of our address book.
 */
public class Trip {

    //Data Fields
    private final FixedCost fixedCost;

    public Trip(FixedCost fixedCost) {
        this.fixedCost = fixedCost;
    }

    public FixedCost getFixedCost() {
        return fixedCost;
    }

    /**
     * Returns true if both Et of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameEt(Trip otherTrip) {
        if (otherTrip == this) {
            return true;
        }

        return otherTrip != null
                && otherTrip.getFixedCost().equals(getFixedCost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(fixedCost);
    }

    /**
     * Returns true if both Et have the same identity and data fields.
     * This defines a stronger notion of equality between two Et.
     */
    @Override
    public boolean equals(Object other) {

        if (other == this) {
            return true;
        }

        if (!(other instanceof Trip)) {
            return false;
        }

        Trip otherTrip = (Trip) other;
        return otherTrip.getFixedCost().equals(getFixedCost());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getFixedCost())
                .append(" Fixed Cost: ");
        return builder.toString();
    }

}
