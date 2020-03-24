package seedu.address.model.listmanagers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPackingListItem.PACKING_LIST_JEANS;
import static seedu.address.testutil.TypicalPackingListItem.PACKING_LIST_SHIRT;
import static seedu.address.testutil.TypicalPackingListItem.getTypicalPackingListManager;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.listmanagers.packinglistitem.PackingListItem;
import seedu.address.model.util.uniquelist.exceptions.DuplicateElementException;

class PackingListManagerTest {

    private final PackingListManager packingListManager = new PackingListManager();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), packingListManager.getPackingList());
    }

    @Test
    public void resetDataNullThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> packingListManager.resetData(null));
    }

    @Test
    public void resetDataWithValidReadOnlyPackingListManagerReplacesData() {
        PackingListManager newData = getTypicalPackingListManager();
        packingListManager.resetData(newData);
        assertEquals(newData, packingListManager);
    }

    @Test
    public void resetDataWithDuplicatePackingListItemThrowsDuplicateElementException() {
        List<PackingListItem> newPackingListItem = Arrays.asList(PACKING_LIST_JEANS, PACKING_LIST_JEANS);
        PackingListManagerStub newData = new PackingListManagerStub(newPackingListItem);
        assertThrows(DuplicateElementException.class, () -> packingListManager.resetData(newData));
    }

    @Test
    public void hasPackingListItemNullPackingListItemThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> packingListManager
                .hasPackingListItem(null));
    }

    @Test
    public void hasPackingListItemNotInPackingListManagerReturnsFalse() {
        assertFalse(packingListManager.hasPackingListItem(PACKING_LIST_JEANS));
    }

    @Test
    public void hasPackingListItemInPackingListManagerReturnsTrue() {
        packingListManager.addPackingListItem(PACKING_LIST_SHIRT);
        assertTrue(packingListManager.hasPackingListItem(PACKING_LIST_SHIRT));
    }

    @Test
    public void getPackingListItemModifyListThrowsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> packingListManager
                .getPackingList().remove(0));
    }

    /**
     * A stub ReadOnlyPackingListManager whose Packing list can violate interface components.
     */

    private static class PackingListManagerStub implements ReadOnlyPackingListManager {
        private final ObservableList<PackingListItem> packingListItems =
                FXCollections.observableArrayList();

        PackingListManagerStub(Collection<PackingListItem> packingListItems) {
            this.packingListItems.setAll(packingListItems);
        }

        @Override
        public ObservableList<PackingListItem> getPackingList() {
            return packingListItems;
        }
    }
}
