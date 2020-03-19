package seedu.address.logic.parser.packinglist;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEMCATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.util.stream.Stream;

import seedu.address.logic.commands.packinglist.AddItemCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.listmanagers.packinglistitem.ItemCategory;
import seedu.address.model.listmanagers.packinglistitem.ItemName;
import seedu.address.model.listmanagers.packinglistitem.PackingListItem;
import seedu.address.model.listmanagers.packinglistitem.Quantity;

/**
 * The type Add item parser.
 */
public class AddItemParser implements Parser<AddItemCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddItemCommand
     * and returns an AddItemCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddItemCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ITEM, PREFIX_QUANTITY, PREFIX_ITEMCATEGORY);

        if (!arePrefixesPresent(argMultimap, PREFIX_ITEM, PREFIX_QUANTITY, PREFIX_ITEMCATEGORY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddItemCommand.MESSAGE_USAGE));
        }

        ItemName name = ParserUtil.parseItemName(argMultimap.getValue(PREFIX_ITEM).get());
        Quantity quantity = ParserUtil.parseQuantity(argMultimap.getValue(PREFIX_QUANTITY).get());
        ItemCategory category = ParserUtil.parseItemCategory(argMultimap.getValue(PREFIX_ITEMCATEGORY).get());

        PackingListItem packingListItem = new PackingListItem(name, quantity, category, false);

        return new AddItemCommand(packingListItem);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
