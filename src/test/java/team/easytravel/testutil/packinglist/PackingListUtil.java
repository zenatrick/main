package team.easytravel.testutil.packinglist;

import static team.easytravel.logic.parser.CliSyntax.PREFIX_ITEM_CATEGORY;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_ITEM_NAME;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_ITEM_QUANTITY;

import team.easytravel.logic.commands.packinglist.AddItemCommand;
import team.easytravel.logic.commands.packinglist.EditItemCommand.EditItemDescriptor;
import team.easytravel.model.listmanagers.packinglistitem.PackingListItem;


/**
 * A utility class for PackingListBooking.
 */
public class PackingListUtil {

    /**
     * Returns an add command string for adding the {@code PackingListBooking}.
     */
    public static String getAddCommand(PackingListItem packingListItem) {
        return AddItemCommand.COMMAND_WORD + " " + getPackingListBookingDetails(packingListItem);
    }

    /**
     * Returns the part of command string for the given {@code PackingListItem}'s details.
     */
    public static String getPackingListBookingDetails(PackingListItem packingListItem) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_ITEM_NAME + packingListItem.getItemName().value + " ");
        sb.append(PREFIX_ITEM_CATEGORY + packingListItem.getItemCategory().value + " ");
        sb.append(PREFIX_ITEM_QUANTITY + packingListItem.getQuantity().value.toString() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPackingListItemDescriptor}'s details.
     */
    public static String getEditPackingListBookingDescriptorDetails(EditItemDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getItemName().ifPresent(name -> sb.append(PREFIX_ITEM_NAME).append(name.value).append(" "));
        descriptor.getItemCategory().ifPresent(category -> sb.append(PREFIX_ITEM_CATEGORY)
                .append(category.value)
                .append(" "));
        descriptor.getQuantity().ifPresent(quantity -> sb.append(PREFIX_ITEM_QUANTITY)
                .append(quantity.value)
                .append(" "));

        return sb.toString();
    }
}
