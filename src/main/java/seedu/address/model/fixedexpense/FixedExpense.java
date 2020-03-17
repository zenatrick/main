package seedu.address.model.fixedexpense;

import java.util.Objects;

import seedu.address.model.util.uniquelist.UniqueListElement;

/**
 * Represent a FixedExpense in the FixedExpenseManager.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class FixedExpense implements UniqueListElement {

    private final Amount amount;
    private final Description description;
    private final Category category;

    public FixedExpense(Amount amount, Description description, Category category) {
        this.amount = amount;
        this.description = description;
        this.category = category;
    }

    public Amount getAmount() {
        return amount;
    }

    public Description getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    /**
     * Returns true if this fixed expense is equal to {@code other} as per {@link #equals(Object)}.
     * This defines an equality between two fixed expenses.
     */
    @Override
    public boolean isSame(UniqueListElement other) {
        return equals(other);
    }

    /**
     * Returns true if both persons have the same data fields.
     * This defines a notion of equality between two fixed expenses.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FixedExpense)) {
            return false;
        }

        FixedExpense otherFixedExpense = (FixedExpense) other;
        return amount.equals(otherFixedExpense.amount)
                && description.equals(otherFixedExpense.description)
                && category.equals(otherFixedExpense.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, description, category);
    }

    @Override
    public String toString() {
        return "Fixed Expense Entry - Description: " + description
                + " Amount: " + amount
                + " Category: " + category;
    }
}
