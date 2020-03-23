package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.listmanagers.FixedExpenseManager;
import seedu.address.model.listmanagers.fixedexpense.Amount;
import seedu.address.model.listmanagers.fixedexpense.Category;
import seedu.address.model.listmanagers.fixedexpense.Description;
import seedu.address.model.listmanagers.fixedexpense.FixedExpense;

public class TypicalFixedExpense {

    public static final FixedExpense FIXED_EXPENSE_PLANE = new FixedExpense(
            new Amount("3000"), new Description("Flights"), new Category("Travel")
    );

    public static final FixedExpense FIXED_EXPENSE_WIFI = new FixedExpense(
            new Amount("30"), new Description("Wifi"), new Category("Essential")
    );

    public static final FixedExpense FIXED_EXPENSE_HOTELS = new FixedExpense(
            new Amount("2500"), new Description("Price of hotel"), new Category("Hotels")
    );

    private TypicalFixedExpense() {}

    /**
     * Returns an {@code TypicalFixedExpense} with all the typical FixedExpense.
     */

    public static FixedExpenseManager getTypicalFixedExpenseManager() {
        FixedExpenseManager fe = new FixedExpenseManager();
        for(FixedExpense fixedExpense: getTypicalFixedExpenses()) {
            fe.addFixedExpense(fixedExpense);
        }
        return fe;
    }

    public static List<FixedExpense> getTypicalFixedExpenses() {
        return new ArrayList<>(Arrays.asList(FIXED_EXPENSE_PLANE, FIXED_EXPENSE_WIFI,
                FIXED_EXPENSE_HOTELS));
    }
}
