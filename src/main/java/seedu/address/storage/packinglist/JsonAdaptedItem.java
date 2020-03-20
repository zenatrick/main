package seedu.address.storage.packinglist;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.listmanagers.packinglistitem.ItemCategory;
import seedu.address.model.listmanagers.packinglistitem.ItemName;
import seedu.address.model.listmanagers.packinglistitem.PackingListItem;
import seedu.address.model.listmanagers.packinglistitem.Quantity;

/**
 * The type Json adapted item.
 */
public class JsonAdaptedItem {
    /**
     * The constant MISSING_FIELD_MESSAGE_FORMAT.
     */
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Item's %s field is missing!";

    private final String name;
    private final Integer quantity;
    private final String category;
    private final boolean isCheck;

    /**
     * Instantiates a new Json adapted item.
     *
     * @param name     the name
     * @param quantity the quantity
     * @param isCheck  the is check
     */
    @JsonCreator
    public JsonAdaptedItem(@JsonProperty("name") String name,
                           @JsonProperty("quantity") Integer quantity,
                           @JsonProperty("category") String category,
                           @JsonProperty("isCheck") boolean isCheck) {
        this.name = name;
        this.quantity = quantity;
        this.category = category;
        this.isCheck = isCheck;
    }

    /**
     * Instantiates a new Json adapted item.
     *
     * @param source the source
     */
    public JsonAdaptedItem(PackingListItem source) {
        name = source.getItemName().value;
        quantity = source.getQuantity().value;
        category = source.getItemCategory().value;
        isCheck = source.getChecked();
    }

    /**
     * To model type packing list item.
     *
     * @return the packing list item
     * @throws IllegalValueException the illegal value exception
     */
    public PackingListItem toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ItemName.class.getSimpleName()));
        }
        if (!ItemName.isValidName(name)) {
            throw new IllegalValueException(ItemName.MESSAGE_CONSTRAINTS);
        }
        final ItemName modelItemName = new ItemName(name);

        if (quantity == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Quantity.class.getSimpleName()));
        }
        if (!Quantity.isValidQuantity(quantity)) {
            throw new IllegalValueException(Quantity.MESSAGE_CONSTRAINTS);
        }
        final Quantity modelQuantity = new Quantity(quantity);

        if (!ItemCategory.isValidItemCategory(category)) {
            throw new IllegalValueException(Quantity.MESSAGE_CONSTRAINTS);
        }
        final ItemCategory modelCategory = new ItemCategory(category);

        return new PackingListItem(modelItemName, modelQuantity, modelCategory, isCheck);
    }
}
