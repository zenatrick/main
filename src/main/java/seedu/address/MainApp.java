package seedu.address;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.address.commons.core.Config;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Version;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.ConfigUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.listmanagers.AccommodationBookingManager;
import seedu.address.model.listmanagers.ActivityManager;
import seedu.address.model.listmanagers.FixedExpenseManager;
import seedu.address.model.listmanagers.PackingListManager;
import seedu.address.model.listmanagers.ReadOnlyAccommodationBookingManager;
import seedu.address.model.listmanagers.ReadOnlyActivityManager;
import seedu.address.model.listmanagers.ReadOnlyFixedExpenseManager;
import seedu.address.model.listmanagers.ReadOnlyPackingListManager;
import seedu.address.model.listmanagers.ReadOnlyTransportBookingManager;
import seedu.address.model.listmanagers.ReadOnlyUserPrefs;
import seedu.address.model.listmanagers.TransportBookingManager;
import seedu.address.model.listmanagers.UserPrefs;
import seedu.address.model.util.sampledata.SampleDataUtil;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.storage.accommodationbooking.AccommodationBookingStorage;
import seedu.address.storage.accommodationbooking.JsonAccommodationBookingStorage;
import seedu.address.storage.activity.ActivityManagerStorage;
import seedu.address.storage.activity.JsonActivityManagerStorage;
import seedu.address.storage.fixedexpense.FixedExpenseStorage;
import seedu.address.storage.fixedexpense.JsonFixedExpenseStorage;
import seedu.address.storage.packinglist.JsonPackingListStorage;
import seedu.address.storage.packinglist.PackingListStorage;
import seedu.address.storage.transportbooking.JsonTransportBookingStorage;
import seedu.address.storage.transportbooking.TransportBookingStorage;
import seedu.address.ui.Ui;
import seedu.address.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    /**
     * The constant VERSION.
     */
    public static final Version VERSION = new Version(0, 6, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    /**
     * The Ui.
     */
    protected Ui ui;
    /**
     * The Logic.
     */
    protected Logic logic;
    /**
     * The Storage.
     */
    protected Storage storage;
    /**
     * The Model.
     */
    protected Model model;
    /**
     * The Config.
     */
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing E.T. ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        TransportBookingStorage transportBookingStorage =
                new JsonTransportBookingStorage((userPrefs.getTransportBookingStorageFilePath()));
        FixedExpenseStorage fixedExpenseStorage =
                new JsonFixedExpenseStorage(userPrefs.getFixedExpenseStorageFilePath());
        ActivityManagerStorage activityManagerStorage =
                new JsonActivityManagerStorage(userPrefs.getActivityManagerStorageFilePath());
        AccommodationBookingStorage accommodationBookingStorage =
                new JsonAccommodationBookingStorage((userPrefs.getAccommodationBookingStorageFilePath()));
        PackingListStorage packingListStorage = new JsonPackingListStorage(userPrefs.getPackingListStorageFilePath());
        AddressBookStorage addressBookStorage = new JsonAddressBookStorage(userPrefs.getAddressBookFilePath());
        storage = new StorageManager(addressBookStorage,
                transportBookingStorage,
                fixedExpenseStorage,
                activityManagerStorage,
                accommodationBookingStorage,
                packingListStorage,
                userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s address book and {@code userPrefs}. <br>
     * The data from the sample address book will be used instead if {@code storage}'s address book is not found,
     * or an empty address book will be used instead if errors occur when reading {@code storage}'s address book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyAddressBook> addressBookOptional;
        ReadOnlyAddressBook initialData;
        try {
            addressBookOptional = storage.readAddressBook();
            if (addressBookOptional.isEmpty()) {
                logger.info("Data file not found. Will be starting with a sample AddressBook");
            }
            initialData = addressBookOptional.orElseGet(SampleDataUtil::getSampleAddressBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty AddressBook");
            initialData = new AddressBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
            initialData = new AddressBook();
        }

        ReadOnlyTransportBookingManager transportBookingManager = initTransportBookingManager(storage);
        ReadOnlyFixedExpenseManager fixedExpenseManager = initFixedExpenseManager(storage);
        ReadOnlyActivityManager activityManager = initActivityManager(storage);
        ReadOnlyAccommodationBookingManager accommodationBookingManager = initAccommodationBookingManager(storage);
        ReadOnlyPackingListManager packingListManager = initPackingListManager(storage);

        return new ModelManager(initialData, transportBookingManager, fixedExpenseManager, packingListManager,
                activityManager, accommodationBookingManager, userPrefs);
    }

    /**
     * Returns a {@code ReadOnlyManager} with the data from {@code storage}'s transport bookings.
     * The data from the sample transport bookings will be used instead
     * if {@code storage}'s transport booking manager is not found,
     * or an empty transport booking manager will be used instead if errors
     * occur when reading {@code storage}'s transport booking manager.
     */
    private ReadOnlyTransportBookingManager initTransportBookingManager(Storage storage) {
        ReadOnlyTransportBookingManager transportBookingManager;
        try {
            Optional<ReadOnlyTransportBookingManager> transportBookingManagerOptional = storage.readTransportBookings();
            if (transportBookingManagerOptional.isEmpty()) {
                logger.info("Data file not found. Will be starting with a sample TransportBookingManager.");
            }
            transportBookingManager =
                    transportBookingManagerOptional.orElseGet(SampleDataUtil::getSampleTransportBookingManager);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty"
                    + "TransportBookingManager.");
            transportBookingManager = new TransportBookingManager();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty "
                    + "TransportBookingManager.");
            transportBookingManager = new TransportBookingManager();
        }

        try {
            storage.saveTransportBookings(transportBookingManager);
            logger.info("Saving initial data of TransportBookingManager.");
        } catch (IOException e) {
            logger.warning("Problem while saving to the file.");
        }

        return transportBookingManager;
    }

    /**
     * Returns a {@code ReadOnlyManager} with the data from {@code storage}'s fixed expenses.
     * The data from the sample fixed expenses will be used instead
     * if {@code storage}'s fixed expense manager is not found,
     * or an empty fixed expense manager will be used instead
     * if errors occur when reading {@code storage}'s fixed expense manager.
     */
    private ReadOnlyFixedExpenseManager initFixedExpenseManager(Storage storage) {
        ReadOnlyFixedExpenseManager fixedExpenseManager;
        try {
            Optional<ReadOnlyFixedExpenseManager> fixedExpenseManagerOptional = storage.readFixedExpenses();
            if (fixedExpenseManagerOptional.isEmpty()) {
                logger.info("Data file not found. Will be starting with a sample FixedExpenseManager.");
            }
            fixedExpenseManager = fixedExpenseManagerOptional.orElseGet(SampleDataUtil::getSampleFixedExpenseManager);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty"
                    + "FixedExpenseManager.");
            fixedExpenseManager = new FixedExpenseManager();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty "
                    + "FixedExpenseManager.");
            fixedExpenseManager = new FixedExpenseManager();
        }

        try {
            storage.saveFixedExpenses(fixedExpenseManager);
            logger.info("Saving initial data of FixedExpenseManager.");
        } catch (IOException e) {
            logger.warning("Problem while saving to the file.");
        }

        return fixedExpenseManager;
    }

    /**
     * Returns a {@code ReadOnlyManager} with the data from {@code storage}'s activities.
     * The data from the sample activities will be used instead if {@code storage}'s activities manager is not found,
     * or an empty activity manager will be used instead if errors occur when reading {@code storage}'s
     * activity manager.
     */
    private ReadOnlyActivityManager initActivityManager(Storage storage) {
        ReadOnlyActivityManager activityManager;
        try {
            Optional<ReadOnlyActivityManager> activityManagerOptional = storage.readActivityManager();
            if (activityManagerOptional.isEmpty()) {
                logger.info("Data file not found. Will be starting with a sample ActivityManager.");
            }
            activityManager =
                    activityManagerOptional.orElseGet(SampleDataUtil::getSampleActivityManager);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty"
                    + "ActivityManager.");
            activityManager = new ActivityManager();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty "
                    + "ActivityManager.");
            activityManager = new ActivityManager();
        }

        try {
            storage.saveActivityManager(activityManager);
            logger.info("Saving initial data of ActivityManager.");
        } catch (IOException e) {
            logger.warning("Problem while saving to the file.");
        }

        return activityManager;
    }

    /**
     * Returns a {@code ReadOnlyManager} with the data from {@code storage}'s accommodation bookings.
     * The data from the sample accommodation bookings will be used instead
     * if {@code storage}'s accommodation booking manager is not found,
     * or an empty accommodation booking manager will be used instead if errors occur when
     * reading {@code storage}'s accommodation booking manager.
     */
    private ReadOnlyAccommodationBookingManager initAccommodationBookingManager(Storage storage) {
        ReadOnlyAccommodationBookingManager accommodationBookingManager;
        try {
            Optional<ReadOnlyAccommodationBookingManager> accommodationBookingManagerOptional =
                    storage.readAccommodationBookings();
            if (accommodationBookingManagerOptional.isEmpty()) {
                logger.info("Data file not found. Will be starting with a sample AccommodationBookingManager.");
            }
            accommodationBookingManager =
                    accommodationBookingManagerOptional.orElseGet(SampleDataUtil::getSampleAccommodationBookingManager);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty"
                    + "AccommodationBookingManager.");
            accommodationBookingManager = new AccommodationBookingManager();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty "
                    + "AccommodationBookingManager.");
            accommodationBookingManager = new AccommodationBookingManager();
        }

        try {
            storage.saveAccommodationBookings(accommodationBookingManager);
            logger.info("Saving initial data of AccommodationBookingManager.");
        } catch (IOException e) {
            logger.warning("Problem while saving to the file.");
        }

        return accommodationBookingManager;
    }

    /**
     * Returns a {@code ReadOnlyManager} with the data from {@code storage}'s packing list.
     * The data from the sample packing list will be used instead if {@code storage}'s packing list
     * is not found, or an empty packing list will be used instead if errors occur when
     * reading {@code storage}'s packing list.
     */
    private ReadOnlyPackingListManager initPackingListManager(Storage storage) {
        ReadOnlyPackingListManager packingListManager;
        try {
            Optional<ReadOnlyPackingListManager> packingListManagerOptional = storage.readPackingList();
            if (packingListManagerOptional.isEmpty()) {
                logger.info("Data file not found. Will be starting with a sample PackingListManager.");
            }
            packingListManager =
                    packingListManagerOptional.orElseGet(SampleDataUtil::getSamplePackingListManager);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty"
                    + "PackingListManager.");
            packingListManager = new PackingListManager();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty "
                    + "PackingListManager.");
            packingListManager = new PackingListManager();
        }

        try {
            storage.saveItems(packingListManager);
            logger.info("Saving initial data of PackingListManager.");
        } catch (IOException e) {
            logger.warning("Problem while saving to the file.");
        }

        return packingListManager;
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     *
     * @param configFilePath the config file path
     * @return the config
     */
    protected Config initConfig(Path configFilePath) {
        Path configFilePathUsed = Config.DEFAULT_CONFIG_FILE;
        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        Config initializedConfig;
        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            logger.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
                    + "Using default config properties");
            initializedConfig = new Config();
        }

        // Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
     *
     * @param storage the storage
     * @return the user prefs
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataConversionException e) {
            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefs();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
            initializedPrefs = new UserPrefs();
        }

        // Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting E.T. " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping E.T. ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
