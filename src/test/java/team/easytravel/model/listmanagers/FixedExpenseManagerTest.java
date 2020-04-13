package team.easytravel.model.listmanagers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static team.easytravel.testutil.Assert.assertThrows;
import static team.easytravel.testutil.TypicalFixedExpense.FIXED_EXPENSE_PLANE;
import static team.easytravel.testutil.TypicalFixedExpense.FIXED_EXPENSE_WIFI;
import static team.easytravel.testutil.TypicalFixedExpense.getTypicalFixedExpenseManager;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import team.easytravel.model.listmanagers.fixedexpense.FixedExpense;
import team.easytravel.model.util.uniquelist.exceptions.DuplicateElementException;
import team.easytravel.testutil.TypicalFixedExpense;

class FixedExpenseManagerTest {

    private final FixedExpenseManager fixedExpenseManager = new FixedExpenseManager();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), fixedExpenseManager.getFixedExpenseList());
    }

    @Test
    public void resetDataNullThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> fixedExpenseManager.resetData(null));
    }

    @Test
    public void resetDataWithValidReadOnlyFixedExpenseManagerReplacesData() {
        FixedExpenseManager newData = getTypicalFixedExpenseManager();
        fixedExpenseManager.resetData(newData);
        assertEquals(newData, fixedExpenseManager);
    }

    @Test
    public void resetDataWithDuplicateFixedExpenseThrowsDuplicateElementException() {
        List<FixedExpense> newFixedExpense = Arrays.asList(FIXED_EXPENSE_PLANE, FIXED_EXPENSE_PLANE);
        FixedExpenseManagerStub newData = new FixedExpenseManagerStub(newFixedExpense);
        assertThrows(DuplicateElementException.class, () -> fixedExpenseManager.resetData(newData));
    }

    @Test
    public void hasFixedExpenseNullFixedExpenseThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> fixedExpenseManager
                .hasFixedExpense(null));
    }

    @Test
    public void hasFixedExpenseNotInFixedExpenseManagerReturnsFalse() {
        assertFalse(fixedExpenseManager.hasFixedExpense(FIXED_EXPENSE_WIFI));
    }

    @Test
    public void hasFixedExpenseInAFixedExpenseManagerReturnsTrue() {
        fixedExpenseManager.addFixedExpense(FIXED_EXPENSE_WIFI);
        assertTrue(fixedExpenseManager.hasFixedExpense(FIXED_EXPENSE_WIFI));
    }

    @Test
    public void getFixedExpenseModifyListThrowsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> fixedExpenseManager
                .getFixedExpenseList().remove(0));
    }

    @Test
    public void getStatusNoExpense() {
        final double BUDGET = 1000.00;
        String result = "[❗] There is no expense entered. You can add expense using the \"addexpense\" command.";
        assertEquals(result, fixedExpenseManager.getStatus(BUDGET));
    }

    @Test
    public void getStatusWithRemainingBudget() {
        final double BUDGET = 1000.00;
        fixedExpenseManager.addFixedExpense(FIXED_EXPENSE_WIFI);
        String result = "[✔] Your remaining budget is $970.00.";
        assertEquals(result, fixedExpenseManager.getStatus(BUDGET));
    }

    @Test
    public void getStatusWithExceedBudget() {
        final double BUDGET = 1000.00;
        fixedExpenseManager.addFixedExpense(FIXED_EXPENSE_PLANE);
        String result = "[❌] You have exceeded your budget by 2000.00!";
        assertEquals(result, fixedExpenseManager.getStatus(BUDGET));
    }

    /**
     * A stub ReadOnlyFixedExpenseManager whose Fixed Expense list can violate interface constraints.
     */
    private static class FixedExpenseManagerStub implements ReadOnlyFixedExpenseManager {
        private final ObservableList<FixedExpense> fixedExpenses =
                FXCollections.observableArrayList();

        FixedExpenseManagerStub(Collection<FixedExpense> fixedExpenseBookings) {
            this.fixedExpenses.setAll(fixedExpenseBookings);
        }

        @Override
        public ObservableList<FixedExpense> getFixedExpenseList() {
            return fixedExpenses;
        }
    }
}
