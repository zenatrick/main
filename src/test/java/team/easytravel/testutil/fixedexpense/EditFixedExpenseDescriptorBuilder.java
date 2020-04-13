package team.easytravel.testutil.fixedexpense;

import team.easytravel.logic.commands.fixedexpense.EditFixedExpenseCommand;
import team.easytravel.logic.commands.fixedexpense.EditFixedExpenseCommand.EditFixedExpenseDescriptor;
import team.easytravel.model.listmanagers.fixedexpense.Amount;
import team.easytravel.model.listmanagers.fixedexpense.Description;
import team.easytravel.model.listmanagers.fixedexpense.FixedExpense;
import team.easytravel.model.listmanagers.fixedexpense.FixedExpenseCategory;


/**
 * A utility class to help with building EditFixedExpenseDescriptor objects.
 */
public class EditFixedExpenseDescriptorBuilder {

    private EditFixedExpenseDescriptor descriptor;

    public EditFixedExpenseDescriptorBuilder() {
        descriptor = new EditFixedExpenseDescriptor();
    }

    /**
     * Returns an {@code EditFixedExpenseDescriptor} with fields containing {@code fixed expense}'s details
     */
    public EditFixedExpenseDescriptorBuilder(FixedExpense fixedExpense) {
        descriptor = new EditFixedExpenseDescriptor();
        descriptor.setAmount(fixedExpense.getAmount());
        descriptor.setDescription(fixedExpense.getDescription());
        descriptor.setFixedExpenseCategory(fixedExpense.getFixedExpenseCategory());
    }

    /**
     * Sets the {@code amount} of the {@code EditFixedExpenseDescriptor} that we are building.
     */
    public EditFixedExpenseDescriptorBuilder withAmount(String amount) {
        descriptor.setAmount(new Amount(amount));
        return this;
    }

    /**
     * Sets the {@code description} of the {@code EditFixedExpenseDescriptor} that we are building.
     */
    public EditFixedExpenseDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code FixedExpenseCategory} of the {@code EditFixedExpenseDescriptor} that we are building.
     */
    public EditFixedExpenseDescriptorBuilder withFixedExpenseCategory(String fixedExpenseCategory) {
        descriptor.setFixedExpenseCategory(new FixedExpenseCategory(fixedExpenseCategory));
        return this;
    }


    public EditFixedExpenseCommand.EditFixedExpenseDescriptor build() {
        return descriptor;
    }
}


