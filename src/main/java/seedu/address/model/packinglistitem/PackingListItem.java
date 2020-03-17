package seedu.address.model.packinglistitem;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.util.uniquelist.UniqueListElement;

/**
 * Represents a PackingListItem in the PackingListManager.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class PackingListItem implements UniqueListElement {
    // Identity fields
    private final ItemName itemName;

    // Data fields
    private final boolean isChecked;
    private final Quantity quantity;

    /**
     * Every field must be present and not null.
     */
    public PackingListItem(ItemName itemName, Quantity quantity, boolean isChecked) {
        requireAllNonNull(itemName, quantity);
        this.itemName = itemName;
        this.quantity = quantity;
        this.isChecked = isChecked;
    }

    public ItemName getItemName() {
        return itemName;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    /**
     * Returns true of the packing list item is checked.
     */
    public boolean isChecked() {
        return isChecked;
    }

    /**
     * Returns true if both packing list items has the same identity fields.
     * This defines a weaker notion of equality between two packing list items.
     */
    @Override
    public boolean isSame(UniqueListElement other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PackingListItem)) {
            return false;
        }

        return itemName.equals(((PackingListItem) other).itemName);
    }

    /**
     * Returns true if both packing list items have the same identity and data fields.
     * This defines a stronger notion of equality between two packing list items.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PackingListItem)) {
            return false;
        }

        PackingListItem otherItem = (PackingListItem) other;
        return itemName.equals(otherItem.itemName)
                && quantity.equals(otherItem.quantity)
                && isChecked == otherItem.isChecked;
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, quantity, isChecked);
    }

    @Override
    public String toString() {
        return "Packing list item - Item Name: " + itemName
                + " Quantity: " + quantity
                + " Is Checked: " + isChecked;
    }
}
