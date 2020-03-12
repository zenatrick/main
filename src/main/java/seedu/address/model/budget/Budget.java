package seedu.address.model.budget;

/**
 * Allow users to Fix a budget. Monetary value $$$
 * Guarantees: details are present and not null, field values are validated, immutable.
 */

public class Budget {

    private final String budgetAmount;
    private int finalBudgetAmount;

    public Budget(String budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public String getBudgetAmount() {
        return budgetAmount;
    }

    public int getFinalBudgetAmount() {
        return new Amount(budgetAmount).getAmountOutput();
    }

    public void setFinalBudgetAmount(int finalBudgetAmount) {
        this.finalBudgetAmount = finalBudgetAmount;
    }
}
