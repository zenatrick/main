package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.listmanagers.packinglistitem.PackingListItem;

/**
 * An UI component that displays information of a {@code Item}.
 */
public class PackingListCard extends UiPart<Region> {

    private static final String FXML = "PackingListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final PackingListItem item;

    @FXML
    private HBox cardPane;
    @FXML
    private Label itemName;
    @FXML
    private Label id;
    @FXML
    private Label quantity;
    @FXML
    private Label isChecked;
    @FXML
    private Label itemCategory;

    public PackingListCard(PackingListItem item, int displayedIndex) {
        super(FXML);
        this.item = item;
        id.setText(displayedIndex + ". ");
        itemName.setText(item.getItemName().toString());
        quantity.setText("Quantity: " + item.getQuantity().toString());
        isChecked.setText("Is Checked: " + item.isChecked());
        itemCategory.setText("Category: " + item.getItemCategory().value);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PackingListCard)) {
            return false;
        }

        // state check
        PackingListCard card = (PackingListCard) other;
        return id.getText().equals(card.id.getText())
                && item.equals(card.item);
    }
}
