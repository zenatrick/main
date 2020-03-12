package seedu.address.model.et;

import java.util.Objects;

import seedu.address.model.accommodation.Accommodation;
import seedu.address.model.activity.Activity;
import seedu.address.model.budget.FixedCost;
import seedu.address.model.packinglist.PackingList;
import seedu.address.model.transportation.Transportation;

/**
 * This represents the "Person" of our address book.
 * Main Class of our address book.
 */
public class Et {

    //Data Fields
    private final Activity activity;
    private final Accommodation accommodation;
    private final Transportation transportation;
    private final FixedCost fixedCost;
    private final PackingList packingList;

    public Et(Activity activity, Accommodation accommodation, Transportation transportation, FixedCost fixedCost,
              PackingList packingList) {
        this.activity = activity;
        this.accommodation = accommodation;
        this.transportation = transportation;
        this.fixedCost = fixedCost;
        this.packingList = packingList;
    }

    // Standard getter methods.
    public Activity getActivity() {
        return activity;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public Transportation getTransportation() {
        return transportation;
    }

    public FixedCost getFixedCost() {
        return fixedCost;
    }

    public PackingList getPackingList() {
        return packingList;
    }

    /**
     * Returns true if both Et of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameEt(Et otherEt) {
        if (otherEt == this) {
            return true;
        }

        return otherEt != null
                && otherEt.getActivity().equals(getActivity())
                && (otherEt.getAccommodation().equals(getAccommodation())
                || otherEt.getTransportation().equals(getTransportation())
                || otherEt.getFixedCost().equals(getFixedCost()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(activity, accommodation, transportation, fixedCost, packingList);
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

        if (!(other instanceof Et)) {
            return false;
        }

        Et otherEt = (Et) other;
        return otherEt.getActivity().equals(getActivity())
                && otherEt.getAccommodation().equals(getAccommodation())
                && otherEt.getTransportation().equals(getTransportation())
                && otherEt.getFixedCost().equals(getFixedCost())
                && otherEt.getPackingList().equals(getPackingList());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getActivity())
                .append(" Activity: ")
                .append(getAccommodation())
                .append(" Accommodation: ")
                .append(getTransportation())
                .append(" Transportation: ")
                .append(getFixedCost())
                .append(" Fixed Cost: ");
        return builder.toString();
    }

}
