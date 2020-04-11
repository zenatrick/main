package team.easytravel.logic.commands.fixedexpense;

import static team.easytravel.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.model.ModelStub;

class SortFixedExpenseCommandTest {

    @Test
    public void execute_tripNotSet_throwsCommandException() {
        ModelStubNoTripSet modelStub = new ModelStubNoTripSet();
        assertThrows(CommandException.class, ()-> new SortFixedExpenseCommand("asc", "amount")
                .execute(modelStub));
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

}
