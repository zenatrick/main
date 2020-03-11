package seedu.address.commons.core.index;

/**
 * Converts the String input the user put into a Integer
 */
public class Amount {

    private String amountInput;

    public Amount(String amountInput) {
        this.amountInput = amountInput;
    }

    /**
     * Converts the user entered Input to Output.
     * @throws IllegalArgumentException
     */
    private int convertInputToOutput(String s) throws IllegalArgumentException {
        try {
            int convertedInput = Integer.parseInt(s);
            System.out.println(convertedInput);
            if (convertedInput < 0) {
                throw new IllegalArgumentException("You have entered an invalid amount");
            } else {
                return convertedInput;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("You have entered an invalid amount");
        }
    }

    /**
     * Gets the amount, converted to integer.
     * @return amountOutput
     */
    public int getAmountOutput() {
        return convertInputToOutput(amountInput);
    }
}
