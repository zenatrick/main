package seedu.address.model.packinglistitem.exceptions;

/**
 * The type Duplicate item exception.
 */
public class DuplicateItemException extends RuntimeException {
    /**
     * Instantiates a new Duplicate item exception.
     */
    public DuplicateItemException() {
        super("Operation would result in duplicate items");
    }
}
