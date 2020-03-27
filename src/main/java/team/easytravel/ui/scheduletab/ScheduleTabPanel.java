package team.easytravel.ui.scheduletab;

import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import team.easytravel.commons.core.LogsCenter;
import team.easytravel.model.trip.DayScheduleEntry;
import team.easytravel.ui.UiPart;

/**
 * Panel containing the schedule.
 */
public class ScheduleTabPanel extends UiPart<Region> {
    public static final String TAB_NAME = "Schedule Tab";

    private static final String FXML = "scheduletab/ScheduleTabPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ScheduleTabPanel.class);

    private List<ListView<DayScheduleEntry>> scheduleView;

    public ScheduleTabPanel(List<ObservableList<DayScheduleEntry>> schedule) {
        super(FXML);
        int size = schedule.size();
        for (int i = 0; i < size; i++) {
            scheduleView.get(i).setItems(schedule.get(i));
            scheduleView.get(i).setCellFactory(listView -> new DayScheduleListViewCell());
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Activity} using a {@code ActivityCard}.
     */
    class DayScheduleListViewCell extends ListCell<DayScheduleEntry> {
        @Override
        protected void updateItem(DayScheduleEntry dayScheduleEntry, boolean empty) {
            super.updateItem(dayScheduleEntry, empty);
            if (empty || dayScheduleEntry == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ScheduleEntryCard(dayScheduleEntry, getIndex() + 1).getRoot());
            }
        }
    }
}
