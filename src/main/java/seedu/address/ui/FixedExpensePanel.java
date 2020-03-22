package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.listmanagers.fixedexpense.FixedExpense;

/**
 * Panel containing the list of fixedExpense.
 */
public class FixedExpensePanel extends UiPart<Region> {

    private static final String FXML = "FixedExpensePanel.fxml";
    private final Logger logger = LogsCenter.getLogger(FixedExpensePanel.class);

    @FXML
    private ListView<FixedExpense> fixedExpenseListView;

    public FixedExpensePanel(ObservableList<FixedExpense> fixedExpensesList) {
        super(FXML);
        fixedExpenseListView.setItems(fixedExpensesList);
        fixedExpenseListView.setCellFactory(listView -> new FixedListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code FixedExpense} using a {@code FixedExpenseCard}.
     */
    class FixedListViewCell extends ListCell<FixedExpense> {
        @Override
        protected void updateItem(FixedExpense fixedExpense, boolean empty) {
            super.updateItem(fixedExpense, empty);
            if (empty || fixedExpense == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new FixedExpenseCard(fixedExpense, getIndex() + 1).getRoot());
            }
        }
    }

}
