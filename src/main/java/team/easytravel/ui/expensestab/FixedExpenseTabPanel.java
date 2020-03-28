package team.easytravel.ui.expensestab;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
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
    private ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    private ObservableList<FixedExpense> fixedExpenses;


    @FXML
    private ListView<FixedExpense> fixedExpenseListView;

    @FXML
    private PieChart pieChart;

    @FXML
    private SplitPane splitPane;


    public FixedExpenseTabPanel(ObservableList<FixedExpense> fixedExpensesList, double height, double width) {
        super(FXML);
        this.fixedExpenses = fixedExpensesList;
        splitPane.setPrefWidth(width);
        fixedExpenseListView.setPrefWidth(0.75 * width);
        pieChart.setPrefWidth(0.25 * width);
        fixedExpenseListView.setItems(fixedExpensesList);
        fixedExpenseListView.setCellFactory(listView -> new FixedListViewCell());

        pieChart.setTitle("Fixed Expenses Breakdown");
        setPieChart();
        try {
            pieChart.getStylesheets().add("view/expensestab/PieChartColor.css");
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    private void setPieChart() {
        Map<String, Double> maps = new HashMap<>();
        for (FixedExpense fixedExpense : fixedExpenses) {
            maps.put(fixedExpense.getFixedExpenseCategory().value,
                    Double.parseDouble(fixedExpense.getAmount().value));
            pieChartData = maps.entrySet().stream()
                    .map(entry -> new PieChart.Data(entry.getKey(), entry.getValue()))
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
        }

        pieChart.setData(pieChartData);
    }


    /**
     * Custom {@code ListCell} that displays the graphics of a {@code FixedExpense} using a {@code FixedExpenseCard}.
     */
    class FixedListViewCell extends ListCell<FixedExpense> {
        @Override
        protected void updateItem(FixedExpense fixedExpense, boolean empty) {
            super.updateItem(fixedExpense, empty);
            if (fixedExpenses.isEmpty()) {
                pieChart.setLegendVisible(false);
                pieChart.setData(null);
            }

            if (empty || fixedExpense == null) {
                setGraphic(null);
                setText(null);

            } else {
                setPieChart();
                pieChart.setLabelsVisible(true);
                pieChart.setLegendVisible(true);
                setGraphic(new FixedExpenseCard(fixedExpense, getIndex() + 1).getRoot());
            }
        }
    }


}
