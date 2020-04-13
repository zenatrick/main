package team.easytravel.logic.parser.fixedexpense;

import static team.easytravel.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static team.easytravel.logic.commands.CommandTestUtil.FIXED_EXPENSE_AMOUNT_DESC_ACCOMMODATION;
import static team.easytravel.logic.commands.CommandTestUtil.FIXED_EXPENSE_AMOUNT_DESC_TEST;
import static team.easytravel.logic.commands.CommandTestUtil.FIXED_EXPENSE_CATEGORY_DESC_ACCOMMODATION;
import static team.easytravel.logic.commands.CommandTestUtil.FIXED_EXPENSE_CATEGORY_DESC_TEST;
import static team.easytravel.logic.commands.CommandTestUtil.FIXED_EXPENSE_CURRENCY_DESC_TEST;
import static team.easytravel.logic.commands.CommandTestUtil.FIXED_EXPENSE_DESCRIPTION_DESC_ACCOMMODATION;
import static team.easytravel.logic.commands.CommandTestUtil.FIXED_EXPENSE_DESCRIPTION_DESC_TEST;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_FIXED_EXPENSE_AMOUNT_DESC;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_FIXED_EXPENSE_CATEGORY_DESC;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_FIXED_EXPENSE_DESCRIPTION_DESC;
import static team.easytravel.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static team.easytravel.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_FIXED_EXPENSE_AMOUNT_TEST;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_FIXED_EXPENSE_CATEGORY_TEST;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_FIXED_EXPENSE_CURRENCY_TEST;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_FIXED_EXPENSE_DESCRIPTION_TEST;
import static team.easytravel.logic.parser.CommandParserTestUtil.assertParseFailure;
import static team.easytravel.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import team.easytravel.logic.commands.fixedexpense.AddFixedExpenseCommand;
import team.easytravel.model.listmanagers.fixedexpense.Amount;
import team.easytravel.model.listmanagers.fixedexpense.Description;
import team.easytravel.model.listmanagers.fixedexpense.FixedExpense;
import team.easytravel.model.listmanagers.fixedexpense.FixedExpenseCategory;
import team.easytravel.testutil.fixedexpense.FixedExpenseBuilder;

class AddFixedExpenseCommandParserTest {

