package seedu.address.storage.packinglist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyPackingListManager;
import seedu.address.model.PackingListManager;
import seedu.address.model.packinglistitem.Item;

@JsonRootName(value = "packingListManager")
public class JsonSerializablePackingListManager {
    public static final String MESSAGE_DUPLICATE_ITEM = "Packing list contains duplicate "
            + "item(s).";

    private final List<JsonAdaptedPackingList> packingList = new ArrayList<>();

    @JsonCreator
    public JsonSerializablePackingListManager(
            @JsonProperty("packingList") List<JsonAdaptedPackingList> packingList) {
        this.packingList.addAll(packingList);
    }

    public JsonSerializablePackingListManager(ReadOnlyPackingListManager source) {
        packingList.addAll(
                source.getPackingList()
                        .stream()
                        .map(JsonAdaptedPackingList::new)
                        .collect(Collectors.toList())
        );
    }

    /**
     * Converts this JsonSerializablePackingListManager into the model's {@code PackingListManager} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PackingListManager toModelType() throws IllegalValueException {
        PackingListManager packingListManager = new PackingListManager();
        for (JsonAdaptedPackingList jsonAdaptedPackingList : packingList) {
            Item item = jsonAdaptedPackingList.toModelType();
            if (packingListManager.hasItem(item)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ITEM);
            }
            packingListManager.addItem(item);
        }
        return packingListManager;
    }
}
