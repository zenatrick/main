package seedu.address.model.packinglistitem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.packinglistitem.exceptions.DuplicateItemException;
import seedu.address.model.packinglistitem.exceptions.ItemNotFoundException;

class UniquePackingListTest {

    //They have a personBuilder, but since we dont have, improvise by using this for now
    public static final Item FIXED_ITEM_PANTS =
            new Item(new Name("Pants"), new Quantity(5),
                    false);

    public static final Item FIXED_ITEM_SHIRT =
            new Item(new Name("Shirt"), new Quantity(3),
                    false);

    private static final Item FIXED_ITEM_JEANS =
            new Item(new Name("Jeans"), new Quantity(10),
                    true);

    private final UniquePackingList uniquePackingList = new UniquePackingList();

    @Test
    public void contains_nullPackingList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePackingList.contains(null));
    }

    @Test
    public void contains_packingListNotInList_returnsFalse() {
        assertFalse(uniquePackingList.contains(FIXED_ITEM_JEANS));
    }

    @Test
    public void contains_packingListInList_returnsTrue() {
        uniquePackingList.add(FIXED_ITEM_JEANS);
        assertTrue(uniquePackingList.contains(FIXED_ITEM_JEANS));
    }

    @Test
    public void contains_packingListWithSameIdentifyFieldsInList_returnsTrue() {
        //Slightly different from UniquePersonListTest, due to different
        //contains convention used for AB3 and for ours.
        uniquePackingList.add(FIXED_ITEM_JEANS);
        Item item = new Item(new Name("Jeans"),
                new Quantity(10), false);
        assertTrue(uniquePackingList.contains(item));
    }

    @Test
    public void add_nullPackingList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePackingList.add(null));
    }

    @Test
    public void add_duplicatePackingList_throwsDuplicatePackingListException() {
        uniquePackingList.add(FIXED_ITEM_JEANS);
        assertThrows(DuplicateItemException.class, () -> uniquePackingList.add(FIXED_ITEM_JEANS));
    }

    @Test
    public void setPackingList_nullTargetPackingList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePackingList.setItem(null,
                FIXED_ITEM_JEANS));
    }

    @Test
    public void setPackingList_nullPackingList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePackingList
                .setItem(FIXED_ITEM_JEANS,
                        null));
    }

    @Test
    public void setPackingList_targetPackingListNotInList_throwsPackingListNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> uniquePackingList.setItem(
                FIXED_ITEM_JEANS, FIXED_ITEM_JEANS));
    }

    @Test
    public void setPackingList_editedPackingListIsSamePackingList_success() {
        uniquePackingList.add(FIXED_ITEM_JEANS);
        uniquePackingList.setItem(FIXED_ITEM_JEANS, FIXED_ITEM_JEANS);
        UniquePackingList expectedPackingList = new UniquePackingList();
        expectedPackingList.add(FIXED_ITEM_JEANS);
        assertEquals(expectedPackingList, uniquePackingList);
    }

    @Test
    public void setPackingListHasSameIdentity_success() {
        uniquePackingList.add(FIXED_ITEM_JEANS);
        Item item = new Item(new Name("Jeans"), new Quantity(10),
                true);
        uniquePackingList.setItem(FIXED_ITEM_JEANS, item);
        UniquePackingList expectedUniquePackingList = new UniquePackingList();
        expectedUniquePackingList.add(item);
        assertEquals(expectedUniquePackingList, uniquePackingList);
    }

    @Test
    public void setPackingList_editedPackingListHasDifferentPackingList_success() {
        uniquePackingList.add(FIXED_ITEM_JEANS);
        uniquePackingList.setItem(FIXED_ITEM_JEANS, FIXED_ITEM_PANTS);
        UniquePackingList expectedPackingExpenseList = new UniquePackingList();
        expectedPackingExpenseList.add(FIXED_ITEM_PANTS);
        assertEquals(expectedPackingExpenseList, uniquePackingList);
    }

    @Test
    public void setPackingList_editedPackingListHasNonUniqueIdentity_throwsDuplicatePackingListException() {
        uniquePackingList.add(FIXED_ITEM_JEANS);
        uniquePackingList.add(FIXED_ITEM_PANTS);
        assertThrows(DuplicateItemException.class, () -> uniquePackingList.setItem(
                FIXED_ITEM_JEANS, FIXED_ITEM_PANTS));
    }

    @Test
    public void remove_nullPackingList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePackingList.remove(null));
    }

    @Test
    public void remove_packingListDoesNotExist_throwsPackingListNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> uniquePackingList
                .remove(FIXED_ITEM_SHIRT));
    }

    @Test
    public void remove_existingPackingList_removesPackingList() {
        uniquePackingList.add(FIXED_ITEM_JEANS);
        uniquePackingList.remove(FIXED_ITEM_JEANS);
        UniquePackingList expectedPackingList = new UniquePackingList();
        assertEquals(expectedPackingList, uniquePackingList);
    }

    @Test
    public void setPackingList_nullUniquePackingList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePackingList
                .setPackingLists((UniquePackingList) null));
    }

    @Test
    public void setPackingList_uniquePackingList_replacesOwnListWithProvidedUniquePackingList() {
        uniquePackingList.add(FIXED_ITEM_SHIRT);
        UniquePackingList expectedUniquePackingList = new UniquePackingList();
        uniquePackingList.add(FIXED_ITEM_JEANS);
        uniquePackingList.setPackingLists(expectedUniquePackingList);
        assertEquals(expectedUniquePackingList, uniquePackingList);
    }

    @Test
    public void setUniquePackingList_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePackingList
                .setPackingLists((List<Item>) null));
    }

    @Test
    public void setUniquePackingList_list_replacesOwnListWithProvidedList() {
        uniquePackingList.add(FIXED_ITEM_JEANS);
        List<Item> itemLists = Collections.singletonList(FIXED_ITEM_SHIRT);
        uniquePackingList.setPackingLists(itemLists);
        UniquePackingList expectedPackingList = new UniquePackingList();
        expectedPackingList.add(FIXED_ITEM_SHIRT);
        assertEquals(expectedPackingList, uniquePackingList);
    }

    @Test
    public void setPackingList_listWithDuplicateFixedExpense_throwsDuplicateItemException() {
        List<Item> listWithDuplicateItems = Arrays
                .asList(FIXED_ITEM_JEANS, FIXED_ITEM_JEANS);
        assertThrows(DuplicateItemException.class, ()
            -> uniquePackingList.setPackingLists(listWithDuplicateItems));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniquePackingList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    void testEquals() {
        UniquePackingList expectedUniquePackingLists = new UniquePackingList();
        expectedUniquePackingLists.add(FIXED_ITEM_JEANS);
        uniquePackingList.add(FIXED_ITEM_JEANS);
        assertEquals(uniquePackingList, expectedUniquePackingLists);

    }

    @Test
    void testHashCode() {
        //Same Hash Code
        uniquePackingList.add(FIXED_ITEM_JEANS);
        UniquePackingList pl = new UniquePackingList();
        pl.add(FIXED_ITEM_JEANS);
        assertEquals(pl.hashCode(), uniquePackingList.hashCode());

        //Different Hash code
        UniquePackingList diffPl = new UniquePackingList();
        diffPl.add(FIXED_ITEM_SHIRT);
        assertNotEquals(diffPl.hashCode(), uniquePackingList.hashCode());
    }
}
