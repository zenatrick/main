package seedu.address.model.packinglistitem;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents an Item in the packing list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Item {
    // Identity fields
    private final Check check;
    private final ItemName name;
    private final Quantity quantity;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     *
     * @param name     the name
     * @param quantity the quantity
     * @param check    the check
     * @param tags     the tags
     */
    public Item(ItemName name, Quantity quantity, Check check, Set<Tag> tags) {
        requireAllNonNull(name, quantity, tags);
        this.name = name;
        this.quantity = quantity;
        this.check = check;
        this.tags.addAll(tags);
    }

    /**
     * Gets item name.
     *
     * @return the item name
     */
    public ItemName getItemName() {
        return name;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public Quantity getQuantity() {
        return quantity;
    }

    /**
     * Gets check.
     *
     * @return the check
     */
    public Check getCheck() {
        return check;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return the tags
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both items of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two items.
     *
     * @param otherItem the other item
     * @return the boolean
     */
    public boolean isSameItem(Item otherItem) {
        if (otherItem == this) {
            return true;
        }

        return otherItem != null
                && otherItem.getItemName().equals(getItemName());
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

        if (!(other instanceof Item)) {
            return false;
        }

        Item otherItem = (Item) other;
        return otherItem.getItemName().equals(getItemName());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, quantity, check, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getItemName())
                .append(" Item Name: ")
                .append(getQuantity())
                .append(" Quantity: ")
                .append(getCheck())
                .append(" Check: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
