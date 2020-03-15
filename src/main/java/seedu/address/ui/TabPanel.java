package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.fixedexpense.FixedExpense;
import seedu.address.model.packinglistitem.Item;
import seedu.address.model.person.Person;
import seedu.address.model.transportbooking.TransportBooking;

/**
 * Panel containing the list of persons.
 */
public class TabPanel extends UiPart<Region> {
    private static final String FXML = "TabPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TabPanel.class);
    private PackingListPanel packingListPanel;
    private FixedExpensePanel fixedExpensePanel;
    private TransportBookingPanel transportBookingPanel;

    @FXML
    private StackPane personListPanelPlaceholder1;

    @FXML
    private StackPane personListPanelPlaceholder2;

    @FXML
    private StackPane personListPanelPlaceholder3;

    public TabPanel(ObservableList<Person> personList,
                    ObservableList<FixedExpense> expenseList,
                    ObservableList<Item>itemList,
                    ObservableList<TransportBooking> transportList) {
        super(FXML);
        packingListPanel = new PackingListPanel(itemList);
        fixedExpensePanel = new FixedExpensePanel(expenseList);
        transportBookingPanel = new TransportBookingPanel(transportList);
        personListPanelPlaceholder1.getChildren().add(packingListPanel.getRoot());
        personListPanelPlaceholder2.getChildren().add(fixedExpensePanel.getRoot());
        personListPanelPlaceholder3.getChildren().add(transportBookingPanel.getRoot());
    }
}
