package seedu.address.storage.packinglist;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.listmanagers.ReadOnlyPackingListManager;

public interface PackingListStorage {

    Optional<ReadOnlyPackingListManager> readPackingList() throws DataConversionException;

    Optional<ReadOnlyPackingListManager> readPackingList(
            Path filePath) throws DataConversionException;

    void saveItems(ReadOnlyPackingListManager packingListManager) throws IOException;

    void saveItems(ReadOnlyPackingListManager packingListManager, Path filePath) throws IOException;
}
