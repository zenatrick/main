package team.easytravel.testutil.packinglist;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import team.easytravel.logic.commands.packinglist.EditItemCommand;
import team.easytravel.logic.commands.packinglist.EditItemCommand.EditItemDescriptor;
import team.easytravel.model.listmanagers.activity.Activity;
import team.easytravel.model.listmanagers.activity.Duration;
import team.easytravel.model.listmanagers.packinglistitem.ItemName;
import team.easytravel.model.listmanagers.packinglistitem.PackingListItem;
import team.easytravel.model.util.attributes.Location;
import team.easytravel.model.util.attributes.Title;
import team.easytravel.model.util.attributes.tag.Tag;
import team.easytravel.testutil.activity.EditActivityDescriptorBuilder;

public class EditItemDescriptorBuilder {
    private EditItemDescriptor descriptor;

    public EditItemDescriptorBuilder() {
        descriptor = new EditItemCommand.EditItemDescriptor();
    }

    public EditItemDescriptorBuilder(EditItemCommand.EditItemDescriptor descriptor) {
        this.descriptor = new EditItemCommand.EditItemDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditItemDescriptor} with fields containing {@code Item}'s details
     */
    public EditItemDescriptorBuilder(PackingListItem Item) {
        descriptor = new EditItemCommand.EditItemDescriptor();

        descriptor.setItemName(Item.getItemName());
        descriptor.setQuantity(Item.getQuantity());
        descriptor.setItemCategory(Item.getItemCategory());
    }

    /**
     * Sets the {@code title} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withItemName(String itemName) {
        descriptor.setItemName(new ItemName(itemName));
        return this;
    }

    /**
     * Sets the {@code duration} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withDuration(Integer duration) {
        descriptor.setDuration(new Duration(duration));
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withLocation(String location) {
        descriptor.setLocation(new Location(location));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditItemDescriptor}
     * that we are building.
     */
    public EditItemDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditItemCommand.EditItemDescriptor build() {
        return descriptor;
    }
}
