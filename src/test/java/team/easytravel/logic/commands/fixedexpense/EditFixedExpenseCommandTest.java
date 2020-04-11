package team.easytravel.logic.commands.fixedexpense;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static team.easytravel.commons.util.CollectionUtil.requireAllNonNull;
import static team.easytravel.logic.commands.fixedexpense.EditFixedExpenseCommand.MESSAGE_EDIT_FIXEDEXPENSE_SUCCESS;
import static team.easytravel.testutil.Assert.assertThrows;
import static team.easytravel.testutil.TypicalIndexes.INDEX_FIRST;
import static team.easytravel.testutil.TypicalIndexes.INDEX_SECOND;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.logic.commands.fixedexpense.EditFixedExpenseCommand.EditFixedExpenseDescriptor;
import team.easytravel.model.ModelStub;
import team.easytravel.model.listmanagers.FixedExpenseManager;
import team.easytravel.model.listmanagers.ReadOnlyFixedExpenseManager;
import team.easytravel.model.listmanagers.fixedexpense.FixedExpense;
import team.easytravel.model.trip.Trip;
import team.easytravel.model.trip.TripManager;
import team.easytravel.testutil.fixedexpense.EditFixedExpenseDescriptorBuilder;
import team.easytravel.testutil.fixedexpense.FixedExpenseBuilder;
import team.easytravel.testutil.trip.TripBuilder;

class EditFixedExpenseCommandTest {


    private TripManager tripManagerSet;

    @BeforeEach
    public void setUp() {
        Trip newTrip = new TripBuilder().build();
        tripManagerSet = new TripManager();
        tripManagerSet.setTrip(newTrip);
    }


    @Test
    public void execute_tripNotSet_throwsCommandException() {
        FixedExpense editedFixedExpense = new FixedExpenseBuilder().build();
        EditFixedExpenseDescriptor descriptor = new EditFixedExpenseDescriptorBuilder(editedFixedExpense).build();
        ModelStubNoTripSet modelStub = new ModelStubNoTripSet();
        assertThrows(CommandException.class, () -> new EditFixedExpenseCommand(INDEX_FIRST, descriptor, false)
                .execute(modelStub));
    }

    @Test
    public void execute_noDescriptorProvided_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditFixedExpenseCommand(INDEX_FIRST, null, false));
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() throws CommandException {
        ModelStubAcceptingFixedExpenseAdded modelStub =
                new ModelStubAcceptingFixedExpenseAdded();
        FixedExpense editedFixedExpense = new FixedExpenseBuilder().build();
        EditFixedExpenseDescriptor descriptor = new EditFixedExpenseDescriptorBuilder(editedFixedExpense).build();
        CommandResult commandResult = new EditFixedExpenseCommand(INDEX_FIRST, descriptor, false)
                .execute(modelStub);
        int currentBudget = (int) (modelStub.getBudget()
                - Double.parseDouble(editedFixedExpense.getAmount().value));
        String expectedMessage =
                String.format(MESSAGE_EDIT_FIXEDEXPENSE_SUCCESS, editedFixedExpense + "\n"
                        + "Your current budget left is " + currentBudget);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(Collections.singletonList(editedFixedExpense), modelStub.fixedExpensesAdded);
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredListOverseasValue_success() throws CommandException {
        ModelStubAcceptingFixedExpenseAdded modelStub =
                new ModelStubAcceptingFixedExpenseAdded();
        FixedExpense editedFixedExpense = new FixedExpenseBuilder().build();
        EditFixedExpenseDescriptor descriptor = new EditFixedExpenseDescriptorBuilder(editedFixedExpense).build();
        CommandResult commandResult = new EditFixedExpenseCommand(INDEX_FIRST, descriptor, true)
                .execute(modelStub);
        int currentBudget = (int) (modelStub.getBudget()
                - Double.parseDouble(editedFixedExpense.getAmount().value));
        String expectedMessage =
                String.format(MESSAGE_EDIT_FIXEDEXPENSE_SUCCESS, editedFixedExpense + "\n"
                        + "Your current budget left is " + currentBudget);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(Collections.singletonList(editedFixedExpense), modelStub.fixedExpensesAdded);
    }

    @Test
    public void execute_allFieldsIndexMoreThanListSize_throwsCommandException() {
        ModelStubAcceptingFixedExpenseAdded modelStub =
                new ModelStubAcceptingFixedExpenseAdded();
        FixedExpense editedFixedExpense = new FixedExpenseBuilder().build();
        EditFixedExpenseDescriptor descriptor = new EditFixedExpenseDescriptorBuilder(editedFixedExpense).build();

        assertThrows(CommandException.class, () -> new EditFixedExpenseCommand(INDEX_SECOND, descriptor, false)
                .execute(modelStub));
    }

    @Test
    public void equals() {
        FixedExpense testExpense = new FixedExpenseBuilder().withAmount("1000").build();
        FixedExpense testExpense2 = new FixedExpenseBuilder().withAmount("3000").build();
        EditFixedExpenseDescriptor descriptor = new EditFixedExpenseDescriptorBuilder(testExpense).build();
        EditFixedExpenseDescriptor descriptor2 = new EditFixedExpenseDescriptorBuilder(testExpense2).build();
        EditFixedExpenseCommand editTestExpenseCommand = new EditFixedExpenseCommand(INDEX_FIRST, descriptor, false);
        EditFixedExpenseCommand editTestExpense2Command = new EditFixedExpenseCommand(INDEX_SECOND, descriptor2, false);

        // same object -> returns true
        assertTrue(editTestExpenseCommand.equals(editTestExpenseCommand));

        assertNotEquals(descriptor, descriptor2);

        assertEquals(descriptor, descriptor);

        // different types -> returns false
        assertNotEquals(1, editTestExpenseCommand);

        // null -> returns false
        assertNotEquals(null, editTestExpenseCommand);

        // different person -> returns false
        assertNotEquals(editTestExpenseCommand, editTestExpense2Command);
    }


    /**
     * A Model stub has no trip set.
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
        public void setFixedExpense(FixedExpense target, FixedExpense edited) {
            requireAllNonNull(target, edited);
            fixedExpensesAdded.remove(target);
            fixedExpensesAdded.add(edited);
        }

        @Override
        public void updateFilteredFixedExpenseList(Predicate<FixedExpense> predicate) {
            requireNonNull(predicate);


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
