package team.easytravel.logic.parser.fixedexpense;

import static team.easytravel.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static team.easytravel.commons.core.Messages.MESSAGE_INVALID_DISPLAYED_INDEX_FORMAT;
import static team.easytravel.logic.commands.CommandTestUtil.FIXED_EXPENSE_AMOUNT_DESC_ACCOMMODATION;
import static team.easytravel.logic.commands.CommandTestUtil.FIXED_EXPENSE_AMOUNT_DESC_TEST;
import static team.easytravel.logic.commands.CommandTestUtil.FIXED_EXPENSE_CATEGORY_DESC_ACCOMMODATION;
import static team.easytravel.logic.commands.CommandTestUtil.FIXED_EXPENSE_CATEGORY_DESC_TEST;
import static team.easytravel.logic.commands.CommandTestUtil.FIXED_EXPENSE_CURRENCY_DESC_ACCOMMODATION;
import static team.easytravel.logic.commands.CommandTestUtil.FIXED_EXPENSE_CURRENCY_DESC_TEST;
import static team.easytravel.logic.commands.CommandTestUtil.FIXED_EXPENSE_DESCRIPTION_DESC_ACCOMMODATION;
import static team.easytravel.logic.commands.CommandTestUtil.FIXED_EXPENSE_DESCRIPTION_DESC_TEST;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_FIXED_EXPENSE_AMOUNT_DESC;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_FIXED_EXPENSE_CATEGORY_DESC;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_FIXED_EXPENSE_DESCRIPTION_DESC;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_FIXED_EXPENSE_AMOUNT_ACCOMMODATION;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_FIXED_EXPENSE_AMOUNT_TEST;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_FIXED_EXPENSE_CATEGORY_ACCOMMODATION;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_FIXED_EXPENSE_CATEGORY_TEST;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_FIXED_EXPENSE_DESCRIPTION_ACCOMMODATION;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_FIXED_EXPENSE_DESCRIPTION_TEST;
import static team.easytravel.logic.parser.CommandParserTestUtil.assertParseFailure;
import static team.easytravel.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static team.easytravel.testutil.TypicalIndexes.INDEX_FIRST;
import static team.easytravel.testutil.TypicalIndexes.INDEX_SECOND;
import static team.easytravel.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import team.easytravel.commons.core.index.Index;
import team.easytravel.logic.commands.fixedexpense.EditFixedExpenseCommand;
import team.easytravel.model.listmanagers.fixedexpense.Amount;
import team.easytravel.model.listmanagers.fixedexpense.Description;
import team.easytravel.model.listmanagers.fixedexpense.FixedExpenseCategory;
import team.easytravel.testutil.fixedexpense.EditFixedExpenseDescriptorBuilder;

class EditFixedExpenseCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditFixedExpenseCommand.MESSAGE_USAGE);

    private static final String MESSAGE_INVALID_INDEX = String.format(MESSAGE_INVALID_DISPLAYED_INDEX_FORMAT,
            "fixed expense");

    private EditFixedExpenseCommandParser parser = new EditFixedExpenseCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_FIXED_EXPENSE_DESCRIPTION_TEST, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditFixedExpenseCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_FIXED_EXPENSE_DESCRIPTION_TEST, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_FIXED_EXPENSE_DESCRIPTION_TEST, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_FIXED_EXPENSE_AMOUNT_DESC + FIXED_EXPENSE_CURRENCY_DESC_TEST,
                Amount.MESSAGE_CONSTRAINTS); // invalid accommodation name
        assertParseFailure(parser, "1" + INVALID_FIXED_EXPENSE_DESCRIPTION_DESC,
                Description.MESSAGE_CONSTRAINTS); // invalid location
        assertParseFailure(parser, "1" + INVALID_FIXED_EXPENSE_CATEGORY_DESC,
                FixedExpenseCategory.MESSAGE_CONSTRAINTS); // invalid start day

        // invalid location followed by valid start day
        assertParseFailure(parser, "1" + INVALID_FIXED_EXPENSE_DESCRIPTION_DESC
                        + FIXED_EXPENSE_CATEGORY_DESC_ACCOMMODATION,
                Description.MESSAGE_CONSTRAINTS);

        // valid location followed by invalid location. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + FIXED_EXPENSE_CATEGORY_DESC_TEST
                        + INVALID_FIXED_EXPENSE_CATEGORY_DESC,
                FixedExpenseCategory.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_FIXED_EXPENSE_AMOUNT_DESC + FIXED_EXPENSE_CURRENCY_DESC_TEST
                        + INVALID_FIXED_EXPENSE_DESCRIPTION_DESC + INVALID_FIXED_EXPENSE_CATEGORY_DESC,
                Amount.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + FIXED_EXPENSE_AMOUNT_DESC_ACCOMMODATION
                + FIXED_EXPENSE_DESCRIPTION_DESC_ACCOMMODATION + FIXED_EXPENSE_CURRENCY_DESC_ACCOMMODATION
                + FIXED_EXPENSE_CATEGORY_DESC_ACCOMMODATION;

        EditFixedExpenseCommand.EditFixedExpenseDescriptor descriptor =
                new EditFixedExpenseDescriptorBuilder().withAmount(VALID_FIXED_EXPENSE_AMOUNT_ACCOMMODATION)
                        .withDescription(VALID_FIXED_EXPENSE_DESCRIPTION_ACCOMMODATION)
                        .withFixedExpenseCategory(VALID_FIXED_EXPENSE_CATEGORY_ACCOMMODATION).build();

        EditFixedExpenseCommand expectedCommand = new EditFixedExpenseCommand(targetIndex, descriptor,
                false);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + FIXED_EXPENSE_DESCRIPTION_DESC_ACCOMMODATION
                + FIXED_EXPENSE_CATEGORY_DESC_ACCOMMODATION;


        EditFixedExpenseCommand.EditFixedExpenseDescriptor descriptor =
                new EditFixedExpenseDescriptorBuilder().withDescription(VALID_FIXED_EXPENSE_DESCRIPTION_ACCOMMODATION)
                        .withFixedExpenseCategory(VALID_FIXED_EXPENSE_CATEGORY_ACCOMMODATION).build();

        EditFixedExpenseCommand expectedCommand = new EditFixedExpenseCommand(targetIndex, descriptor,
                false);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // amount
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + FIXED_EXPENSE_AMOUNT_DESC_ACCOMMODATION
                + FIXED_EXPENSE_CURRENCY_DESC_ACCOMMODATION;
        EditFixedExpenseCommand.EditFixedExpenseDescriptor descriptor =
                new EditFixedExpenseDescriptorBuilder()
                        .withAmount(VALID_FIXED_EXPENSE_AMOUNT_ACCOMMODATION).build();
        EditFixedExpenseCommand expectedCommand = new EditFixedExpenseCommand(targetIndex, descriptor,
                false);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = targetIndex.getOneBased() + FIXED_EXPENSE_DESCRIPTION_DESC_ACCOMMODATION;
        descriptor = new EditFixedExpenseDescriptorBuilder()
                .withDescription(VALID_FIXED_EXPENSE_DESCRIPTION_ACCOMMODATION).build();
        expectedCommand = new EditFixedExpenseCommand(targetIndex, descriptor, false);
        assertParseSuccess(parser, userInput, expectedCommand);

        // category
        userInput = targetIndex.getOneBased() + FIXED_EXPENSE_CATEGORY_DESC_ACCOMMODATION;
        descriptor = new EditFixedExpenseDescriptorBuilder()
                .withFixedExpenseCategory(VALID_FIXED_EXPENSE_CATEGORY_ACCOMMODATION).build();
        expectedCommand = new EditFixedExpenseCommand(targetIndex, descriptor, false);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + FIXED_EXPENSE_AMOUNT_DESC_ACCOMMODATION
                + FIXED_EXPENSE_DESCRIPTION_DESC_ACCOMMODATION + FIXED_EXPENSE_CATEGORY_DESC_ACCOMMODATION
                + FIXED_EXPENSE_CURRENCY_DESC_ACCOMMODATION + FIXED_EXPENSE_CATEGORY_DESC_ACCOMMODATION
                + FIXED_EXPENSE_AMOUNT_DESC_TEST + FIXED_EXPENSE_CURRENCY_DESC_TEST + FIXED_EXPENSE_CATEGORY_DESC_TEST
                + FIXED_EXPENSE_DESCRIPTION_DESC_TEST;

        EditFixedExpenseCommand.EditFixedExpenseDescriptor descriptor =
                new EditFixedExpenseDescriptorBuilder().withAmount(VALID_FIXED_EXPENSE_AMOUNT_TEST)
                        .withDescription(VALID_FIXED_EXPENSE_DESCRIPTION_TEST)
                        .withFixedExpenseCategory(VALID_FIXED_EXPENSE_CATEGORY_TEST).build();
        EditFixedExpenseCommand expectedCommand = new EditFixedExpenseCommand(targetIndex, descriptor,
                false);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_FIXED_EXPENSE_DESCRIPTION_DESC
                + FIXED_EXPENSE_DESCRIPTION_DESC_TEST;
        EditFixedExpenseCommand.EditFixedExpenseDescriptor descriptor =
                new EditFixedExpenseDescriptorBuilder().withDescription(VALID_FIXED_EXPENSE_DESCRIPTION_TEST)
                        .build();
        EditFixedExpenseCommand expectedCommand = new EditFixedExpenseCommand(targetIndex, descriptor,
                false);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_FIXED_EXPENSE_DESCRIPTION_DESC
                + FIXED_EXPENSE_CATEGORY_DESC_TEST + FIXED_EXPENSE_DESCRIPTION_DESC_TEST;
        descriptor = new EditFixedExpenseDescriptorBuilder().withDescription(VALID_FIXED_EXPENSE_DESCRIPTION_TEST)
                .withFixedExpenseCategory(VALID_FIXED_EXPENSE_CATEGORY_TEST).build();
        expectedCommand = new EditFixedExpenseCommand(targetIndex, descriptor, false);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
