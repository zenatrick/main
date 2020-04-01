package team.easytravel.ui.help;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import team.easytravel.ui.UiPart;
import team.easytravel.ui.transportationtab.TransportBookingCard;

/**
 * An UI component that displays information of a {@code String}.
 */
public class HelpCard extends UiPart<Region> {


    private static final String FXML = "helptab/HelpCard.fxml";
    private static final String USERGUIDE_URL =
            "https://github.com/AY1920S2-CS2103T-W17-3/main/blob/master/docs/UserGuide.adoc";

    private final String helpInString;

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
    private Hyperlink hyperlink;
    @FXML
    private Label mode;


    public HelpCard(String helpInString) {
        super(FXML);
        this.helpInString = helpInString;
        if (helpInString.equals("Refer to the user guide: ")) {
            hyperlink.setText(USERGUIDE_URL);
        }

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

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
