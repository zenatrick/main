package team.easytravel.storage.trip;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static team.easytravel.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import team.easytravel.commons.exceptions.DataConversionException;
import team.easytravel.model.trip.TripManager;

class JsonTripManagerStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test",
            "data", "JsonTripStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTrip_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTripManager(null));
    }

    private Optional<TripManager> readTripManager(String filePath) throws Exception {
        return new JsonTripManagerStorage(Paths.get(filePath))
                .readTripManager(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTripManager("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTripManager(
                "notJsonFormatTripManager.json"));
    }

    @Test
    public void readTransportBookingManager_invalidTripManager_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTripManager(
                "invalidTripManager.json"));
    }

    @Test
    public void readTransportBookingManager_invalidAndValidTripManager_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTripManager(
                "invalidAndValidTripManager.json"));
    }

    @Test
    public void saveTripManager_nullTripManager_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTripManager(
                null, "SomeFile.json"));
    }

    /**
     * Saves {@code tripManager} at specified {@code filePath}.
     */
    private void saveTripManager(TripManager tripManager, String filePath) {
        try {
            new JsonTripManagerStorage(Paths.get(filePath))
                    .saveTripManager(tripManager, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file ", ioe);
        }
    }

    @Test
    public void saveTripManager_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTripManager(
                new TripManager(), null));
    }
}
