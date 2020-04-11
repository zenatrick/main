package team.easytravel.logic.commands.packinglist;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static team.easytravel.logic.commands.CommandTestUtil.DESC_FINLAND;
import static team.easytravel.logic.commands.CommandTestUtil.DESC_JAPAN;
import static team.easytravel.logic.commands.CommandTestUtil.ITEM_UNDERWEAR;
import static team.easytravel.logic.commands.CommandTestUtil.ITEM_PASSPORT;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ACTIVITY_DURATION_JAPAN;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ACTIVITY_TITLE_JAPAN;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ITEM_CATEGORY_UNDERWEAR;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ITEM_NAME_UNDERWEAR;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_LOCATION_JAPAN;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_QUANTITY_UNDERWEAR;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_TAG_EXPENSIVE;

import org.junit.jupiter.api.Test;

import team.easytravel.logic.commands.activity.EditActivityCommand;
import team.easytravel.logic.commands.packinglist.EditItemCommand.EditItemDescriptor;
import team.easytravel.testutil.activity.EditActivityDescriptorBuilder;
import team.easytravel.testutil.packinglist.EditItemDescriptorBuilder;

public class EditItemDescriptorTest {
    @Test
    public void equals() {
        EditItemDescriptor descriptorWithSameValues = new EditItemDescriptor(ITEM_PASSPORT);

        // same values -> returns true
        assertTrue(ITEM_PASSPORT.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(ITEM_PASSPORT.equals(ITEM_PASSPORT));

        // null -> returns false
        assertFalse(ITEM_PASSPORT.equals(null));

        // different types -> returns false
        assertFalse(ITEM_PASSPORT.equals(5));

        // different values -> returns false
        assertFalse(ITEM_PASSPORT.equals(DESC_JAPAN));

        // different name -> returns false
        EditItemDescriptor editedUnderwear =
                new EditItemDescriptorBuilder(ITEM_PASSPORT).withItemName(VALID_ITEM_NAME_UNDERWEAR).build();
        assertFalse(ITEM_PASSPORT.equals(editedUnderwear));

//        // different phone -> returns false
//        editedUnderwear =
//                new EditItemDescriptorBuilder(ITEM_PASSPORT).withQuantity(VALID_Item_DURATION_JAPAN).build();
//        assertFalse(ITEM_PASSPORT.equals(editedUnderwear));
//
//        // different email -> returns false
//        editedUnderwear = new EditItemDescriptorBuilder(ITEM_PASSPORT).withLocation(VALID_LOCATION_JAPAN).build();
//        assertFalse(ITEM_PASSPORT.equals(editedUnderwear));
//
//        // different tags -> returns false
//        editedUnderwear = new EditItemDescriptorBuilder(ITEM_PASSPORT).withTags(VALID_TAG_EXPENSIVE).build();
//        assertFalse(ITEM_PASSPORT.equals(editedUnderwear));
    }
}
