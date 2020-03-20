package seedu.address.storage.packinglist;

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
import seedu.address.model.listmanagers.ReadOnlyPackingListManager;

/**
 * The type Json packing list storage.
 */
public class JsonPackingListStorage implements PackingListStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonPackingListStorage.class);

    private Path filePath;

    /**
     * Instantiates a new Json packing list storage.
     *
     * @param filePath the file path
     */
    public JsonPackingListStorage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Gets packing list storage file path.
     *
     * @return the packing list storage file path
     */
    public Path getPackingListStorageFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyPackingListManager> readPackingList() throws DataConversionException {
        return readPackingList(filePath);
    }

    @Override
    public Optional<ReadOnlyPackingListManager> readPackingList(
            Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializablePackingListManager> jsonPackingListManager = JsonUtil.readJsonFile(
                filePath, JsonSerializablePackingListManager.class);
        if (jsonPackingListManager.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonPackingListManager.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void savePackingList(ReadOnlyPackingListManager packingListManager) throws IOException {
        savePackingList(packingListManager, filePath);
    }

    @Override
    public void savePackingList(
            ReadOnlyPackingListManager packingListManager, Path filePath) throws IOException {
        requireNonNull(packingListManager);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePackingListManager(packingListManager), filePath);
    }
}
