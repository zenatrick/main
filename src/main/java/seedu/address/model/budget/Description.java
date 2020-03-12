package seedu.address.model.budget;


/**
 * For the user to outline their fixedCost Description.
 */

public class Description {

    private final String description;

    public Description(String cost) {
        this.description = cost;
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && description.equals(((Description) other).description)); // state check
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }

}
