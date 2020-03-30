package team.easytravel.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import team.easytravel.commons.core.LogsCenter;
import team.easytravel.model.trip.Trip;

/**
 * Panel containing the list of persons.
 */
public class DashBoardPanel extends UiPart<Region> {
    private static final String FXML = "DashBoardPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(DashBoardPanel.class);

    @FXML
    private Label tripTitle;
    @FXML
    private Label tripStartDate;
    @FXML
    private Label budget;

    public DashBoardPanel(Trip triptoshow) {
        super(FXML);
        this.budget.setText("");
        this.budget.setText("Budget: " + triptoshow.getBudget().toString());
        this.tripStartDate.setText(
                "Duration of trip: " + triptoshow.getStartDate().toString() + " ~ "
                        + triptoshow.getEndDate().toString());
        this.tripTitle.setText("Title: " + triptoshow.getTitle().toString());
    }

    public DashBoardPanel() {
        super(FXML);
        this.budget.setText(" ");
        this.tripStartDate.setText("Hint: Use settrip to start a trip");
        this.tripTitle.setText("There is no trip set");
    }

}
