//package team.easytravel.logic.commands.packinglisttest;
//
//import team.easytravel.logic.commands.packinglist.DeleteItemCommand;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import static team.easytravel.testutil.TypicalIndexes.INDEX_FIRST;
//import static team.easytravel.testutil.TypicalIndexes.INDEX_SECOND;
//
//import team.easytravel.model.Model;
//import team.easytravel.model.ModelManager;
//import team.easytravel.model.listmanagers.AccommodationBookingManager;
//import team.easytravel.model.listmanagers.ActivityManager;
//import team.easytravel.model.listmanagers.FixedExpenseManager;
//import team.easytravel.model.listmanagers.PackingListManager;
//import team.easytravel.model.listmanagers.TransportBookingManager;
//import team.easytravel.model.listmanagers.UserPrefs;
//import team.easytravel.model.listmanagers.packinglistitem.PackingListItem;
//import team.easytravel.model.trip.TripManager;
//
//import team.easytravel.logic.commands.Command;
//import team.easytravel.logic.commands.CommandResult;
//
//import org.junit.jupiter.api.Test;
//
//public class DeleteItemCommandTest {
//    private Model model = new ModelManager(new TransportBookingManager(), new FixedExpenseManager(),
//        new PackingListManager(), new ActivityManager(), new AccommodationBookingManager(),
//        new TripManager(), new UserPrefs());
//
//    public enum Action {
//        NONE,
//        HELP,
//        TRIP_SET,
//        TRIP_DELETE,
//        TRIP_EDIT,
//        STATUS,
//        EXIT,
//        PRESET,
//        SWITCH_TAB_SCHEDULE,
//        SWITCH_TAB_ACTIVITY,
//        SWITCH_TAB_ACCOMMODATION,
//        SWITCH_TAB_TRANSPORT,
//        SWITCH_TAB_PACKING_LIST,
//        SWITCH_TAB_FIXED_EXPENSE
//    }
//
//    public static Action action;
//
//    @Test
//    public void execute_validIndexUnfilteredList_success() {
//        PackingListItem itemToDelete = model.getFilteredPackingList().get(INDEX_FIRST.getZeroBased());
//        DeleteItemCommand deleteCommand = new DeleteItemCommand(INDEX_FIRST);
//
//        String expectedMessage = String.format(DeleteItemCommand.MESSAGE_DELETE_ITEM_SUCCESS,
//                itemToDelete);
//
//        List<String>
//
//        ModelManager expectedModel = new ModelManager(model.getTransportBookingManager(),
//                model.getFixedExpenseManager(), model.getPackingListManager(), model.getActivityManager(),
//                model.getAccommodationBookingManager(), model.getTripManager(), new UserPrefs());
//
//        expectedModel.deletePackingListItem(itemToDelete);
//
//        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
//    }
//
//    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
//                                            Model expectedModel) {
//        List<String> status = new ArrayList<>();
//        CommandResult expectedCommandResult = new CommandResult(expectedMessage, status, action);
//        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
//    }
//}
