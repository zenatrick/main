package seedu.address.storage.activity;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.listmanagers.ReadOnlyActivityManager;

/**
 * A class to access ActivityManager data stored as a json file on the hard disk.
 */
public class JsonActivityManagerStorage implements ActivityManagerStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonActivityManagerStorage.class);

    private Path filePath;

    public JsonActivityManagerStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getActivityManagerStorageFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyActivityManager> readActivityManager() throws DataConversionException {
        return readActivityManager(filePath);
    }

    /**
     * Similar to {@link #readActivityManager()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyActivityManager> readActivityManager(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableActivityManager> jsonActivityManager = JsonUtil.readJsonFile(
                filePath, JsonSerializableActivityManager.class);
        if (jsonActivityManager.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonActivityManager.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveActivityManager(ReadOnlyActivityManager activityManager) throws IOException {
        saveActivityManager(activityManager, filePath);
    }

    /**
     * Similar to {@link #saveActivityManager(ReadOnlyActivityManager)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveActivityManager(ReadOnlyActivityManager activityManager, Path filePath) throws IOException {
        requireNonNull(activityManager);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableActivityManager(activityManager), filePath);
    }

}
