package team.easytravel.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import team.easytravel.commons.core.LogsCenter;
import team.easytravel.model.listmanagers.accommodationbooking.AccommodationBooking;
import team.easytravel.model.listmanagers.activity.Activity;
import team.easytravel.model.listmanagers.fixedexpense.FixedExpense;
import team.easytravel.model.listmanagers.packinglistitem.PackingListItem;
import team.easytravel.model.listmanagers.transportbooking.TransportBooking;

/**
 * Panel containing the list of persons.
 */
public class TabPanel extends UiPart<Region> {
    private static final String FXML = "TabPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TabPanel.class);

    @FXML
    private StackPane transportBookingPanelPlaceholder;

    @FXML
    private StackPane fixedExpensePanelPlaceholder;

    @FXML
    private StackPane packingListPanelPlaceholder;

    @FXML
    private StackPane accommodationListPanelPlaceholder;

    @FXML
    private StackPane activityPanelPlaceholder;


    public TabPanel(ObservableList<TransportBooking> transportList,
                    ObservableList<FixedExpense> fixedExpenseList,
                    ObservableList<PackingListItem> packingList,
                    ObservableList<AccommodationBooking> accommodationList,
                    ObservableList<Activity> activityList
    ) {
        super(FXML);
        transportBookingPanelPlaceholder.getChildren().add(new TransportBookingPanel(transportList).getRoot());
        fixedExpensePanelPlaceholder.getChildren().add(new FixedExpensePanel(fixedExpenseList).getRoot());
        packingListPanelPlaceholder.getChildren().add(new PackingListPanel(packingList).getRoot());
        accommodationListPanelPlaceholder.getChildren().add(new AccommodationBookingPanel(accommodationList).getRoot());
        activityPanelPlaceholder.getChildren().add(new ActivityPanel(activityList).getRoot());
    }
}
