package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.listmanagers.PackingListManager;
import seedu.address.model.listmanagers.packinglistitem.ItemCategory;
import seedu.address.model.listmanagers.packinglistitem.ItemName;
import seedu.address.model.listmanagers.packinglistitem.PackingListItem;
import seedu.address.model.listmanagers.packinglistitem.Quantity;

/**
 * A utility class containing a list of {@code PackingListItem} objects to be used in tests.
 */
public class TypicalPackingListItem {

    public static final PackingListItem PACKING_LIST_SHIRT = new PackingListItem(
            new ItemName("Tshirt"), new Quantity(7), new ItemCategory("basics"), false);

    public static final PackingListItem PACKING_LIST_JEANS = new PackingListItem(
            new ItemName("Jeans"), new Quantity(5), new ItemCategory("basics"), false);

    public static final PackingListItem PACKING_LIST_UNDERWEAR = new PackingListItem(
            new ItemName("Underwear"), new Quantity(7), new ItemCategory("essentials"), false);

    private TypicalPackingListItem() {
    }

    /**
     * Returns an {@code TypicalPackingListItem} with all the typical PackingListItem
     */

    public static PackingListManager getTypicalPackingListManager() {
        PackingListManager pm = new PackingListManager();
        for (PackingListItem packingListItem : getTypicalPackingListItems()) {
            pm.addPackingListItem(packingListItem);
        }
        return pm;
    }

    public static List<PackingListItem> getTypicalPackingListItems() {
        return new ArrayList<>(Arrays.asList(PACKING_LIST_UNDERWEAR, PACKING_LIST_SHIRT,
                PACKING_LIST_JEANS));
    }


}
