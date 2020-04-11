package team.easytravel.logic.commands.fixedexpense;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static team.easytravel.testutil.Assert.assertThrows;
import static team.easytravel.testutil.TypicalIndexes.INDEX_FIRST;
import static team.easytravel.testutil.TypicalIndexes.INDEX_SECOND;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import team.easytravel.commons.core.index.Index;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.model.ModelStub;
import team.easytravel.model.listmanagers.FixedExpenseManager;
import team.easytravel.model.listmanagers.ReadOnlyFixedExpenseManager;
import team.easytravel.model.listmanagers.fixedexpense.FixedExpense;
import team.easytravel.testutil.fixedexpense.FixedExpenseBuilder;

class DeleteFixedExpenseCommandTest {

    @Test
    public void execute_tripNotSet_throwsCommandException() {
        ModelStubNoTripSet modelStub = new ModelStubNoTripSet();
        assertThrows(CommandException.class, ()-> new DeleteFixedExpenseCommand(Index.fromZeroBased(0))
                .execute(modelStub));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() throws CommandException {
        ModelStub modelStub = new ModelStubAcceptingFixedExpenseAdded();
        FixedExpense fixedExpenseToDelete = modelStub.getFilteredFixedExpenseList().get(INDEX_FIRST.getZeroBased());
        CommandResult commandResult = new DeleteFixedExpenseCommand(INDEX_FIRST)
                .execute(modelStub);
        int expectedBudget = (int) (modelStub.getBudget() - Double.parseDouble(fixedExpenseToDelete.getAmount().value));
        String expectedMessage = String.format(DeleteFixedExpenseCommand.MESSAGE_DELETE_FIXEDEXPENSE_SUCCESS,
                fixedExpenseToDelete + "\n"
                + "Your remaining budget is: " + expectedBudget);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        ModelStub modelStub = new ModelStubAcceptingFixedExpenseAdded();
        assertThrows(CommandException.class, ()-> new DeleteFixedExpenseCommand(INDEX_SECOND).execute(modelStub));
    }

    /**
     * A Model stub that does not have a trip set.
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
        public void deleteFixedExpense(FixedExpense fixedExpense) {
            fixedExpensesAdded.remove(fixedExpense);
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



}
