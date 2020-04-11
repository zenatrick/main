package team.easytravel.testutil.packinglist;

import team.easytravel.logic.commands.packinglist.EditItemCommand;
import team.easytravel.logic.commands.packinglist.EditItemCommand.EditItemDescriptor;
import team.easytravel.model.listmanagers.packinglistitem.ItemCategory;
import team.easytravel.model.listmanagers.packinglistitem.ItemName;
import team.easytravel.model.listmanagers.packinglistitem.PackingListItem;
import team.easytravel.model.listmanagers.packinglistitem.Quantity;

/**
 * The type Edit item descriptor builder.
 */
public class EditItemDescriptorBuilder {
    private EditItemDescriptor descriptor;

    /**
     * Instantiates a new Edit item descriptor builder.
     */
    public EditItemDescriptorBuilder() {
        descriptor = new EditItemCommand.EditItemDescriptor();
    }

    /**
     * Instantiates a new Edit item descriptor builder.
     *
     * @param descriptor the descriptor
     */
    public EditItemDescriptorBuilder(EditItemCommand.EditItemDescriptor descriptor) {
        this.descriptor = new EditItemCommand.EditItemDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditItemDescriptor} with fields containing {@code Item}'s details
     *
     * @param item the item
     */
    public EditItemDescriptorBuilder(PackingListItem item) {
        descriptor = new EditItemCommand.EditItemDescriptor();

        descriptor.setItemName(item.getItemName());
        descriptor.setQuantity(item.getQuantity());
        descriptor.setItemCategory(item.getItemCategory());
    }

    /**
     * Sets the {@code title} of the {@code EditItemDescriptor} that we are building.
     *
     * @param itemName the item name
     * @return the edit item descriptor builder
     */
    public EditItemDescriptorBuilder withItemName(String itemName) {
        descriptor.setItemName(new ItemName(itemName));
        return this;
    }

    /**
     * Sets the {@code duration} of the {@code EditItemDescriptor} that we are building.
     *
     * @param quantity the quantity
     * @return the edit item descriptor builder
     */
    public EditItemDescriptorBuilder withQuantity(Integer quantity) {
        descriptor.setQuantity(new Quantity(quantity));
        return this;
    }

    /**
     * Sets the {@code ItemCategory} of the {@code EditItemDescriptor} that we are building.
     *
     * @param itemCategory the item category
     * @return the edit item descriptor builder
     */
    public EditItemDescriptorBuilder withItemCategory(String itemCategory) {
        descriptor.setItemCategory(new ItemCategory(itemCategory));
        return this;
    }

    /**
     * Build edit item command . edit item descriptor.
     *
     * @return the edit item command . edit item descriptor
     */
    public EditItemCommand.EditItemDescriptor build() {
        return descriptor;
    }
}
