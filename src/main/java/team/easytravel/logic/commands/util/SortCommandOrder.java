package team.easytravel.logic.commands.util;

import team.easytravel.logic.parser.exceptions.ParseException;

/**
 * Represents the order that a sort command can sort by.
 */
public enum SortCommandOrder {
    ASCENDING,
    DESCENDING;

    public static final String MESSAGE_CONSTRAINTS = "Order must be \"asc\" or \"ascending\" for ascending order and "
            + "\"des\" or \"descending\" for descending order.";

    /**
     * Parses the given String and returns the corresponding SortCommandOrder.
     */
    public static SortCommandOrder parseOrder(String order) throws ParseException {
        switch (order) {
        case "asc":
        case "ascending":
            return ASCENDING;
        case "des":
        case "descending":
            return DESCENDING;
        default:
            throw new ParseException(MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Returns true if this is an ascending order.
     */
    public boolean isAscending() {
        return equals(ASCENDING);
    }

    /**
     * Returns true if this is an descending order.
     */
    public boolean isDescending() {
        return equals(DESCENDING);
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
