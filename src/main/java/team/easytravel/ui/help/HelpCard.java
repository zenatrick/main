package team.easytravel.ui.help;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import team.easytravel.ui.UiPart;
import team.easytravel.ui.transportationtab.TransportBookingCard;

public class HelpCard  extends UiPart<Region> {


    private static final String FXML = "helptab/HelpCard.fxml";
    private boolean isLink = false;

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    @FXML
    private HBox cardPane;
    @FXML
    private Label mode;
    @FXML
    private Label link;

    public final String helpInString;

    public HelpCard(String helpInString) {
        super(FXML);
        this.helpInString = helpInString;
        System.out.println(helpInString);
        mode.setText(helpInString);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TransportBookingCard)) {
            return false;
        }

        // state check
        HelpCard card = (HelpCard) other;
        return mode.getText().equals(card.mode.getText());
    }
}
