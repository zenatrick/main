package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.budget.FixedCost;

/**
 * Represents the in-memory FixedCost model of the address book data.
 */

public class FixedCostModelManager implements FixedCostModel {

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final FixedCostBook fixedCostBook;
    private final UserPrefs userPrefs;
    private final FilteredList<FixedCost> filteredFixedCosts;

    public FixedCostModelManager(ReadOnlyFixedCostBook fixedCostBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(fixedCostBook, userPrefs);

        logger.fine("Initializing with address book: " + fixedCostBook + " and user prefs " + userPrefs);

        this.fixedCostBook = new FixedCostBook(fixedCostBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredFixedCosts = new FilteredList<FixedCost>(this.fixedCostBook.getFixedCostList());
    }

    public FixedCostModelManager() {
        this(new FixedCostBook(), new UserPrefs());
    }
    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    // Stays as addressbook else you might as well change the whole address book sua.
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path fixedCostBookFilePath) {
        requireNonNull(fixedCostBookFilePath);
        userPrefs.setAddressBookFilePath(fixedCostBookFilePath);
    }

    //======== FixedCostBook================================================//
    // Believe that it will be merged under the big umbrella of E.T//

    @Override
    public void setFixedCostBook(ReadOnlyFixedCostBook addressBook) {
        this.fixedCostBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyFixedCostBook getFixedCostBook() {
        return fixedCostBook;
    }

    @Override
    public boolean hasFixedCost(FixedCost fixedCost) {
        requireNonNull(fixedCost);
        return fixedCostBook.hasFixedCost(fixedCost);
    }

    @Override
    public void deleteFixedCost(FixedCost target) {
        fixedCostBook.removeFixedCost(target);
    }

    @Override
    public void addFixedCost(FixedCost fixedCost) {
        fixedCostBook.addPerson(fixedCost);
        updateFilteredFixedCostList(PREDICATE_SHOW_ALL_FIXED_COST);
    }

    @Override
    public void setFixedCost(FixedCost target, FixedCost editedFixedCost) {
        requireAllNonNull(target, editedFixedCost);

        fixedCostBook.setFixedCost(target, editedFixedCost);
    }


    //============== Filtered FixedCost List Accessors =====================//

    /**
     * Returns an unmodifiable view of the list of {@code FixedCost} backed by the internal list of
     * {@code versionedAddressBook}
     */

    @Override
    public ObservableList<FixedCost> getFilteredFixedCostList() {
        return filteredFixedCosts;
    }

    @Override
    public void updateFilteredFixedCostList(Predicate<FixedCost> predicate) {
        requireNonNull(predicate);
        filteredFixedCosts.setPredicate(predicate);
    }

}
