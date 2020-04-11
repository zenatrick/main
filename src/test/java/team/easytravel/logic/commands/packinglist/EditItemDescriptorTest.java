package team.easytravel.logic.commands.packinglist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import static team.easytravel.logic.commands.CommandTestUtil.ITEM_PASSPORT;
import static team.easytravel.logic.commands.CommandTestUtil.ITEM_UNDERWEAR;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ITEM_NAME_UNDERWEAR;

import org.junit.jupiter.api.Test;

import team.easytravel.logic.commands.packinglist.EditItemCommand.EditItemDescriptor;
import team.easytravel.testutil.packinglist.EditItemDescriptorBuilder;

public class EditItemDescriptorTest {
    @Test
    public void equals() {
        EditItemDescriptor descriptorWithSameValues = new EditItemDescriptor(ITEM_PASSPORT);

        // same values -> returns true
        assertEquals(ITEM_PASSPORT, descriptorWithSameValues);

        // same object -> returns true
        assertEquals(ITEM_PASSPORT, ITEM_PASSPORT);

        // null -> returns false
        assertNotEquals(ITEM_PASSPORT, null);

        // different types -> returns false
        assertNotEquals(ITEM_PASSPORT, 5);

        // different values -> returns false
        assertNotEquals(ITEM_PASSPORT, ITEM_UNDERWEAR);

        // different name -> returns false
        EditItemDescriptor editedUnderwear =
            new EditItemDescriptorBuilder(ITEM_PASSPORT).withItemName(VALID_ITEM_NAME_UNDERWEAR).build();

        assertNotEquals(ITEM_PASSPORT, editedUnderwear);
    }
}
