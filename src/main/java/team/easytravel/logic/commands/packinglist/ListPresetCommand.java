package team.easytravel.logic.commands.packinglist;

import static java.util.Objects.requireNonNull;

import team.easytravel.logic.commands.Command;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.model.Model;

public class ListPresetCommand extends Command {
    public static final String COMMAND_WORD = "listpreset";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists the available presets that the user can use. "
            + "Example: " + COMMAND_WORD + " ";

    public static final String MESSAGE_SUCCESS = "List Preset popup has been opened!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        return new CommandResult(String.format(MESSAGE_SUCCESS), null, false, false, false, false, false, false, true);
    }
}
