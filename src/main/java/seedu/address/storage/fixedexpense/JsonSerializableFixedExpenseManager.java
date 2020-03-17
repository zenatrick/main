package seedu.address.storage.fixedexpense;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.fixedexpense.FixedExpense;
import seedu.address.model.listmanager.FixedExpenseManager;
import seedu.address.model.listmanager.ReadOnlyFixedExpenseManager;

/**
 * An Immutable FixedExpenseManager that is serializable to JSON format.
 */
@JsonRootName(value = "fixedExpenseManager")
class JsonSerializableFixedExpenseManager {

    public static final String MESSAGE_DUPLICATE_TRANSPORT_BOOKING = "Fixed Expense list contains duplicate "
            + "Fixed Expense(s).";

    private final List<JsonAdaptedFixedExpense> fixedExpenses = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableFixedExpenseManager} with the given fixed expenses.
     */
    @JsonCreator
    public JsonSerializableFixedExpenseManager(
            @JsonProperty("fixedExpenses") List<JsonAdaptedFixedExpense> fixedExpenses) {
        this.fixedExpenses.addAll(fixedExpenses);
    }

    /**
     * Converts a given {@code ReadOnlyFixedExpenseManager} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableFixedExpenseManager}.
     */
    public JsonSerializableFixedExpenseManager(ReadOnlyFixedExpenseManager source) {
        fixedExpenses.addAll(
                source.getFixedExpenseList()
                .stream()
                .map(JsonAdaptedFixedExpense::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this JsonSerializableFixedExpenseManager into the model's {@code FixedExpenseManager} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FixedExpenseManager toModelType() throws IllegalValueException {
        FixedExpenseManager fixedExpenseManager = new FixedExpenseManager();
        for (JsonAdaptedFixedExpense jsonAdaptedFixedExpense : fixedExpenses) {
            FixedExpense fixedExpense = jsonAdaptedFixedExpense.toModelType();
            if (fixedExpenseManager.hasFixedExpense(fixedExpense)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TRANSPORT_BOOKING);
            }
            fixedExpenseManager.addFixedExpense(fixedExpense);
        }
        return fixedExpenseManager;
    }

}
