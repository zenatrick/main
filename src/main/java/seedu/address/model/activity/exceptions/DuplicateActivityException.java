package seedu.address.model.activity.exceptions;

public class DuplicateActivityException extends RuntimeException {
    public DuplicateActivityException() {
        super("Operation would result in duplicate Activity");
    }
}
