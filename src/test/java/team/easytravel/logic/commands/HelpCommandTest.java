package team.easytravel.logic.commands;

import static team.easytravel.logic.commands.CommandResult.Action.HELP;
import static team.easytravel.logic.commands.CommandTestUtil.assertCommandSuccess;
import static team.easytravel.logic.commands.general.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import team.easytravel.logic.commands.general.HelpCommand;
import team.easytravel.model.Model;
import team.easytravel.model.ModelManager;
import team.easytravel.model.listmanagers.AccommodationBookingManager;
import team.easytravel.model.listmanagers.ActivityManager;
import team.easytravel.model.listmanagers.FixedExpenseManager;
import team.easytravel.model.listmanagers.PackingListManager;
import team.easytravel.model.listmanagers.TransportBookingManager;
import team.easytravel.model.trip.TripManager;
import team.easytravel.model.userprefs.UserPrefs;

public class HelpCommandTest {
    private Model model = new ModelManager(new TransportBookingManager(), new FixedExpenseManager(),
            new PackingListManager(), new ActivityManager(), new AccommodationBookingManager(),
            new TripManager(), new UserPrefs());
    private Model expectedModel = new ModelManager(new TransportBookingManager(), new FixedExpenseManager(),
            new PackingListManager(), new ActivityManager(), new AccommodationBookingManager(), new TripManager(),
            new UserPrefs());

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, HELP);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
