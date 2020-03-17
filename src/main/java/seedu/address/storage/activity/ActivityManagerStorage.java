package seedu.address.storage.activity;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.listmanager.ReadOnlyActivityManager;

/**
 * Represents a storage for {@link seedu.address.model.activity.UniqueActivityList}.
 */
public interface ActivityManagerStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getActivityManagerStorageFilePath();

    /**
     * Returns activity data as a {@link ReadOnlyActivityManager}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyActivityManager> readActivityManager() throws DataConversionException, IOException;

    /**
     * @see #getActivityManagerStorageFilePath()
     */
    Optional<ReadOnlyActivityManager> readActivityManager(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyActivityManager} to the storage.
     * @param activityManager cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveActivityManager(ReadOnlyActivityManager activityManager) throws IOException;

    /**
     * @see #saveActivityManager(ReadOnlyActivityManager)
     */
    void saveActivityManager(ReadOnlyActivityManager activityManager, Path filePath) throws IOException;

}
