package team.easytravel.testutil.fixedexpense;

import static team.easytravel.logic.parser.CliSyntax.PREFIX_EXPENSE_AMOUNT;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_EXPENSE_CATEGORY;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_EXPENSE_CURRENCY;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_EXPENSE_DESCRIPTION;

import team.easytravel.logic.commands.fixedexpense.AddFixedExpenseCommand;
import team.easytravel.logic.commands.fixedexpense.EditFixedExpenseCommand.EditFixedExpenseDescriptor;
import team.easytravel.model.listmanagers.fixedexpense.FixedExpense;

/**
 * A utility class for FixedExpense.
 */
public class FixedExpenseUtil {

    /**
     * Returns an add command string for adding the {@code fixedExpense}.
     */
    public static String getAddCommand(FixedExpense fixedExpense) {
        return AddFixedExpenseCommand.COMMAND_WORD + " " + getFixedExpenseDetails(fixedExpense);
    }

    /**
     * Returns the part of command string for the given {@code fixedExpense}'s details.
     */
    public static String getFixedExpenseDetails(FixedExpense fixedExpense) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_EXPENSE_AMOUNT + fixedExpense.getAmount().value + " ");
        sb.append(PREFIX_EXPENSE_CATEGORY + fixedExpense.getFixedExpenseCategory().value + " ");
        sb.append(PREFIX_EXPENSE_CURRENCY + "sgd ");
        sb.append(PREFIX_EXPENSE_DESCRIPTION + fixedExpense.getDescription().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditFixedExpenseDescriptor}'s details.
     */
    public static String getEditFixedExpenseDescriptorDetails(EditFixedExpenseDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getAmount().ifPresent(amount -> sb.append(PREFIX_EXPENSE_AMOUNT)
                .append(amount.value).append(" "));
        descriptor.getDescription().ifPresent(description -> sb.append(PREFIX_EXPENSE_DESCRIPTION)
                .append(description.value)
                .append(" "));
        descriptor.getFixedExpenseCategory().ifPresent(category -> sb
                .append(PREFIX_EXPENSE_CATEGORY).append(category.value)
                .append(" ").append(PREFIX_EXPENSE_CURRENCY).append("sgd").append(" "));
        return sb.toString();
    }
}
