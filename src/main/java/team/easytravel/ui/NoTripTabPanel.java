package team.easytravel.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * Panel containing the schedule.
 */
public class NoTripTabPanel extends TabPanel {
    private static final String FXML = "NoTripTabPanel.fxml";

    @FXML
    private AnchorPane panel;

    public NoTripTabPanel() {
        super(FXML);
        Label label = new Label();
        label.setText("NO TRIP SET");
        panel.getChildren().add(label);
    }
}
