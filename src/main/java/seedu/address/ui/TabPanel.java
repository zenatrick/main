package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.listmanagers.fixedexpense.FixedExpense;
import seedu.address.model.listmanagers.transportbooking.TransportBooking;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class TabPanel extends UiPart<Region> {
    private static final String FXML = "TabPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TabPanel.class);

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane transportBookingPanelPlaceholder;

    @FXML
    private StackPane fixedExpensePanelPlaceholder;

    public TabPanel(ObservableList<Person> personList,
                    ObservableList<TransportBooking> transportList,
                    ObservableList<FixedExpense>fixedExpenseList) {
        super(FXML);
        personListPanelPlaceholder.getChildren().add(new PersonListPanel(personList).getRoot());
        transportBookingPanelPlaceholder.getChildren().add(new TransportBookingPanel(transportList).getRoot());
        fixedExpensePanelPlaceholder.getChildren().add(new FixedExpensePanel(fixedExpenseList).getRoot());
    }
}
