package team.easytravel.logic.parser.packinglist;

import static team.easytravel.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_ITEM_CATEGORY;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_ITEM_NAME;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_ITEM_QUANTITY;
import static team.easytravel.logic.commands.CommandTestUtil.ITEM_CATEGORY_PASSPORT;
import static team.easytravel.logic.commands.CommandTestUtil.ITEM_CATEGORY_UNDERWEAR;
import static team.easytravel.logic.commands.CommandTestUtil.ITEM_NAME_PASSPORT;
import static team.easytravel.logic.commands.CommandTestUtil.ITEM_NAME_UNDERWEAR;
import static team.easytravel.logic.commands.CommandTestUtil.ITEM_QUANTITY_PASSPORT;
import static team.easytravel.logic.commands.CommandTestUtil.ITEM_QUANTITY_UNDERWEAR;
import static team.easytravel.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static team.easytravel.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ITEM_CATEGORY_UNDERWEAR;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ITEM_NAME_UNDERWEAR;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_QUANTITY_UNDERWEAR;
import static team.easytravel.logic.parser.CommandParserTestUtil.assertParseFailure;
import static team.easytravel.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static team.easytravel.testutil.packinglist.TypicalPackingListItem.PACKING_LIST_UNDERWEAR;

import org.junit.jupiter.api.Test;

import team.easytravel.logic.commands.packinglist.AddItemCommand;
import team.easytravel.model.listmanagers.packinglistitem.ItemCategory;
import team.easytravel.model.listmanagers.packinglistitem.ItemName;
import team.easytravel.model.listmanagers.packinglistitem.PackingListItem;
import team.easytravel.model.listmanagers.packinglistitem.Quantity;
import team.easytravel.testutil.packinglist.PackingListItemBuilder;

public class AddItemCommandParserTest {
    private AddItemCommandParser parser = new AddItemCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        PackingListItem packingListItem = new PackingListItemBuilder(PACKING_LIST_UNDERWEAR).build();

        // trailing white space
        assertParseSuccess(parser, PREAMBLE_WHITESPACE
                + ITEM_NAME_UNDERWEAR
                + ITEM_QUANTITY_UNDERWEAR
                + ITEM_CATEGORY_UNDERWEAR, new AddItemCommand(packingListItem));

        // last name accepted
        assertParseSuccess(parser, ITEM_NAME_PASSPORT
                + ITEM_NAME_UNDERWEAR
                + ITEM_QUANTITY_UNDERWEAR
                + ITEM_CATEGORY_UNDERWEAR, new AddItemCommand(packingListItem));

        // last quantity accepted
        assertParseSuccess(parser, ITEM_NAME_UNDERWEAR
                + ITEM_QUANTITY_PASSPORT
                + ITEM_QUANTITY_UNDERWEAR
                + ITEM_CATEGORY_UNDERWEAR, new AddItemCommand(packingListItem));

        // last category accepted
        assertParseSuccess(parser, ITEM_NAME_UNDERWEAR
                + ITEM_QUANTITY_UNDERWEAR
                + ITEM_CATEGORY_PASSPORT
                + ITEM_CATEGORY_UNDERWEAR, new AddItemCommand(packingListItem));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddItemCommand.MESSAGE_USAGE);

        // missing name
        assertParseFailure(parser, ITEM_QUANTITY_UNDERWEAR
                + ITEM_CATEGORY_UNDERWEAR, expectedMessage);

        // missing quantity
        assertParseFailure(parser, ITEM_NAME_UNDERWEAR
                + ITEM_CATEGORY_UNDERWEAR, expectedMessage);

        // missing category
        assertParseFailure(parser, ITEM_NAME_UNDERWEAR
                + ITEM_QUANTITY_UNDERWEAR, expectedMessage);


        assertParseFailure(parser, VALID_ITEM_NAME_UNDERWEAR
                + VALID_QUANTITY_UNDERWEAR
                + VALID_ITEM_CATEGORY_UNDERWEAR, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {

        // invalid name
        assertParseFailure(parser, INVALID_ITEM_NAME
                + ITEM_QUANTITY_UNDERWEAR
                + ITEM_CATEGORY_UNDERWEAR, ItemName.MESSAGE_CONSTRAINTS);

        // invalid quantity
        assertParseFailure(parser, ITEM_NAME_UNDERWEAR
                + INVALID_ITEM_QUANTITY
                + ITEM_CATEGORY_UNDERWEAR, Quantity.MESSAGE_CONSTRAINTS);

        // invalid category
        assertParseFailure(parser, ITEM_NAME_UNDERWEAR
                + ITEM_QUANTITY_UNDERWEAR
                + INVALID_ITEM_CATEGORY, ItemCategory.MESSAGE_CONSTRAINTS);

        // two invalid fields, only the first one is reported
        assertParseFailure(parser, INVALID_ITEM_NAME
                + ITEM_QUANTITY_UNDERWEAR
                + INVALID_ITEM_CATEGORY, ItemName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY
                + ITEM_NAME_UNDERWEAR
                + ITEM_QUANTITY_UNDERWEAR
                + ITEM_CATEGORY_UNDERWEAR,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddItemCommand.MESSAGE_USAGE));
    }
}