    private AddFixedExpenseCommandParser parser = new AddFixedExpenseCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        FixedExpense expectedFixedExpense =
                new FixedExpenseBuilder().build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + FIXED_EXPENSE_AMOUNT_DESC_TEST
                        + FIXED_EXPENSE_CURRENCY_DESC_TEST + FIXED_EXPENSE_DESCRIPTION_DESC_TEST
                        + FIXED_EXPENSE_CATEGORY_DESC_TEST,
                new AddFixedExpenseCommand(expectedFixedExpense, false));

        // multiple expense names - last expense name accepted
        assertParseSuccess(parser, FIXED_EXPENSE_AMOUNT_DESC_ACCOMMODATION + FIXED_EXPENSE_AMOUNT_DESC_TEST
                        + FIXED_EXPENSE_CURRENCY_DESC_TEST + FIXED_EXPENSE_DESCRIPTION_DESC_TEST
                        + FIXED_EXPENSE_CATEGORY_DESC_TEST,
                new AddFixedExpenseCommand(expectedFixedExpense, false));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, FIXED_EXPENSE_AMOUNT_DESC_TEST + FIXED_EXPENSE_CURRENCY_DESC_TEST
                        + FIXED_EXPENSE_DESCRIPTION_DESC_ACCOMMODATION
                        + FIXED_EXPENSE_DESCRIPTION_DESC_TEST + FIXED_EXPENSE_CATEGORY_DESC_TEST,
                new AddFixedExpenseCommand(expectedFixedExpense, false));

        // multiple categories - last category accepted
        assertParseSuccess(parser, FIXED_EXPENSE_AMOUNT_DESC_TEST + FIXED_EXPENSE_CURRENCY_DESC_TEST
                        + FIXED_EXPENSE_DESCRIPTION_DESC_TEST
                        + FIXED_EXPENSE_CATEGORY_DESC_ACCOMMODATION + FIXED_EXPENSE_CATEGORY_DESC_TEST,
                new AddFixedExpenseCommand(expectedFixedExpense, false));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddFixedExpenseCommand.MESSAGE_USAGE);

        // missing amount prefix
        assertParseFailure(parser, VALID_FIXED_EXPENSE_AMOUNT_TEST + FIXED_EXPENSE_CURRENCY_DESC_TEST
                + FIXED_EXPENSE_DESCRIPTION_DESC_TEST
                + FIXED_EXPENSE_CATEGORY_DESC_TEST, expectedMessage);

        // missing currency prefix
        assertParseFailure(parser, FIXED_EXPENSE_AMOUNT_DESC_TEST + VALID_FIXED_EXPENSE_CURRENCY_TEST
                + FIXED_EXPENSE_DESCRIPTION_DESC_TEST + FIXED_EXPENSE_CATEGORY_DESC_TEST, expectedMessage);

        // missing description prefix
        assertParseFailure(parser, FIXED_EXPENSE_AMOUNT_DESC_TEST + FIXED_EXPENSE_CURRENCY_DESC_TEST
                + VALID_FIXED_EXPENSE_DESCRIPTION_TEST + FIXED_EXPENSE_CATEGORY_DESC_TEST, expectedMessage);

        // missing category prefix
        assertParseFailure(parser, FIXED_EXPENSE_AMOUNT_DESC_TEST + FIXED_EXPENSE_CURRENCY_DESC_TEST
                + FIXED_EXPENSE_DESCRIPTION_DESC_TEST
                + VALID_FIXED_EXPENSE_CATEGORY_TEST, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_FIXED_EXPENSE_AMOUNT_TEST + VALID_FIXED_EXPENSE_CURRENCY_TEST
                + VALID_FIXED_EXPENSE_DESCRIPTION_TEST + VALID_FIXED_EXPENSE_CATEGORY_TEST, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid amount
        assertParseFailure(parser, INVALID_FIXED_EXPENSE_AMOUNT_DESC + FIXED_EXPENSE_CURRENCY_DESC_TEST
                + FIXED_EXPENSE_DESCRIPTION_DESC_TEST + FIXED_EXPENSE_CATEGORY_DESC_TEST, Amount.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, FIXED_EXPENSE_AMOUNT_DESC_TEST + FIXED_EXPENSE_CURRENCY_DESC_TEST
                        + INVALID_FIXED_EXPENSE_DESCRIPTION_DESC + FIXED_EXPENSE_CATEGORY_DESC_TEST,
                Description.MESSAGE_CONSTRAINTS);

        // invalid category
        assertParseFailure(parser, FIXED_EXPENSE_AMOUNT_DESC_TEST + FIXED_EXPENSE_CURRENCY_DESC_TEST
                        + FIXED_EXPENSE_DESCRIPTION_DESC_TEST + INVALID_FIXED_EXPENSE_CATEGORY_DESC,
                FixedExpenseCategory.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_FIXED_EXPENSE_AMOUNT_DESC + FIXED_EXPENSE_CURRENCY_DESC_TEST
                        + INVALID_FIXED_EXPENSE_DESCRIPTION_DESC + FIXED_EXPENSE_CATEGORY_DESC_TEST,
                Amount.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + FIXED_EXPENSE_AMOUNT_DESC_TEST
                        + FIXED_EXPENSE_CURRENCY_DESC_TEST
                        + FIXED_EXPENSE_DESCRIPTION_DESC_TEST + FIXED_EXPENSE_CATEGORY_DESC_TEST,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddFixedExpenseCommand.MESSAGE_USAGE));
    }

}
