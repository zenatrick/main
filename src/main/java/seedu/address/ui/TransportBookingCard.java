package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.transportbooking.TransportBooking;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class TransportBookingCard extends UiPart<Region> {

    private static final String FXML = "TransportBookingCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final TransportBooking transportBooking;

    @FXML
    private HBox cardPane;
    @FXML
    private Label mode;
    @FXML
    private Label id;
    @FXML
    private Label startlocation;
    @FXML
    private Label endlocation;
    @FXML
    private Label starttime;
    @FXML
    private Label endtime;
    @FXML
    private FlowPane tags;

    public TransportBookingCard(TransportBooking transportBooking, int displayedIndex) {
        super(FXML);
        this.transportBooking = transportBooking;
        id.setText(displayedIndex + ". ");
        mode.setText(transportBooking.getMode().value);
        startlocation.setText("Start location is: " + transportBooking.getStartLocation().value);
        endlocation.setText("End location is: " + transportBooking.getEndLocation().value);

        String startTime = transportBooking.getStartTime().value.toString();
        startTime = startTime.substring(0, 10) + " , " + startTime.substring(11);

        starttime.setText("Start time is: " + startTime);

        String endTime = transportBooking.getEndTime().value.toString();
        endTime = endTime.substring(0, 10) + " , " + endTime.substring(11);

        endtime.setText("End time is: " + endTime);
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
        TransportBookingCard card = (TransportBookingCard) other;
        return id.getText().equals(card.id.getText())
                && transportBooking.equals(card.transportBooking);
    }
}
