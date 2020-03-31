package team.easytravel.ui.help;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import team.easytravel.commons.core.LogsCenter;
import team.easytravel.ui.TabPanel;
import team.easytravel.ui.transportationtab.TransportBookingTabPanel;

public class HelpTabPanel extends TabPanel {


    public static final String TAB_NAME = "Help Tab";

    private static final String FXML = "helptab/HelpPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TransportBookingTabPanel.class);
    private static final String USERGUIDE_URL =
            "https://github.com/AY1920S2-CS2103T-W17-3/main/blob/master/docs/UserGuide.adoc";

    @FXML
    private ListView<String> helpListView;

    public HelpTabPanel(ObservableList<String> helpList) {
        super(FXML);
        helpListView.setItems(helpList);
        helpListView.setCellFactory(listView -> new HelpListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a
     * {@code TransportList} using a {@code TransportBookingCard}.
     */
    class HelpListViewCell extends ListCell<String> {
        @Override
        protected void updateItem(String helpInString, boolean empty) {
            super.updateItem(helpInString, empty);
            if (empty || helpInString == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new HelpCard(helpInString).getRoot());
            }
        }
    }


}
