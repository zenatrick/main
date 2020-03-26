package team.easytravel.ui.expensestab;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import team.easytravel.commons.core.LogsCenter;
import team.easytravel.model.listmanagers.fixedexpense.FixedExpense;
import team.easytravel.ui.UiPart;

/**
 * Panel containing the list of fixedExpense.
 */
public class FixedExpenseTabPanel extends UiPart<Region> {
    public static final String TAB_NAME = "Fixed Expenses Tab";

    private static final String FXML = "expensestab/FixedExpenseTabPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(FixedExpenseTabPanel.class);

    @FXML
    private ListView<FixedExpense> fixedExpenseListView;

    public FixedExpenseTabPanel(ObservableList<FixedExpense> fixedExpensesList) {
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
