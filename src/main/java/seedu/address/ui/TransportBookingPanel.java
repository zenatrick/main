package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.packinglistitem.Item;

public class TransportBookingPanel extends UiPart<Region> {

    private static final String FXML = "PackingListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TransportBookingPanel.class);

    @FXML
    private ListView<Item> packingListView;

    public TransportBookingPanel(ObservableList<Item> packingList) {
        super(FXML);
        packingListView.setItems(packingList);
        packingListView.setCellFactory(listView -> new PackingListViewCell());
    }

    class PackingListViewCell extends ListCell<Item> {
        @Override
        protected void updateItem(Item item, boolean empty) {
            if(empty || item == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PackingListCard(item, getIndex()+1).getRoot());
            }
        }
    }

}
