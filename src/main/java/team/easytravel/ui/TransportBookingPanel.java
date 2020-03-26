package team.easytravel.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import team.easytravel.commons.core.LogsCenter;
import team.easytravel.model.listmanagers.transportbooking.TransportBooking;

/**
 * Panel containing the list of transportBookings.
 */
public class TransportBookingPanel extends UiPart<Region> {

    private static final String FXML = "TransportBookingPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TransportBookingPanel.class);

    @FXML
    private ListView<TransportBooking> transportBookingListView;

    public TransportBookingPanel(ObservableList<TransportBooking> transportBookingList) {
        super(FXML);
        transportBookingListView.setItems(transportBookingList);
        transportBookingListView.setCellFactory(listView -> new TransportListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a
     * {@code TransportList} using a {@code TransportBookingCard}.
     */
    class TransportListViewCell extends ListCell<TransportBooking> {
        @Override
        protected void updateItem(TransportBooking transportBooking, boolean empty) {
            super.updateItem(transportBooking, empty);
            if (empty || transportBooking == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TransportBookingCard(transportBooking, getIndex() + 1).getRoot());
            }
        }
    }


}
