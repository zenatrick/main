package team.easytravel.ui.packinglisttab;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import team.easytravel.commons.core.LogsCenter;
import team.easytravel.ui.UiPart;

public class ListPresetWindow extends UiPart<Stage> {
    public static final String HELP_MESSAGE = "Presets";

    private static final Logger logger = LogsCenter.getLogger(ListPresetWindow.class);
    private static final String FXML = "packinglisttab/ListPresetWindow.fxml";


    /**
     * Creates a new ListPresetWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public ListPresetWindow(Stage root) {
        super(FXML, root);
        root.setTitle("List Preset");
    }

    /**
     * Creates a new HelpWindow.
     */
    public ListPresetWindow() {
        this(new Stage());
    }

    /**
     * Shows the list preset window.
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
        logger.fine("Showing the available packing list presets");
        Text preset = new Text("Available presets: \nEssentials\nSwim");
        String essentials = "Essentials\n";
        String swim = "Swim\n";
        String camping = "Camping\n";
        String toiletries = "Toiletries\n";

        String msg = preset + essentials + swim + camping + toiletries;
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
