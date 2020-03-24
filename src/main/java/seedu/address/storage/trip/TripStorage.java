package seedu.address.storage.trip;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.trip.ReadOnlyTripManager;
import seedu.address.model.trip.TripManager;

/**
 * Represents a storage for {@link TripManager}.
 */
public interface TripStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTripStorageFilePath();

    /**
     * Returns TripManager data as a {@link ReadOnlyTripManager}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTripManager> readTrip() throws DataConversionException, IOException;

    /**
     * @see #getTripStorageFilePath()
     */
    Optional<ReadOnlyTripManager> readTripManager(Path filePath) throws DataConversionException,
            IOException;

    /**
     * Saves the given {@link ReadOnlyTripManager} to the storage.
     *
     * @param tripManager cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTrip(ReadOnlyTripManager tripManager) throws IOException;

    /**
     * @see #saveTrip(ReadOnlyTripManager)
     */
    void saveTrip(
            ReadOnlyTripManager tripManager, Path filePath) throws IOException;

}
