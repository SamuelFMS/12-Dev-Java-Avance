package validation;

public class Validator {
    public static final String ACCOUNT_NUMBER = "^[A-Z]{2}-\\d{4}-\\d{4}$";
    public static final String DECIMAL = "^\\d{1,15}(.\\d{1,2})?$";

    private Validator() {
        /* This utility class should not be instantiated */
    }

    public static boolean isValidAccountNumber(String accountNumber) {
        return accountNumber.matches(ACCOUNT_NUMBER);
    }

    public static boolean isAValidDecimal(String decimal) {
        return decimal.matches(DECIMAL);
    }
}
