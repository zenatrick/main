package seedu.address.model.packinglistitem;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.packinglistitem.exceptions.DuplicatePackingListItemException;
import seedu.address.model.packinglistitem.exceptions.PackingListItemNotFoundException;

/**
 * The type Unique item list.
 */
public class UniquePackingList implements Iterable<PackingListItem> {
    private final ObservableList<PackingListItem> internalList = FXCollections.observableArrayList();
    private final ObservableList<PackingListItem> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Adds an item to the list.
     * The item must not already exist in the list.
     *
     * @param toAdd the to add
     */
    public void add (PackingListItem toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePackingListItemException();
        }
        internalList.add(toAdd);
    }

    /**
     * Add at index.
     *
     * @param index the index
     * @param toAdd the to add
     */
    public void addAtIndex(Index index, PackingListItem toAdd) {
        requireAllNonNull(index, toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePackingListItemException();
        }
        internalList.add(index.getZeroBased(), toAdd);
    }

    /**
     * Returns true if the list contains an equivalent PackingListItem as the given argument.
     *
     * @param toCheck the to check
     * @return the boolean
     */
    public boolean contains(PackingListItem toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameItem);
    }

    /**
     * Gets item.
     *
     * @param name     the name
     * @param quantity the quantity
     * @return the item
     */
    public Optional<PackingListItem> getItem(Name name, Quantity quantity) {
        requireAllNonNull(name, quantity);
        return internalList.stream().filter(x -> x.getItemName().equals(name) && x.getQuantity().equals(quantity))
                .findFirst();
    }

    /**
     * Index of optional.
     *
     * @param toFind the to find
     * @return the optional
     */
    public Optional<Index> indexOf(PackingListItem toFind) {
        requireNonNull(toFind);
        int indexOfToFind = internalList.indexOf(toFind);
        if (indexOfToFind == -1) {
            return Optional.empty();
        } else {
            return Optional.of(Index.fromZeroBased(indexOfToFind));
        }
    }

    /**
     * Replaces the item {@code target} in the list with {@code editedPackingListItem}.
     * {@code target} must exist in the list.
     * The item identity of {@code editedPackingListItem} must not be the same as another existing item in the list.
     *
     * @param target     the target
     * @param editedPackingListItem the edited item
     */
    public void setItem(PackingListItem target, PackingListItem editedPackingListItem) {
        requireAllNonNull(target, editedPackingListItem);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PackingListItemNotFoundException();
        }

        if (!target.isSameItem(editedPackingListItem) && contains(editedPackingListItem)) {
            throw new DuplicatePackingListItemException();
        }

        internalList.set(index, editedPackingListItem);
    }

    /**
     * Sets items.
     *
     * @param replacement the replacement
     */
    public void setPackingList(UniquePackingList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code packingListItems}.
     * {@code packingListItems} must not contain duplicate packingListItems.
     *
     * @param packingListItems the packingListItems
     */
    public void setPackingList(List<PackingListItem> packingListItems) {
        requireAllNonNull(packingListItems);
        if (!itemsAreUnique(packingListItems)) {
            throw new DuplicatePackingListItemException();
        }

        internalList.setAll(packingListItems);
    }

    /**
     * Removes the equivalent item from the list.
     * The item must exist in the list.
     *
     * @param toRemove the to remove
     */
    public void remove(PackingListItem toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PackingListItemNotFoundException();
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     *
     * @return the observable list
     */
    public ObservableList<PackingListItem> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<PackingListItem> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePackingList // instanceof handles nulls
                && internalList.equals(((UniquePackingList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code items} contains only unique items.
     */
    private boolean itemsAreUnique(List<PackingListItem> packingList) {
        for (int i = 0; i < packingList.size() - 1; i++) {
            for (int j = i + 1; j < packingList.size(); j++) {
                if (packingList.get(i).isSameItem(packingList.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
