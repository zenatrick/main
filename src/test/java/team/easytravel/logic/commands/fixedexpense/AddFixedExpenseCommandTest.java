package team.easytravel.logic.commands.fixedexpense;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static team.easytravel.logic.commands.CommandResult.Action.SWITCH_TAB_FIXED_EXPENSE;
import static team.easytravel.logic.commands.fixedexpense.AddFixedExpenseCommand.MESSAGE_EXCEED_BUDGET;
import static team.easytravel.logic.commands.fixedexpense.AddFixedExpenseCommand.MESSAGE_SUCCESS;
import static team.easytravel.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.model.ModelStub;
import team.easytravel.model.listmanagers.FixedExpenseManager;
import team.easytravel.model.listmanagers.ReadOnlyFixedExpenseManager;
import team.easytravel.model.listmanagers.fixedexpense.FixedExpense;
import team.easytravel.testutil.fixedexpense.FixedExpenseBuilder;

class AddFixedExpenseCommandTest {
    @Test
    public void constructor_nullFixedExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddFixedExpenseCommand(null, false));
    }

    @Test

    public void execute_addFixedExpenseWithoutTripSet_throwsCommandException() {
        ModelStubNoTripSet modelStub = new ModelStubNoTripSet();
        FixedExpense validFixedExpense = new FixedExpenseBuilder().build();
        assertThrows(CommandException.class, () -> new AddFixedExpenseCommand(validFixedExpense, false)
                .execute(modelStub));

    }

    @Test
    public void execute_fixedExpenseAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingFixedExpenseAdded modelStub = new ModelStubAcceptingFixedExpenseAdded();
        FixedExpense validFixedExpense = new FixedExpenseBuilder().build();

        CommandResult commandResult = new AddFixedExpenseCommand(validFixedExpense, false)
                .execute(modelStub);


        assertEquals(String.format(String.format(MESSAGE_SUCCESS, validFixedExpense + "\n"
                        + "Your budget left is now " + modelStub.getBudget()), SWITCH_TAB_FIXED_EXPENSE),
                commandResult.getFeedbackToUser());
        assertEquals(Collections.singletonList(validFixedExpense), modelStub.fixedExpensesAdded);
    }

    @Test
    public void execute_fixedExpenseAcceptedByModel_addSuccessfulBelowBudget() throws Exception {
        ModelStubAcceptingFixedExpenseAddedBelowBudget modelStub = new ModelStubAcceptingFixedExpenseAddedBelowBudget();
        FixedExpense validFixedExpense = new FixedExpenseBuilder().build();
        CommandResult commandResult = new AddFixedExpenseCommand(validFixedExpense, false)
                .execute(modelStub);
        assertEquals(String.format(MESSAGE_SUCCESS, validFixedExpense + "\n"
                + MESSAGE_EXCEED_BUDGET + "\nCurrently, your Highest expense is "
                + validFixedExpense), commandResult.getFeedbackToUser());
        assertEquals(Collections.singletonList(validFixedExpense), modelStub.fixedExpensesAdded);
    }

    @Test
    public void execute_fixedExpenseOverseasAmountAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingFixedExpenseAdded modelStub = new ModelStubAcceptingFixedExpenseAdded();
        FixedExpense validFixedExpense = new FixedExpenseBuilder().build();

        CommandResult commandResult = new AddFixedExpenseCommand(validFixedExpense, true)
                .execute(modelStub);
        assertEquals(String.format(String.format(MESSAGE_SUCCESS, validFixedExpense + "\n"
                        + "Your budget left is now " + modelStub.getBudget()), SWITCH_TAB_FIXED_EXPENSE),
                commandResult.getFeedbackToUser());
        assertEquals(Collections.singletonList(validFixedExpense), modelStub.fixedExpensesAdded);
    }


    @Test
    public void execute_duplicateFixedExpenses_throwsCommandException() {
        FixedExpense validFixedExpense = new FixedExpenseBuilder().build();
        AddFixedExpenseCommand addCommand = new AddFixedExpenseCommand(validFixedExpense, false);
        ModelStub modelStub = new ModelStubWithFixedExpense(validFixedExpense);

        assertThrows(CommandException.class, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        FixedExpense testExpense = new FixedExpenseBuilder().withAmount("1000").build();
        FixedExpense testExpense2 = new FixedExpenseBuilder().withAmount("3000").build();
        AddFixedExpenseCommand addTestExpenseCommand = new AddFixedExpenseCommand(testExpense, false);
        AddFixedExpenseCommand addTestExpense2Command = new AddFixedExpenseCommand(testExpense2, false);

        // same object -> returns true
        assertEquals(addTestExpenseCommand, addTestExpenseCommand);

        // same values -> returns true
        AddFixedExpenseCommand addTestExpenseCommandCopy = new AddFixedExpenseCommand(testExpense,
                false);
        assertEquals(addTestExpenseCommand, addTestExpenseCommandCopy);

        // different types -> returns false
        assertNotEquals(1, addTestExpenseCommand);

        // null -> returns false
        assertNotEquals(null, addTestExpenseCommand);

        // different person -> returns false
        assertNotEquals(addTestExpenseCommand, addTestExpense2Command);
    }

    /**
     * A Model stub that contains a single fixed expense.
     */
    private class ModelStubWithFixedExpense extends ModelStub {
        private final FixedExpense fixedExpense;

        ModelStubWithFixedExpense(FixedExpense fixedExpense) {
            requireNonNull(fixedExpense);
            this.fixedExpense = fixedExpense;
        }

        @Override
        public boolean hasTrip() {
            return true;
        }

        @Override
        public boolean hasFixedExpense(FixedExpense fixedExpense) {
            requireNonNull(fixedExpense);
            return this.fixedExpense.isSame(fixedExpense);
        }
    }

    /**
     * A Model stub that has no trip set.
     */
    private class ModelStubNoTripSet extends ModelStub {
        @Override
        public boolean hasTrip() {
            return false;
        }
    }

    /**
     * A Model stub that always accept the fixed expense being added.
     */
    private class ModelStubAcceptingFixedExpenseAdded extends ModelStub {
        final ArrayList<FixedExpense> fixedExpensesAdded = new ArrayList<>();

        @Override
        public boolean hasFixedExpense(FixedExpense fixedExpense) {
            requireNonNull(fixedExpense);
            return fixedExpensesAdded.stream().anyMatch(fixedExpense::isSame);
        }

        @Override
        public double getExchangeRate() {
            return 1.0;
        }

        @Override
        public boolean hasTrip() {
            return true;
        }

        @Override
        public double getTotalExpense() {
            return 0.0;
        }

        @Override
        public int getBudget() {
            return 10000;
        }

        @Override
        public ObservableList<FixedExpense> getFilteredFixedExpenseList() {
            List<FixedExpense> fe = new ArrayList<>();
            fe.add(new FixedExpenseBuilder().withAmount("1000").build());
            return FXCollections.observableList(fe);
        }

        @Override
        public void addFixedExpense(FixedExpense fixedExpense) {
            requireNonNull(fixedExpense);
            fixedExpensesAdded.add(fixedExpense);
        }

        @Override
        public ReadOnlyFixedExpenseManager getFixedExpenseManager() {
            return new FixedExpenseManager();
        }
    }

    /**
     * A Model stub that always accept the fixed expense being added and is below budget.
     */
    private class ModelStubAcceptingFixedExpenseAddedBelowBudget extends ModelStub {
        final ArrayList<FixedExpense> fixedExpensesAdded = new ArrayList<>();

        @Override
        public boolean hasFixedExpense(FixedExpense fixedExpense) {
            requireNonNull(fixedExpense);
            return fixedExpensesAdded.stream().anyMatch(fixedExpense::isSame);
        }

        @Override
        public boolean hasTrip() {
            return true;
        }

        @Override
        public double getTotalExpense() {
            return 10.0;
        }

        @Override
        public int getBudget() {
            return 0;
        }

        @Override
        public ObservableList<FixedExpense> getFilteredFixedExpenseList() {
            List<FixedExpense> fe = new ArrayList<>();
            fe.add(new FixedExpenseBuilder().withAmount("1000").build());
            return FXCollections.observableList(fe);
        }

        @Override
        public void addFixedExpense(FixedExpense fixedExpense) {
            requireNonNull(fixedExpense);
            fixedExpensesAdded.add(fixedExpense);
        }

        @Override
        public ReadOnlyFixedExpenseManager getFixedExpenseManager() {
            return new FixedExpenseManager();
        }
    }
}
