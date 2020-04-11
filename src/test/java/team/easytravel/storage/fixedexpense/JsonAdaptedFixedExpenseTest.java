package team.easytravel.storage.fixedexpense;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static team.easytravel.storage.fixedexpense.JsonAdaptedFixedExpense.MISSING_FIELD_MESSAGE_FORMAT;
import static team.easytravel.testutil.Assert.assertThrows;
import static team.easytravel.testutil.TypicalFixedExpense.FIXED_EXPENSE_HOTELS;

import org.junit.jupiter.api.Test;

import team.easytravel.commons.exceptions.IllegalValueException;
import team.easytravel.model.listmanagers.fixedexpense.Amount;
import team.easytravel.model.listmanagers.fixedexpense.Description;
import team.easytravel.model.listmanagers.fixedexpense.FixedExpenseCategory;

class JsonAdaptedFixedExpenseTest {

    public static final String INVALID_AMOUNT = "-1000";
    public static final String INVALID_DESCRIPTION = "IUH!IUH#J@";
    public static final String INVALID_FIXED_EXPENSE_CATEGORY = "Testing123";

    public static final String VALID_AMOUNT = FIXED_EXPENSE_HOTELS.getAmount().value;
    public static final String VALID_DESCRIPTION = FIXED_EXPENSE_HOTELS.getDescription().value;
    public static final String VALID_FIXED_EXPENSE_CATEGORY = FIXED_EXPENSE_HOTELS.getFixedExpenseCategory().value;

    @Test
    public void toModelType_validFixedExpenseDetails_returnsFixedExpense() throws Exception {
        JsonAdaptedFixedExpense fixedExpense = new JsonAdaptedFixedExpense(
               FIXED_EXPENSE_HOTELS);
        assertEquals(FIXED_EXPENSE_HOTELS, fixedExpense.toModelType());
    }

    @Test
    public void toModelType_invalidFixedExpenseAmount_throwsIllegalValueException() {
        JsonAdaptedFixedExpense fixedExpense =
                new JsonAdaptedFixedExpense(INVALID_AMOUNT, VALID_DESCRIPTION, VALID_FIXED_EXPENSE_CATEGORY);
        String expectedMessage = Amount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, fixedExpense::toModelType);
    }

    @Test
    public void toModelType_null_throwsIllegalValueException() {
        JsonAdaptedFixedExpense fixedExpense =
                new JsonAdaptedFixedExpense(null, VALID_DESCRIPTION, VALID_FIXED_EXPENSE_CATEGORY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Amount.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, fixedExpense::toModelType);
    }

    @Test
    public void toModelType_invalidFixedExpenseDescription_throwsIllegalValueException() {
        JsonAdaptedFixedExpense fixedExpense =
                new JsonAdaptedFixedExpense(VALID_AMOUNT, INVALID_DESCRIPTION, VALID_FIXED_EXPENSE_CATEGORY);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, fixedExpense::toModelType);
    }

    @Test
    public void toModelType_nullFixedExpenseDescription_throwsIllegalValueException() {
        JsonAdaptedFixedExpense fixedExpense =
                new JsonAdaptedFixedExpense(VALID_AMOUNT, null, VALID_FIXED_EXPENSE_CATEGORY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, fixedExpense::toModelType);
    }

    @Test
    public void toModelType_invalidFixedExpenseCategory_throwsIllegalValueException() {
        JsonAdaptedFixedExpense fixedExpense =
                new JsonAdaptedFixedExpense(VALID_AMOUNT, VALID_DESCRIPTION, INVALID_FIXED_EXPENSE_CATEGORY);
        String expectedMessage = FixedExpenseCategory.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, fixedExpense::toModelType);
    }

    @Test
    public void toModelType_nullFixedExpenseCategory_throwsIllegalValueException() {
        JsonAdaptedFixedExpense fixedExpense =
                new JsonAdaptedFixedExpense(VALID_AMOUNT, VALID_DESCRIPTION, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, FixedExpenseCategory
                .class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, fixedExpense::toModelType);
    }
}
