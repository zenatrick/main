package seedu.address.logic.commands.packinglist;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.listmanagers.packinglistitem.PackingListItem;

/**
 * The type Add item command.
 */
public class AddItemCommand extends Command {
    /**
     * The constant COMMAND_WORD.
     */
    public static final String COMMAND_WORD = "additem";

    /**
     * The constant MESSAGE_USAGE.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an item to the packing list. "
            + "Parameters: "
            + PREFIX_ITEM + "NAME "
            + PREFIX_QUANTITY + "QUANTITY "
            + PREFIX_CATEGORY + "CATEGORY \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ITEM + "Tshirts "
            + PREFIX_QUANTITY + 5 + " "
            + PREFIX_CATEGORY + "basics";

    /**
     * The constant MESSAGE_SUCCESS.
     */
    public static final String MESSAGE_SUCCESS = "New item added: %1$s";
    /**
     * The constant MESSAGE_DUPLICATE_PERSON.
     */
    public static final String MESSAGE_DUPLICATE_ITEM = "This item already exists in the packing list";

    private final PackingListItem toAdd;

    /**
     * Creates an AddItemCommand to add the specified {@code Person}
     *
     * @param item the item
     */
    public AddItemCommand(PackingListItem item) {
        requireNonNull(item);
        toAdd = item;
    }

    /**
     * Execute command result.
     *
     * @param model the model
     * @return the command result
     * @throws CommandException the command exception
     */
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPackingListItem(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ITEM);
        }

        model.addPackingListItem(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddItemCommand // instanceof handles nulls
                && toAdd.equals(((AddItemCommand) other).toAdd));
    }
}
