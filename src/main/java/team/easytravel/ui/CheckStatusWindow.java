package team.easytravel.ui;

import java.util.List;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import team.easytravel.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class CheckStatusWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(CheckStatusWindow.class);
    private static final String FXML = "CheckStatusWindow.fxml";

    @FXML
    private Label scheduleMessage;
    @FXML
    private Label packingListMessage;
    @FXML
    private Label fixedExpenseMessage;
    @FXML
    private ProgressBar packingListIndicator;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public CheckStatusWindow(Stage root) {
        super(FXML, root);
        root.setTitle("Check Status");
    }

    /**
     * Creates a new HelpWindow.
     */
    public CheckStatusWindow() {
        this(new Stage());
    }

    /**
     * Shows the checkstatus window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show(List<String> status) {
        logger.fine("Showing the trip's status in preparedness");
        scheduleMessage.setText(status.get(0));
        packingListMessage.setText(status.get(1));
        fixedExpenseMessage.setText(status.get(2));
        String packingList = status.get(1);
        String percentage = packingList.split(":")[2];
        double nominator = Double.parseDouble(percentage.split("/")[0]);
        double denominator = Double.parseDouble(percentage.split("/")[1]);
        packingListIndicator.getStylesheets().add("view/CheckStatus.css");
        packingListIndicator.setProgress(nominator / denominator);
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Closes this window.
     */
    public void close() {
        getRoot().close();
    }

    /**
     * Returns true if the window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

}
