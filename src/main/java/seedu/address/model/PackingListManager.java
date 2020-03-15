package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.packinglistitem.Name;
import seedu.address.model.packinglistitem.PackingListItem;
import seedu.address.model.packinglistitem.Quantity;
import seedu.address.model.packinglistitem.UniquePackingList;
//@@author loycatherine

/**
 * The type Packing list manager.
 */
public class PackingListManager implements ReadOnlyPackingListManager {
    private final UniquePackingList packingList;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        packingList = new UniquePackingList();
    }

    /**
     * Instantiates a new Packing list manager.
     */
    public PackingListManager() {}

    /**
     * Creates an PackingListManager using the Items in the {@code toBeCopied}
     *
     * @param toBeCopied the to be copied
     */
    public PackingListManager(ReadOnlyPackingListManager toBeCopied) {
        this();
        resetDataPackingList(toBeCopied);
    }

    //// For Packing list overwrite operations

    /**
     * Resets the existing data of this {@code PackingManager} with {@code newData}.
     *
     * @param newData the new data
     */
    public void resetDataPackingList(ReadOnlyPackingListManager newData) {
        requireNonNull(newData);
        setPackingList(newData.getPackingList());
    }

    /**
     * Replaces the contents of the contacts list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     *
     * @param packingList the packing list
     */
    public void setPackingList(List<PackingListItem> packingList) {
        this.packingList.setPackingList(packingList);
    }

    //// packing list-level operations

    /**
     * Returns true if a contacts with the same identity as {@code contacts} exists in the address book.
     *
     * @param packingListItem the packingListItem
     * @return the boolean
     */
    public boolean hasItem(PackingListItem packingListItem) {
        requireNonNull(packingListItem);
        return packingList.contains(packingListItem);
    }

    /**
     * Returns the PackingListItem that matches the specified name and address
     *
     * @param name     the name
     * @param quantity the quantity
     * @return the item
     */
    public Optional<PackingListItem> getItem(Name name, Quantity quantity) {
        return packingList.getItem(name, quantity);
    }

    /**
     * Return the optional index of PackingListItem to find in {@code packingList}. Returns empty optional if
     * not found.
     *
     * @param toFind the to find
     * @return the optional
     */
    public Optional<Index> findItemIndex(PackingListItem toFind) {
        return packingList.indexOf(toFind);
    }

    /**
     * Adds a contacts to the address book.
     * The contacts must not already exist in the address book.
     *
     * @param packingListItem the packingListItem
     */
    public void addItem(PackingListItem packingListItem) {
        packingList.add(packingListItem);
    }

    /**
     * Adds a contacts to the address book.
     * The contacts must not already exist in the address book.
     *
     * @param index the index
     * @param packingListItem  the packingListItem
     */
    public void addItemAtIndex(Index index, PackingListItem packingListItem) {
        packingList.addAtIndex(index, packingListItem);
    }

    /**
     * Replaces the given contacts {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The contacts identity of {@code editedPerson} must not be the same as another existing contacts in the address
     * book.
     *
     * @param target     the target
     * @param editedPackingListItem the edited item
     */
    public void setItem(PackingListItem target, PackingListItem editedPackingListItem) {
        requireNonNull(editedPackingListItem);

        packingList.setItem(target, editedPackingListItem);
    }

    /**
     * Removes {@code key} from this {@code ItemManager}.
     * {@code key} must exist in the address book.
     *
     * @param key the key
     */
    public void removeItem(PackingListItem key) {
        packingList.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return packingList.asUnmodifiableObservableList().size() + " packingList,";
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
