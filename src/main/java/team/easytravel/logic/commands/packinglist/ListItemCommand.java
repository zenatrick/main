package team.easytravel.logic.commands.packinglist;

import static java.util.Objects.requireNonNull;
import static team.easytravel.model.Model.PREDICATE_SHOW_ALL_PACKING_LIST_ITEMS;

import team.easytravel.logic.commands.Command;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.model.Model;

/**
 * Lists all packing list items to the user.
 */
public class ListItemCommand extends Command {

    public static final String COMMAND_WORD = "listitem";

    public static final String MESSAGE_SUCCESS = "Listed all packing list items";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPackingList(PREDICATE_SHOW_ALL_PACKING_LIST_ITEMS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
