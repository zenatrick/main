package team.easytravel.ui.scheduletab;

import java.util.logging.Logger;

import javafx.scene.layout.Region;
import team.easytravel.commons.core.LogsCenter;
import team.easytravel.ui.UiPart;

/**
 * Panel containing the schedule.
 */
public class ScheduleTabPanel extends UiPart<Region> {
    public static final String TAB_NAME = "Schedule Tab";

    private static final String FXML = "scheduletab/ScheduleTabPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ScheduleTabPanel.class);

    public ScheduleTabPanel() {
        super(FXML);
    }
}
