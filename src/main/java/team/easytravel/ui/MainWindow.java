package team.easytravel.ui;

import java.util.List;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import team.easytravel.commons.core.GuiSettings;
import team.easytravel.commons.core.LogsCenter;
import team.easytravel.logic.Logic;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.logic.parser.exceptions.ParseException;
import team.easytravel.ui.accommodationtab.AccommodationBookingTabPanel;
import team.easytravel.ui.activitiestab.ActivityTabPanel;
import team.easytravel.ui.expensestab.FixedExpenseTabPanel;
import team.easytravel.ui.packinglisttab.PackingListTabPanel;
import team.easytravel.ui.scheduletab.ScheduleTabPanel;
import team.easytravel.ui.transportationtab.TransportBookingTabPanel;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private CheckStatusWindow checkStatusWindow;

    // Individual list panels
    private TabPanel scheduleTabPanel;
    private TabPanel activityTabPanel;
    private TabPanel accommodationBookingTabPanel;
    private TabPanel transportBookingTabPanel;
    private TabPanel packingListTabPanel;
    private TabPanel fixedExpenseTabPanel;

    @FXML
    private StackPane sideTabsBarPlaceholder;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private StackPane tabPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane dashboardPlaceholder;

    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());


        helpWindow = new HelpWindow();
        checkStatusWindow = new CheckStatusWindow();

        // Set up the list panels
        if (logic.hasTrip()) {
            handleSetTrip();
        } else {
            handleDeleteTrip();
        }

    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }


    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        // Independent Ui parts residing in this Ui container
        sideTabsBarPlaceholder.getChildren().add(new SideTabsBar(this::switchTab).getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Sets up the respective tabs when trip is set.
     */
    public void handleSetTrip() {
        scheduleTabPanel = new ScheduleTabPanel(logic.getScheduleList(), logic.getGuiSettings().getWindowHeight(),
                logic.getGuiSettings().getWindowHeight());
        activityTabPanel = new ActivityTabPanel(logic.getFilteredActivityList());
        accommodationBookingTabPanel = new AccommodationBookingTabPanel(logic.getFilteredAccommodationBookingList());
        transportBookingTabPanel = new TransportBookingTabPanel(logic.getFilteredTransportBookingList());
        packingListTabPanel = new PackingListTabPanel(logic.getFilteredPackingList());
        fixedExpenseTabPanel = new FixedExpenseTabPanel(logic.getFilteredFixedExpenseList(),
                logic.getGuiSettings().getWindowHeight(), logic.getGuiSettings().getWindowWidth());
        switchTab(ScheduleTabPanel.TAB_NAME);
        dashboardPlaceholder.getChildren().add(new DashBoardPanel(logic.getTrip()).getRoot());
    }

    /**
     * Tears down the respective tabs when trip is deleted.
     */
    public void handleDeleteTrip() {
        scheduleTabPanel = new NoTripTabPanel();
        activityTabPanel = new NoTripTabPanel();
        accommodationBookingTabPanel = new NoTripTabPanel();
        transportBookingTabPanel = new NoTripTabPanel();
        packingListTabPanel = new NoTripTabPanel();
        fixedExpenseTabPanel = new NoTripTabPanel();
        switchTab(ScheduleTabPanel.TAB_NAME);
        dashboardPlaceholder.getChildren().add(new DashBoardPanel().getRoot());
    }

    public void handleUpdateTrip() {
        dashboardPlaceholder.getChildren().add(new DashBoardPanel(logic.getTrip()).getRoot());
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    /**
     * Opens the checkstatus window or focuses on it if it's already opened.
     */
    public void handleShowStatus(List<String> status) {
        if (!checkStatusWindow.isShowing()) {
            checkStatusWindow.show(status);
        } else {
            checkStatusWindow.close();
            checkStatusWindow.show(status);
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    /**
     * Switches tab to the specified tab name.
     */
    private void switchTab(String tabName) {
        tabPanelPlaceholder.getChildren().clear();

        switch (tabName) {
        case ScheduleTabPanel.TAB_NAME:
            tabPanelPlaceholder.getChildren().add(scheduleTabPanel.getRoot());
            break;
        case ActivityTabPanel.TAB_NAME:
            tabPanelPlaceholder.getChildren().add(activityTabPanel.getRoot());
            break;
        case AccommodationBookingTabPanel.TAB_NAME:
            tabPanelPlaceholder.getChildren().add(accommodationBookingTabPanel.getRoot());
            break;
        case TransportBookingTabPanel.TAB_NAME:
            tabPanelPlaceholder.getChildren().add(transportBookingTabPanel.getRoot());
            break;
        case PackingListTabPanel.TAB_NAME:
            tabPanelPlaceholder.getChildren().add(packingListTabPanel.getRoot());
            break;
        case FixedExpenseTabPanel.TAB_NAME:
            tabPanelPlaceholder.getChildren().add(fixedExpenseTabPanel.getRoot());
            break;
        default:
            throw new AssertionError("No such tab name: " + tabName);
        }
    }

    /**
     * Executes the command and returns the result.
     *
     * @see Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isCheckStatus()) {
                handleShowStatus(commandResult.getStatusFeedback());
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isSetTrip()) {
                handleSetTrip();
            }

            if (commandResult.isDeleteTrip()) {
                handleDeleteTrip();
            }

            if (commandResult.isEditTrip()) {
                handleUpdateTrip();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
