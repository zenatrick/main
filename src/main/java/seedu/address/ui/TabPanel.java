package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class TabPanel extends UiPart<Region> {
    private static final String FXML = "TabPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TabPanel.class);
    private PersonListPanel personListPanel1;
    private PersonListPanel personListPanel2;
    private PersonListPanel personListPanel3;

    @FXML
    private StackPane personListPanelPlaceholder1;

    @FXML
    private StackPane personListPanelPlaceholder2;

    @FXML
    private StackPane personListPanelPlaceholder3;

    public TabPanel(ObservableList<Person> personList) {
        super(FXML);
        personListPanel1 = new PersonListPanel(personList);
        personListPanel2 = new PersonListPanel(personList);
        personListPanel3 = new PersonListPanel(personList);
        personListPanelPlaceholder1.getChildren().add(personListPanel1.getRoot());
        personListPanelPlaceholder2.getChildren().add(personListPanel2.getRoot());
        personListPanelPlaceholder3.getChildren().add(personListPanel3.getRoot());
    }
}
