package validation;

public class Validator {
    private Validator() {
        /* This utility class should not be instantiated */
    }

    public static final String ACCOUNT_NUMBER = "^[A-Z]{2}-\\d{4}-\\d{4}$";

    public static boolean isValidAccountNumber(String accountNumber){
        return accountNumber.matches(ACCOUNT_NUMBER);
    }
}
