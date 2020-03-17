package seedu.address.model.listmanagers;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.listmanagers.packinglistitem.PackingListItem;
import seedu.address.model.util.uniquelist.UniqueList;

/**
 * The type Packing list manager.
 *
 * @author loycatherine
 */
public class PackingListManager implements ReadOnlyPackingListManager {
    private final UniqueList<PackingListItem> packingList;

    /**
     * Instantiates a new PackingListManager.
     */
    public PackingListManager() {
        packingList = new UniqueList<>();
    }

    /**
     * Creates an PackingListManager using the Items in the {@code toBeCopied}
     *
     * @param toBeCopied the to be copied
     */
    public PackingListManager(ReadOnlyPackingListManager toBeCopied) {
        packingList = new UniqueList<>();
        resetData(toBeCopied);
    }

    // List overwrite operations

    /**
     * Replaces the contents of the packing list with {@code packingListItems}.
     * {@code packingListItems} must not contain duplicate persons.
     */
    public void setPackingListItems(List<PackingListItem> packingListItems) {
        this.packingList.setElements(packingListItems);
    }

    /**
     * Resets the existing data of this {@code PackingManager} with {@code newData}.
     */
    public void resetData(ReadOnlyPackingListManager newData) {
        requireNonNull(newData);
        setPackingListItems(newData.getPackingList());
    }

    // PackingListItem-level operations

    /**
     * Returns true if a contacts with the same identity as {@code contacts} exists in the address book.
     */
    public boolean hasPackingListItem(PackingListItem packingListItem) {
        requireNonNull(packingListItem);
        return packingList.contains(packingListItem);
    }

    /**
     * Adds a packing list item to the PackingListManager.
     * The packing list item must not already exist in the PackingListManager.
     */
    public void addPackingListItem(PackingListItem packingListItem) {
        packingList.add(packingListItem);
    }

    /**
     * Replaces the given packing list item {@code target} in the list with {@code editedPackingListItem}.
     * {@code target} must exist in the PackingListManager.
     * The packing list item identity of {@code editedPackingListItem} must not be the same as another existing
     * packing list item in the PackingListManager.
     */
    public void setPackingListItem(PackingListItem target, PackingListItem editedPackingListItem) {
        requireNonNull(editedPackingListItem);

        packingList.setElement(target, editedPackingListItem);
    }

    /**
     * Removes {@code key} from this {@code PackingListManager}.
     * {@code key} must exist in the PackingListManager.
     */
    public void removePackingListItem(PackingListItem key) {
        packingList.remove(key);
    }

    // Util methods

    @Override
    public String toString() {
        return packingList.asUnmodifiableObservableList().size() + " packing list items";
    }

    @Override
    public ObservableList<PackingListItem> getPackingList() {
        return packingList.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PackingListManager // instanceof handles nulls
                && packingList.equals(((PackingListManager) other).packingList));
    }

    @Override
    public int hashCode() {
        return packingList.hashCode();
    }
}
