package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.listmanagers.packinglistitem.PackingListItem;

/**
 * Panel containing the list of items.
 */
public class PackingListPanel extends UiPart<Region> {

    private static final String FXML = "PackingListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PackingListPanel.class);

    @FXML
    private ListView<PackingListItem> packingListView;

    public PackingListPanel(ObservableList<PackingListItem> packingList) {
        super(FXML);
        packingListView.setItems(packingList);
        packingListView.setCellFactory(listView -> new PackingListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Item} using a {@code PackingListCard}.
     */
    class PackingListViewCell extends ListCell<PackingListItem> {
        @Override
        protected void updateItem(PackingListItem item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PackingListCard(item, getIndex() + 1).getRoot());
            }
        }
    }

}
