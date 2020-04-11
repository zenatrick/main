package team.easytravel.ui.scheduletab;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import team.easytravel.ui.UiPart;

/**
 * An UI component that displays information of a {@code Item}.
 */
public class DayNumberCard extends UiPart<Region> {

    private static final String FXML = "scheduletab/DayNumberCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */


    @FXML
    private Label dayNumber;


    public DayNumberCard(int number) {
        super(FXML);
        dayNumber.setText("" + number);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DayNumberCard)) {
            return false;
        }

        // state check
        DayNumberCard card = (DayNumberCard) other;
        return dayNumber.getText().equals(card.dayNumber.getText());
    }
}
