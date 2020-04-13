package team.easytravel.ui.packinglisttab;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import team.easytravel.model.listmanagers.packinglistitem.PackingListItem;
import team.easytravel.ui.UiPart;

/**
 * An UI component that displays information of a {@code Item}.
 */
public class PackingListCard extends UiPart<Region> {

    private static final String FXML = "packinglisttab/PackingListCard.fxml";

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
    private Label itemCategory;
    @FXML
    private ImageView checkBox;
    @FXML
    private ImageView category;


    public PackingListCard(PackingListItem item, int displayedIndex) {
        super(FXML);
        this.item = item;
        id.setText(displayedIndex + ". ");
        itemName.setText(item.getItemName().toString());
        quantity.setText("Quantity: " + item.getQuantity().toString());
        itemCategory.setText("Category: " + item.getItemCategory().value);

        Image image;

        if (item.getIsChecked()) {
            image = new Image("/images/tick.png");
        } else {
            image = new Image("/images/cross.png");
        }

        checkBox.setImage(image);

        Image imageCategory;

        switch (item.getItemCategory().value) {
        case "swimming":
            imageCategory = new Image("/images/swim.png");
            break;
        case "clothes":
            imageCategory = new Image("/images/clothes.png");
            break;
        case "car":
            imageCategory = new Image("/images/car.png");
            break;
        case "camping":
            imageCategory = new Image("/images/camping.png");
            break;
        case "formal dinner female":
            imageCategory = new Image("/images/dress.png");
            break;
        case "formal dinner male":
            imageCategory = new Image("/images/suit.png");
            break;
        case "snow":
            imageCategory = new Image("/images/snow.png");
            break;
        case "gym":
            imageCategory = new Image("/images/gym.png");
            break;
        case "essentials":
            imageCategory = new Image("/images/warning.png");
            break;
        case "hiking":
            imageCategory = new Image("/images/adventurer.png");
            break;
        case "train":
            imageCategory = new Image("/images/train.png");
            break;
        case "cruise":
            imageCategory = new Image("/images/cruise.png");
            break;
        case "beach":
            imageCategory = new Image("/images/beach.png");
            break;
        case "toiletries":
            imageCategory = new Image("/images/soap.png");
            break;
        case "international":
            imageCategory = new Image("/images/global.png");
            break;
        case "work":
            imageCategory = new Image("/images/portfolio.png");
            break;
        case "photography":
            imageCategory = new Image("/images/camera.png");
            break;
        case "music festival":
            imageCategory = new Image("/images/music.png");
            break;
        case "airplane":
            imageCategory = new Image("/images/plane.png");
            break;
        default:
            imageCategory = new Image("/images/default.png");
            break;
        }

        category.setImage(imageCategory);
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
