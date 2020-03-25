package seedu.address.logic.commands.fixedexpense;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.listmanagers.fixedexpense.Amount;
import seedu.address.model.listmanagers.fixedexpense.Description;
import seedu.address.model.listmanagers.fixedexpense.FixedExpense;
import seedu.address.model.listmanagers.fixedexpense.FixedExpenseCategory;

/**
 * Edits the details of an existing fixed expense.
 */
public class EditFixedExpenseCommand extends Command {

    public static final String COMMAND_WORD = "editexpense";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the fixed expense identified "
            + "by the index number used in the displayed list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_AMOUNT + "AMOUNT] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_CATEGORY + "EMAIL] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_AMOUNT + "3000 "
            + PREFIX_DESCRIPTION + "SQ Flight";

    public static final String MESSAGE_EDIT_FIXEDEXPENSE_SUCCESS = "Edited Fixed Expense: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_FIXED_EXPENSE = "This Fixed Expense already exists in the "
            + "address book.";

    private final Index index;
    private final EditFixedExpenseDescriptor editFixedExpenseDescriptor;

    /**
     * @param index                      of the person in the filtered person list to edit
     * @param editFixedExpenseDescriptor details to edit the fixed expense with
     */
    public EditFixedExpenseCommand(Index index, EditFixedExpenseDescriptor editFixedExpenseDescriptor) {
        requireNonNull(index);
        requireNonNull(editFixedExpenseDescriptor);

        this.index = index;
        this.editFixedExpenseDescriptor = new EditFixedExpenseDescriptor(editFixedExpenseDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<FixedExpense> lastShownList = model.getFilteredFixedExpenseList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FIXEDEXPENSE_DISPLAYED_INDEX);
        }

        FixedExpense fixedExpenseToEdit = lastShownList.get(index.getZeroBased());
        FixedExpense editedExpense = createEditedFixedExpense(fixedExpenseToEdit, editFixedExpenseDescriptor);

        if (!fixedExpenseToEdit.isSame(editedExpense) && model.hasFixedExpense(editedExpense)) {
            throw new CommandException(MESSAGE_DUPLICATE_FIXED_EXPENSE);
        }

        model.setFixedExpense(fixedExpenseToEdit, editedExpense);
        model.updateFilteredFixedExpenseList(Model.PREDICATE_SHOW_ALL_FIXED_EXPENSES);
        return new CommandResult(String.format(MESSAGE_EDIT_FIXEDEXPENSE_SUCCESS, editedExpense));
    }

    /**
     * Creates and returns a {@code FixedExpense} with the details of {@code fixedExpenseToEdit}
     * edited with {@code editFixedExpenseDescriptor}
     */
    private static FixedExpense createEditedFixedExpense(FixedExpense fixedExpenseToEdit,
                                                         EditFixedExpenseDescriptor editFixedExpenseDescriptor) {
        assert fixedExpenseToEdit != null;

        Amount updatedAmount = editFixedExpenseDescriptor.getAmount().orElse(fixedExpenseToEdit.getAmount());
        Description updatedDescription = editFixedExpenseDescriptor
                .getDescription().orElse(fixedExpenseToEdit.getDescription());
        FixedExpenseCategory updatedFixedExpenseCategory = editFixedExpenseDescriptor.getFixedExpenseCategory()
                .orElse(fixedExpenseToEdit.getFixedExpenseCategory());

        return new FixedExpense(updatedAmount, updatedDescription, updatedFixedExpenseCategory);
    }

    /**
     * Stores the details to edit the fixed expense with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditFixedExpenseDescriptor {
        private Amount amount;
        private Description description;
        private FixedExpenseCategory fixedExpenseCategory;

        public EditFixedExpenseDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditFixedExpenseDescriptor(EditFixedExpenseDescriptor toCopy) {
            setAmount(toCopy.amount);
            setDescription(toCopy.description);
            setFixedExpenseCategory(toCopy.fixedExpenseCategory);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(amount, description, fixedExpenseCategory);
        }

        public void setAmount(Amount amount) {
            this.amount = amount;
        }

        public Optional<Amount> getAmount() {
            return Optional.ofNullable(amount);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setFixedExpenseCategory(FixedExpenseCategory fixedExpenseCategory) {
            this.fixedExpenseCategory = fixedExpenseCategory;
        }

        public Optional<FixedExpenseCategory> getFixedExpenseCategory() {
            return Optional.ofNullable(fixedExpenseCategory);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditFixedExpenseDescriptor)) {
                return false;
            }

            // state check
            EditFixedExpenseDescriptor e = (EditFixedExpenseDescriptor) other;

            return getAmount().equals(e.getAmount())
                    && getDescription().equals(e.getDescription())
                    && getFixedExpenseCategory().equals(e.getFixedExpenseCategory());
        }
    }
}
