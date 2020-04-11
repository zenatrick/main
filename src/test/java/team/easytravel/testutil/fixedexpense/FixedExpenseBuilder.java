package team.easytravel.testutil.fixedexpense;

import team.easytravel.model.listmanagers.fixedexpense.Amount;
import team.easytravel.model.listmanagers.fixedexpense.Description;
import team.easytravel.model.listmanagers.fixedexpense.FixedExpense;
import team.easytravel.model.listmanagers.fixedexpense.FixedExpenseCategory;

/**
 * A utility class to help with building Fixed Expense objects.
 */
public class FixedExpenseBuilder {

    public static final String DEFAULT_AMOUNT = "1000";
    public static final String DEFAULT_DESCRIPTION = "TestExpense";
    public static final String DEFAULT_FIXED_EXPENSE_CATEGORY = "others";

    private Amount amount;
    private Description description;
    private FixedExpenseCategory fixedExpenseCategory;

    public FixedExpenseBuilder() {
        amount = new Amount(DEFAULT_AMOUNT);
        description = new Description(DEFAULT_DESCRIPTION);
        fixedExpenseCategory = new FixedExpenseCategory(DEFAULT_FIXED_EXPENSE_CATEGORY);
    }

    /**
     * Initializes the FixedExpenseBuilder with the data of {@code fixedExpense}.
     */
    public FixedExpenseBuilder(FixedExpense fixedExpense) {
        amount = fixedExpense.getAmount();
        description = fixedExpense.getDescription();
        fixedExpenseCategory = fixedExpense.getFixedExpenseCategory();
    }

    /**
     * Parses the {@code Amount} of the {@code FixedExpense} that we are building.
     */
    public FixedExpenseBuilder withAmount(String amount) {
        this.amount = new Amount(amount);
        return this;
    }

    /**
     * Parses the {@code Description} of the {@code FixedExpense} that we are building.
     */
    public FixedExpenseBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Parses the {@code FixedExpenseCategory} of the {@code FixedExpense} that we are building.
     */
    public FixedExpenseBuilder withFixedExpenseCategory(String fixedExpenseCategory) {
        this.fixedExpenseCategory = new FixedExpenseCategory(fixedExpenseCategory);
        return this;
    }

    public FixedExpense build() {
        return new FixedExpense(amount, description, fixedExpenseCategory);
    }



}
