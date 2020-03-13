package seedu.address.model.fixedexpense;

import java.util.Objects;

/**
 * Represent a FixedExpense in the FixedExpenseManager.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class FixedExpense {

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
        return otherFixedExpense.getAmount().equals(getAmount())
                && otherFixedExpense.getDescription().equals(getDescription())
                && otherFixedExpense.getCategory().equals(getCategory());
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
