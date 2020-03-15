package seedu.address.model.packinglistitem;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a PackingListItem in the PackingListManager.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class PackingListItem {
    // Identity fields
    private final Name name;

    // Data fields
    private final boolean isChecked;
    private final Quantity quantity;

    /**
     * Every field must be present and not null.
     */
    public PackingListItem(Name name, Quantity quantity, boolean isChecked) {
        requireAllNonNull(name, quantity);
        this.name = name;
        this.quantity = quantity;
        this.isChecked = isChecked;
    }

    public Name getItemName() {
        return name;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    /**
     * Returns true of the item is checked.
     *
     * @return true if the item is checked.
     */
    public boolean isChecked() {
        return isChecked;
    }

    /**
     * Returns true if both items of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two items.
     *
     * @param otherPackingListItem the other item
     * @return true if both items are the same.
     */
    public boolean isSameItem(PackingListItem otherPackingListItem) {
        if (otherPackingListItem == this) {
            return true;
        }

        return otherPackingListItem != null
                && otherPackingListItem.name.equals(name);
    }

    /**
     * Returns true if both items have the same identity and data fields.
     * This defines a stronger notion of equality between two items.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PackingListItem)) {
            return false;
        }

        PackingListItem otherPackingListItem = (PackingListItem) other;
        return otherPackingListItem.getItemName().equals(getItemName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity, isChecked);
    }

    @Override
    public String toString() {
        return "Packing list item - Name: " + name
                + " Quantity: " + quantity
                + " Is Checked: " + isChecked;
    }
}
