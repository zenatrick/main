package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.listmanagers.accommodationbooking.AccommodationBooking;

/**
 * Panel containing the list of accommodations.
 */
public class AccommodationBookingPanel extends UiPart<Region> {

    private static final String FXML = "AccommodationBookingPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AccommodationBookingPanel.class);

    @FXML
    private ListView<AccommodationBooking> accommodationBookingListView;

    public AccommodationBookingPanel(ObservableList<AccommodationBooking> accommodationBookingList) {
        super(FXML);
        accommodationBookingListView.setItems(accommodationBookingList);
        accommodationBookingListView.setCellFactory(listView -> new AccommodationBookingListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Accommodation} using a
     * {@code AccommodationListCard}.
     */
    class AccommodationBookingListViewCell extends ListCell<AccommodationBooking> {
        @Override
        protected void updateItem(AccommodationBooking accommodationBooking, boolean empty) {
            super.updateItem(accommodationBooking, empty);
            if (empty || accommodationBooking == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AccommodationBookingCard(accommodationBooking, getIndex() + 1).getRoot());
            }
        }
    }


}
