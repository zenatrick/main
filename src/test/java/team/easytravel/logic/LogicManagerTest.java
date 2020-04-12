//package team.easytravel.logic;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//import java.io.IOException;
//import java.nio.file.Path;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.io.TempDir;
//
//
//import team.easytravel.logic.Logic;
//import team.easytravel.logic.LogicManager;
//import team.easytravel.logic.commands.CommandResult;
//import team.easytravel.logic.commands.exceptions.CommandException;
//import team.easytravel.model.Model;
//import team.easytravel.model.ModelManager;
//import team.easytravel.model.listmanagers.AccommodationBookingManager;
//import team.easytravel.model.listmanagers.ActivityManager;
//import team.easytravel.model.listmanagers.FixedExpenseManager;
//import team.easytravel.model.listmanagers.PackingListManager;
//import team.easytravel.model.listmanagers.TransportBookingManager;
//import team.easytravel.model.trip.TripManager;
//import team.easytravel.model.userprefs.UserPrefs;
//import team.easytravel.storage.StorageManager;
//import team.easytravel.storage.accommodationbooking.JsonAccommodationBookingStorage;
//import team.easytravel.storage.activity.JsonActivityStorage;
//import team.easytravel.storage.fixedexpense.JsonFixedExpenseStorage;
//import team.easytravel.storage.packinglist.JsonPackingListStorage;
//import team.easytravel.storage.transportbooking.JsonTransportBookingStorage;
//import team.easytravel.storage.trip.JsonTripManagerStorage;
//import team.easytravel.storage.userprefs.JsonUserPrefsStorage;
//
//public class LogicManagerTest {
//    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");
//
//    @TempDir
//    public Path temporaryFolder;
//
//    private Model model = new ModelManager(new TransportBookingManager(), new FixedExpenseManager(),
//            new PackingListManager(),
//            new ActivityManager(), new AccommodationBookingManager(), new TripManager(), new UserPrefs());
//    private Logic logic;
//
//    @BeforeEach
//    public void setUp() {
//        JsonTripManagerStorage tripManagerStorage =
//                new JsonTripManagerStorage(temporaryFolder.resolve("trip.json"));
//        JsonAccommodationBookingStorage accommodationBookingStorage =
//                new JsonAccommodationBookingStorage(temporaryFolder.resolve("accommodation.json"));
//        JsonFixedExpenseStorage fixedExpenseStorage =
//                new JsonFixedExpenseStorage(temporaryFolder.resolve("expense.json"));
//        JsonTransportBookingStorage transportBookingStorage =
//                new JsonTransportBookingStorage(temporaryFolder.resolve("transportation.json"));
//        JsonPackingListStorage packingListStorage =
//                new JsonPackingListStorage(temporaryFolder.resolve("packinglist.json"));
//        JsonActivityStorage activityStorage =
//                new JsonActivityStorage(temporaryFolder.resolve("activity.json"));
//        JsonUserPrefsStorage userPrefsStorage =
//                new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
//
//        StorageManager storage = new StorageManager(transportBookingStorage,
//                fixedExpenseStorage,
//                activityStorage,
//                accommodationBookingStorage,
//                packingListStorage, tripManagerStorage,
//                userPrefsStorage);
//
//        logic = new LogicManager(model, storage);
//    }
//
//    @Test
//    public void execute_invalidCommandFormat_throwsParseException() {
//        String invalidCommand = "uicfhmowqewca";
//        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
//    }
//
//    @Test
//    public void execute_commandExecutionError_throwsCommandException() {
//        String deleteCommand = "delete 9";
//        assertCommandException(deleteCommand, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
//    }
//
//    @Test
//    public void execute_validCommand_success() throws Exception {
//        String listCommand = ListCommand.COMMAND_WORD;
//        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
//    }
//
//    @Test
//    public void execute_storageThrowsIoException_throwsCommandException() {
//        // Setup LogicManager with JsonAddressBookIoExceptionThrowingStub
//        JsonAddressBookStorage addressBookStorage =
//                new JsonAddressBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionAddressBook.json"));
//        JsonUserPrefsStorage userPrefsStorage =
//                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
//        //TODO Temporary backstop
//        JsonTransportBookingStorage transportBookingStorage =
//                new JsonTransportBookingStorage(temporaryFolder.resolve("ioExceptionTransportBooking.json"));
//        JsonFixedExpenseStorage fixedExpenseStorage =
//                new JsonFixedExpenseStorage(temporaryFolder.resolve("ioExceptionFixedExpense.json"));
//        JsonActivityManagerStorage activityManagerStorage =
//                new JsonActivityManagerStorage(temporaryFolder.resolve("ioExceptionActivity.json"));
//        JsonAccommodationBookingStorage accommodationBookingStorage =
//                new JsonAccommodationBookingStorage(temporaryFolder.resolve("ioExceptionAccommodationBooking.json"));
//        JsonPackingListStorage packingListStorage =
//                new JsonPackingListStorage(temporaryFolder.resolve("ioExceptionPackingListStorage.json"));
//        JsonTripManagerStorage tripStorage = new JsonTripManagerStorage(temporaryFolder.resolve(
//                "ioExceptionTripStorage.json"));
//        StorageManager storage = new StorageManager(addressBookStorage,
//                transportBookingStorage,
//                fixedExpenseStorage,
//                activityManagerStorage,
//                accommodationBookingStorage,
//                packingListStorage, tripStorage,
//                userPrefsStorage);
//
//        logic = new LogicManager(model, storage);
//
//        // Execute add command
//        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
//                + ADDRESS_DESC_AMY;
//        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
//        ModelManager expectedModel = new ModelManager();
//        expectedModel.addPerson(expectedPerson);
//        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
//        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
//        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredPersonList().remove(0));
//    }
//
//    /**
//     * Executes the command and confirms that
//     * - no exceptions are thrown <br>
//     * - the feedback message is equal to {@code expectedMessage} <br>
//     * - the internal model manager state is the same as that in {@code expectedModel} <br>
//     *
//     * @see #assertCommandFailure(String, Class, String, Model)
//     */
//    private void assertCommandSuccess(String inputCommand, String expectedMessage,
//                                      Model expectedModel) throws CommandException, ParseException {
//        CommandResult result = logic.execute(inputCommand);
//        assertEquals(expectedMessage, result.getFeedbackToUser());
//        assertEquals(expectedModel, model);
//    }
//
//    /**
//     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
//     *
//     * @see #assertCommandFailure(String, Class, String, Model)
//     */
//    private void assertParseException(String inputCommand, String expectedMessage) {
//        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
//    }
//
//    /**
//     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
//     *
//     * @see #assertCommandFailure(String, Class, String, Model)
//     */
//    private void assertCommandException(String inputCommand, String expectedMessage) {
//        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
//    }
//
//    /**
//     * Executes the command, confirms that the exception is thrown and that the result message is correct.
//     *
//     * @see #assertCommandFailure(String, Class, String, Model)
//     */
//    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
//                                      String expectedMessage) {
//        Model expectedModel = new ModelManager(model.getAddressBook(),
//                model.getTransportBookingManager(), model.getFixedExpenseManager(),
//                model.getPackingListManager(), model.getActivityManager(),
//                model.getAccommodationBookingManager(), new TripManager(), model.getUserPrefs());
//
//        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
//    }
//
//    /**
//     * Executes the command and confirms that
//     * - the {@code expectedException} is thrown <br>
//     * - the resulting error message is equal to {@code expectedMessage} <br>
//     * - the internal model manager state is the same as that in {@code expectedModel} <br>
//     *
//     * @see #assertCommandSuccess(String, String, Model)
//     */
//    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
//                                      String expectedMessage, Model expectedModel) {
//        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
//        assertEquals(expectedModel, model);
//    }
//
//    /**
//     * A stub class to throw an {@code IOException} when the save method is called.
//     */
//    private static class JsonAddressBookIoExceptionThrowingStub extends JsonAddressBookStorage {
//        private JsonAddressBookIoExceptionThrowingStub(Path filePath) {
//            super(filePath);
//        }
//
//        @Override
//        public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
//            throw DUMMY_IO_EXCEPTION;
//        }
//    }
//}
