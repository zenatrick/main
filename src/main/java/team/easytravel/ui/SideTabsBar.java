package team.easytravel.ui;

import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import team.easytravel.commons.core.LogsCenter;
import team.easytravel.ui.accommodationtab.AccommodationBookingTabPanel;
import team.easytravel.ui.activitiestab.ActivityTabPanel;
import team.easytravel.ui.expensestab.FixedExpenseTabPanel;
import team.easytravel.ui.packinglisttab.PackingListTabPanel;
import team.easytravel.ui.scheduletab.ScheduleTabPanel;
import team.easytravel.ui.transportationtab.TransportBookingTabPanel;

/**
 * Panel containing the list of persons.
 */
public class SideTabsBar extends UiPart<Region> {
    private static final String FXML = "SideTabsBar.fxml";

    private final Logger logger = LogsCenter.getLogger(SideTabsBar.class);

    private Consumer<String> consumer;

    public SideTabsBar(Consumer<String> consumer) {
        super(FXML);
        this.consumer = consumer;
    }

    @FXML
    private void handleSwitchToScheduleTab() {
        consumer.accept(ScheduleTabPanel.TAB_NAME);
    }

    @FXML
    private void handleSwitchToActivitiesTab() {
        consumer.accept(ActivityTabPanel.TAB_NAME);
    }

    @FXML
    private void handleSwitchToAccommodationTab() {
        consumer.accept(AccommodationBookingTabPanel.TAB_NAME);
    }

    @FXML
    private void handleSwitchToTransportationTab() {
        consumer.accept(TransportBookingTabPanel.TAB_NAME);
    }

    @FXML
    private void handleSwitchToPackingListTab() {
        consumer.accept(PackingListTabPanel.TAB_NAME);
    }

    @FXML
    private void handleSwitchToFixedExpensesTab() {
        consumer.accept(FixedExpenseTabPanel.TAB_NAME);
    }
}
