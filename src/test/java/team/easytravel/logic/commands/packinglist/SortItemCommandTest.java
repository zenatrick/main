package team.easytravel.logic.commands.packinglist;

import static team.easytravel.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.logic.commands.util.SortCommandOrder;
import team.easytravel.model.ModelStub;

class SortItemCommandTest {

    @Test
    public void execute_tripNotSet_throwsCommandException() {
        SortItemCommandTest.ModelStubNoTripSet modelStub = new ModelStubNoTripSet();
        assertThrows(CommandException.class, ()-> new SortItemCommand(SortCommandOrder.ASCENDING,
                "name")
                .execute(modelStub));
    }

    /**
     * A Model stub that does not have a trip set.
     */
    private static class ModelStubNoTripSet extends ModelStub {
        @Override
        public boolean hasTrip() {
            return false;
        }
    }

}
