package seedu.address.storage.trip;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.trip.ReadOnlyTripManager;

/**
 * A class to access TripManager data stored as a json file on the hard disk.
 */
public class JsonTripStorage implements TripStorage {
    @Override
    public Path getTripStorageFilePath() {
        return null;
    }

    @Override
    public Optional<ReadOnlyTripManager> readTrip() throws DataConversionException, IOException {
        return Optional.empty();
    }

    @Override
    public Optional<ReadOnlyTripManager> readTripManager(Path filePath) throws DataConversionException, IOException {
        return Optional.empty();
    }

    @Override
    public void saveTrip(ReadOnlyTripManager tripManager) throws IOException {

    }

    @Override
    public void saveTrip(ReadOnlyTripManager tripManager, Path filePath) throws IOException {

    }
}
