package seedu.address.model.budget;

/**
 * For the user to outline all their costs.
 */
public class Cost {

    private final Description description;
    private final Type type;
    private final FixedCost fixedCost;
    private final Budget budget;

    public Cost(Description description, Type type, FixedCost fixedCost, Budget budget) {
        this.description = description;
        this.type = type;
        this.fixedCost = fixedCost;
        this.budget = budget;
    }
}
