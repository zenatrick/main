package seedu.address.model.budget;

/**
 * For the user to outline all their costs.
 * And see how much money they are left
 */
public class Cost {

    private final String cost;

    public Cost(String cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return cost;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Cost // instanceof handles nulls
                && cost.equals(((Cost) other).cost)); // state check
    }

    @Override
    public int hashCode() {
        return cost.hashCode();
    }

}
