package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class CheckStatusWindow extends UiPart<Stage> {

    public static final String HELP_MESSAGE = "You can be more prepared by checking" +
            " your packinglist, expenselist or accomodations";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "CheckStatusWindow.fxml";

    @FXML
    private Label statusMessage;
    @FXML
    private Label helpMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public CheckStatusWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        statusMessage.setText("You are set"); //need change here
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
    public void show() {
        logger.fine("Showing the trip's status in preparedness");
        getRoot().show();
        getRoot().centerOnScreen();
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
