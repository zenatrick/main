package seedu.address.logic.commands;

import java.util.Optional;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.budget.Budget;

/**
 * Allows the user to edit his or her budget which must already be set.
 */
public class BudgetEditCommand extends Command {

    public static final String MESSAGE_EDIT_BUDGET_SUCCESS = "Edited Budget: %1$s";
    public static final String COMMAND_WORD = "editbudget";
    private final EditBudgetDescriptor editBudgetDescriptor;


    public BudgetEditCommand(EditBudgetDescriptor editBudgetDescriptor) {
        this.editBudgetDescriptor = new EditBudgetDescriptor(editBudgetDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        //TODO: INTEGRATE my model into this thing
        return new CommandResult("Allahu Akbar");
    }


    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditBudgetDescriptor {
        private Budget budget;

        public EditBudgetDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditBudgetDescriptor(EditBudgetDescriptor toCopy) {
            setBudget(toCopy.budget);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(budget);
        }

        public void setBudget(Budget budget) {
            this.budget = budget;
        }

        public Optional<Budget> getBudget() {
            return Optional.of(budget);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditBudgetDescriptor)) {
                return false;
            }

            // state check
            EditBudgetDescriptor e = (EditBudgetDescriptor) other;
            return getBudget().equals(e.getBudget());
        }
    }


}
