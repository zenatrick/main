package team.easytravel.logic.parser.packinglist;

import static team.easytravel.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static team.easytravel.logic.commands.CommandTestUtil.ITEM_CATEGORY_PASSPORT;
import static team.easytravel.logic.commands.CommandTestUtil.ITEM_CATEGORY_UNDERWEAR;
import static team.easytravel.logic.commands.CommandTestUtil.ITEM_NAME_UNDERWEAR;
import static team.easytravel.logic.commands.CommandTestUtil.ITEM_QUANTITY_UNDERWEAR;
import static team.easytravel.logic.commands.CommandTestUtil.ITEM_QUANTITY_PASSPORT;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_ITEM_CATEGORY;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_ITEM_NAME;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_ITEM_NAME_UNDERWEAR;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_ITEM_QUANTITY;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_QUANTITY_UNDERWEAR;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ITEM_CATEGORY_PASSPORT;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ITEM_CATEGORY_UNDERWEAR;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ITEM_NAME_PASSPORT;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ITEM_NAME_UNDERWEAR;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_QUANTITY_PASSPORT;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_QUANTITY_UNDERWEAR;
import static team.easytravel.logic.parser.CommandParserTestUtil.assertParseFailure;
import static team.easytravel.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static team.easytravel.testutil.TypicalIndexes.INDEX_FIRST;
import static team.easytravel.testutil.TypicalIndexes.INDEX_SECOND;
import static team.easytravel.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import team.easytravel.commons.core.index.Index;
import team.easytravel.logic.commands.packinglist.EditItemCommand;
import team.easytravel.logic.commands.packinglist.EditItemCommand.EditItemDescriptor;
import team.easytravel.model.listmanagers.packinglistitem.ItemCategory;
import team.easytravel.model.listmanagers.packinglistitem.ItemName;
import team.easytravel.model.listmanagers.packinglistitem.Quantity;
import team.easytravel.testutil.packinglist.EditItemDescriptorBuilder;

public class EditItemCommandParserTest {
    private EditItemCommandParser parser = new EditItemCommandParser();

    @Test
    public void parse_missingParts_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditItemCommand.MESSAGE_USAGE);

        // no index specified
        assertParseFailure(parser, VALID_ITEM_NAME_UNDERWEAR, expectedMessage);

        // no field specified
        assertParseFailure(parser, "1", EditItemCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", expectedMessage);
    }

    @Test
    public void parse_invalidPreamble_failure() {

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditItemCommand.MESSAGE_USAGE);

        // negative index
        assertParseFailure(parser, "-5" + ITEM_NAME_UNDERWEAR, expectedMessage);

        // zero index
        assertParseFailure(parser, "0" + ITEM_NAME_UNDERWEAR, expectedMessage);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", expectedMessage);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1"
                + INVALID_ITEM_NAME, ItemName.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1"
                + INVALID_ITEM_QUANTITY, Quantity.MESSAGE_CONSTRAINTS); // invalid quantity
        assertParseFailure(parser, "1"
                + INVALID_ITEM_CATEGORY, ItemCategory.MESSAGE_CONSTRAINTS); // invalid category

        // invalid name followed by quantity
        assertParseFailure(parser, "1" + INVALID_ITEM_NAME
                        + ITEM_QUANTITY_UNDERWEAR,
                ItemName.MESSAGE_CONSTRAINTS);

        // valid duration followed by invalid duration. The test case for invalid duration followed by valid duration
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + ITEM_QUANTITY_UNDERWEAR
                        + INVALID_ITEM_QUANTITY,
                Quantity.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_ITEM_NAME_UNDERWEAR
                        + INVALID_QUANTITY_UNDERWEAR
                        + VALID_ITEM_CATEGORY_UNDERWEAR,
                ItemName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + ITEM_NAME_UNDERWEAR
                + ITEM_QUANTITY_UNDERWEAR
                + ITEM_CATEGORY_UNDERWEAR;

        EditItemDescriptor descriptor = new EditItemDescriptorBuilder()
                .withItemName(VALID_ITEM_NAME_UNDERWEAR)
                .withQuantity(VALID_QUANTITY_UNDERWEAR)
                .withItemCategory(VALID_ITEM_CATEGORY_UNDERWEAR).build();
        EditItemCommand expectedCommand = new EditItemCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + ITEM_NAME_UNDERWEAR;
        EditItemDescriptor descriptor = new EditItemDescriptorBuilder()
                .withItemName(VALID_ITEM_NAME_UNDERWEAR)
                .build();
        EditItemCommand expectedCommand = new EditItemCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // quantity
        userInput = targetIndex.getOneBased() + ITEM_QUANTITY_UNDERWEAR;
        descriptor = new EditItemDescriptorBuilder()
                .withQuantity(VALID_QUANTITY_UNDERWEAR)
                .build();
        expectedCommand = new EditItemCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // category
        userInput = targetIndex.getOneBased() + ITEM_CATEGORY_UNDERWEAR;
        descriptor = new EditItemDescriptorBuilder()
                .withItemCategory(VALID_ITEM_CATEGORY_UNDERWEAR)
                .build();
        expectedCommand = new EditItemCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased()
                + ITEM_QUANTITY_UNDERWEAR + ITEM_CATEGORY_UNDERWEAR
                + ITEM_QUANTITY_UNDERWEAR + ITEM_CATEGORY_UNDERWEAR
                + ITEM_QUANTITY_PASSPORT + ITEM_CATEGORY_PASSPORT;

        EditItemDescriptor descriptor = new EditItemDescriptorBuilder()
                .withItemName(VALID_ITEM_NAME_PASSPORT)
                .withQuantity(VALID_QUANTITY_PASSPORT)
                .withItemCategory(VALID_ITEM_CATEGORY_PASSPORT)
                .build();
        EditItemCommand expectedCommand = new EditItemCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_ITEM_QUANTITY + ITEM_QUANTITY_UNDERWEAR;
        EditItemCommand.EditItemDescriptor descriptor = new EditItemDescriptorBuilder()
                .withQuantity(VALID_QUANTITY_UNDERWEAR).build();
        EditItemCommand expectedCommand = new EditItemCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + ITEM_CATEGORY_UNDERWEAR + INVALID_ITEM_QUANTITY
                + ITEM_QUANTITY_UNDERWEAR;
        descriptor = new EditItemDescriptorBuilder()
                .withQuantity(VALID_QUANTITY_UNDERWEAR)
                .withItemCategory(VALID_ITEM_CATEGORY_UNDERWEAR).build();
        expectedCommand = new EditItemCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
