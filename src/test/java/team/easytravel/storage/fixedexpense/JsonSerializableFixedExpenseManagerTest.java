package team.easytravel.storage.fixedexpense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static team.easytravel.testutil.Assert.assertThrows;
import static team.easytravel.testutil.TypicalFixedExpense.getTypicalFixedExpenseManager;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import team.easytravel.commons.exceptions.IllegalValueException;
import team.easytravel.commons.util.JsonUtil;
import team.easytravel.model.listmanagers.FixedExpenseManager;

class JsonSerializableFixedExpenseManagerTest {

    public static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableFixedExpenseManagerTest");

    public static final Path TYPICAL_FIXED_EXPENSE_BOOKING_FILE = TEST_DATA_FOLDER
            .resolve("typicalFixedExpenseManager.json");
    public static final Path INVALID_FIXED_EXPENSE_BOOKING_FILE = TEST_DATA_FOLDER
            .resolve("invalidFixedExpenseManager.json");
    public static final Path DUPLICATE_FIXED_EXPENSE_BOOKING_FILE = TEST_DATA_FOLDER
            .resolve("duplicateFixedExpenseManager.json");

    @Test
    public void toModelType_typicalFixedExpenseFile_success() throws Exception {
        JsonSerializableFixedExpenseManager dataFromFile = JsonUtil
                .readJsonFile(TYPICAL_FIXED_EXPENSE_BOOKING_FILE,
                        JsonSerializableFixedExpenseManager.class).get();
        FixedExpenseManager fixedExpenseManagerFromFile = dataFromFile.toModelType();
        FixedExpenseManager typicalFixedExpenseManager =
                getTypicalFixedExpenseManager();
        assertEquals(fixedExpenseManagerFromFile, typicalFixedExpenseManager);
    }

    @Test
    public void toModelType_invalidFixedExpenseFile_throwsIllegalValueException() throws Exception {
        JsonSerializableFixedExpenseManager dataFromFile = JsonUtil
                .readJsonFile(INVALID_FIXED_EXPENSE_BOOKING_FILE,
                        JsonSerializableFixedExpenseManager.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateFixedExpenses_throwsIllegalValueException() throws Exception {
        JsonSerializableFixedExpenseManager dataFromFile = JsonUtil
                .readJsonFile(DUPLICATE_FIXED_EXPENSE_BOOKING_FILE,
                        JsonSerializableFixedExpenseManager.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableFixedExpenseManager
                        .MESSAGE_DUPLICATE_FIXED_EXPENSE_BOOKING,
                dataFromFile::toModelType);
    }

}
