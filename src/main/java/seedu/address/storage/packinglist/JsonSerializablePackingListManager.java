package seedu.address.storage.packinglist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.listmanagers.PackingListManager;
import seedu.address.model.listmanagers.ReadOnlyPackingListManager;
import seedu.address.model.listmanagers.packinglistitem.PackingListItem;

/**
 * The type Json serializable packing list manager.
 */
@JsonRootName(value = "packingListManager")
public class JsonSerializablePackingListManager {
    /**
     * The constant MESSAGE_DUPLICATE_ITEM.
     */
    public static final String MESSAGE_DUPLICATE_ITEM = "Packing list contains duplicate "
            + "item(s).";

    private final List<JsonAdaptedItem> packingList = new ArrayList<>();

    /**
     * Instantiates a new Json serializable packing list manager.
     *
     * @param packingList the packing list
     */
    @JsonCreator
    public JsonSerializablePackingListManager(
            @JsonProperty("packingList") List<JsonAdaptedItem> packingList) {
        this.packingList.addAll(packingList);
    }

    /**
     * Instantiates a new Json serializable packing list manager.
     *
     * @param source the source
     */
    public JsonSerializablePackingListManager(ReadOnlyPackingListManager source) {
        packingList.addAll(
                source.getPackingList()
                        .stream()
                        .map(JsonAdaptedItem::new)
                        .collect(Collectors.toList())
        );
    }

    /**
     * Converts this JsonSerializablePackingListManager into the model's {@code PackingListManager} object.
     *
     * @return the packing list manager
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PackingListManager toModelType() throws IllegalValueException {
        PackingListManager packingListManager = new PackingListManager();
        for (JsonAdaptedItem jsonAdaptedItem : packingList) {
            PackingListItem item = jsonAdaptedItem.toModelType();
            if (packingListManager.hasPackingListItem(item)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ITEM);
            }
            packingListManager.addPackingListItem(item);
        }
        return packingListManager;
    }
}
