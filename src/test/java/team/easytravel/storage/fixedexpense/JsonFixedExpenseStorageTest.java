package team.easytravel.storage.fixedexpense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static team.easytravel.testutil.Assert.assertThrows;
import static team.easytravel.testutil.TypicalFixedExpense.FIXED_EXPENSE_FLY_KITE;
import static team.easytravel.testutil.TypicalFixedExpense.FIXED_EXPENSE_HOTELS;
import static team.easytravel.testutil.TypicalFixedExpense.FIXED_EXPENSE_STALL;
import static team.easytravel.testutil.TypicalFixedExpense.getTypicalFixedExpenseManager;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import team.easytravel.commons.exceptions.DataConversionException;
import team.easytravel.model.listmanagers.AccommodationBookingManager;
import team.easytravel.model.listmanagers.FixedExpenseManager;
import team.easytravel.model.listmanagers.ReadOnlyAccommodationBookingManager;
import team.easytravel.model.listmanagers.ReadOnlyFixedExpenseManager;
import team.easytravel.storage.accommodationbooking.JsonAccommodationBookingStorage;

class JsonFixedExpenseStorageTest {


    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonFixedExpenseStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readFixedExpenseManager_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readFixedExpenseManager(null));
    }

    private java.util.Optional<ReadOnlyFixedExpenseManager> readFixedExpenseManager(
            String filePath) throws Exception {
        return new JsonFixedExpenseStorage(Paths.get(filePath)).readFixedExpenses(
                addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readFixedExpenseManager("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readFixedExpenseManager(
                "notJsonFormatFixedExpenseManager.json"));
    }

    @Test
    public void readFixedExpenseManager_invalidFixedExpenseManager_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readFixedExpenseManager
                ("invalidFixedExpenseManager.json"));
    }

    @Test
    public void readFixedExpense_invalidAndValidFixedExpenseManager_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readFixedExpenseManager
                ("invalidAndValidFixedExpenseManager.json"));
    }

    @Test
    public void readAndSaveFixedExpenses_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempFixedExpense.json");
        FixedExpenseManager origin = getTypicalFixedExpenseManager();
        JsonFixedExpenseStorage jsonFixedExpenseStorage = new JsonFixedExpenseStorage(filePath);

        jsonFixedExpenseStorage.saveFixedExpenses(origin, filePath);
        ReadOnlyFixedExpenseManager readBack = jsonFixedExpenseStorage
                .readFixedExpenses(filePath).get();
        assertEquals(origin, new FixedExpenseManager(readBack));

        origin.addFixedExpense(FIXED_EXPENSE_FLY_KITE);
        origin.removeFixedExpense(FIXED_EXPENSE_HOTELS);
        jsonFixedExpenseStorage.saveFixedExpenses(origin, filePath);
        readBack = jsonFixedExpenseStorage.readFixedExpenses(filePath).get();
        assertEquals(origin, new FixedExpenseManager(readBack));

        origin.addFixedExpense(FIXED_EXPENSE_STALL);
        jsonFixedExpenseStorage.saveFixedExpenses(origin);
        readBack = jsonFixedExpenseStorage.readFixedExpenses().get();
        assertEquals(origin, new FixedExpenseManager(readBack));



    }

    @Test
    public void saveAccommodationBookingManager_nullAccommodationBookingManager_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAccommodationBookings(
                null, "SomeFile.json"));
    }

    /**
     * Saves {@code accommodationBookingManager} at the specified {@code filePath}.
     */
    private void saveAccommodationBookings(ReadOnlyAccommodationBookingManager addressBook, String filePath) {
        try {
            new JsonAccommodationBookingStorage(Paths.get(filePath))
                    .saveAccommodationBookings(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAccommodationBookingManager_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAccommodationBookings(new AccommodationBookingManager(),
                null));
    }
}
