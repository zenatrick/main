package team.easytravel.ui.expensestab;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import team.easytravel.commons.core.LogsCenter;
import team.easytravel.logic.Logic;
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
        //Set the Pref Width and height here.
        splitPane.setPrefWidth(width);
        fixedExpenseListView.setPrefWidth(width/2);
        //fixedExpenseListView.setPrefSize(width/2, height-100);
        pieChart.setPrefWidth(width/2);
        //pieChart.setPrefSize(width/2, height-100);

        fixedExpenseListView.setItems(fixedExpensesList);
        fixedExpenseListView.setCellFactory(listView -> new FixedListViewCell());

        pieChart.setTitle("Fixed Expenses Breakdown");
        setPieChart();
        pieChart.getStylesheets().add("/view/expensestab/PieChartColor.css");


    }

    private void setPieChart() {
        Map<String, Double> maps = new HashMap<>();
        for (FixedExpense fixedExpense : fixedExpenses) {
            System.out.println("The size of fixed Expenses is " + fixedExpenses.size());
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
                pieChart.setLegendVisible(true);
                setGraphic(new FixedExpenseCard(fixedExpense, getIndex() + 1).getRoot());
            }
        }
    }


}
