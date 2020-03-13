package seedu.address.model.packinglistitem;

/**
 * Represents the boolean if item is packed in the packing list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Check {
    /**
     * The Is check.
     */
    private final boolean isChecked;

    /**
     * Instantiates a new Check.
     */
    public Check() {
        this(false);
    }

    /**
     * Instantiates a new Check with the given boolean.
     */
    public Check(boolean isChecked) {
        this.isChecked = isChecked;
    }
    /**
     * Is check boolean.
     *
     * @return the boolean
     */
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public String toString() {
        return "" + isChecked;
    }
}
