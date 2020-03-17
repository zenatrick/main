package seedu.address.model.util.uniquelist;

/**
 * API of an element of a UniqueList.
 * <p>
 * {@link UniqueList}
 */
public interface UniqueListElement {
    boolean isSame(UniqueListElement other);
}
