package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.fixedexpense.FixedExpense;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PackingListCard extends UiPart<Region> {

    private static final String FXML = "FixedExpenseCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final FixedExpense fixedExpense;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label amount;
    @FXML
    private Label description;
    @FXML
    private Label category;
    @FXML
    private FlowPane tags;

    public PackingListCard(FixedExpense fixedExpense, int displayedIndex) {
        super(FXML);
        this.fixedExpense = fixedExpense;
        id.setText(displayedIndex + ". ");
        name.setText(fixedExpense.getDescription().toString());
        amount.setText("Amount: $" + fixedExpense.getAmount().toString());
        category.setText("Category: " + fixedExpense.getCategory().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PackingListCard)) {
            return false;
        }

        // state check
        PackingListCard card = (PackingListCard) other;
        return id.getText().equals(card.id.getText())
                && fixedExpense.equals(card.fixedExpense);
    }
}
