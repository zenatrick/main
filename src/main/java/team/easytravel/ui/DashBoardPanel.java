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
    private Label tripEndDate;
    @FXML
    private Label budget;

    public DashBoardPanel(Trip triptoshow) {
        super(FXML);
        this.budget.setText(triptoshow.getBudget().toString());
        this.tripStartDate.setText(triptoshow.getStartDate().toString());
        this.tripEndDate.setText(triptoshow.getEndDate().toString());
        this.tripTitle.setText(triptoshow.getTitle().toString());
    }


    /**
     * Updates the window
     */
    public void update(Trip updated) {
        this.budget.setText(updated.getBudget().toString());
        this.tripStartDate.setText(updated.getStartDate().toString());
        this.tripEndDate.setText(updated.getEndDate().toString());
        this.tripTitle.setText(updated.getTitle().toString());
    }

}
