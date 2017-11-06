package Security;

import java.util.regex.Pattern;

public class Validation {

    /* pattern */
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})");

    private static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$");

    private static final Pattern NAME_PATTERN = Pattern.compile("[A-Za-z0_]+");

    private static final Pattern USER_NAME_PATTERN = Pattern.compile("[A-Za-z0_9]+");

    private static final Pattern COUNTRY_PATTERN = Pattern.compile("[A-Za-z0_]+");

    /* is valid field * */
    public boolean isNameValid(String name) {
        return NAME_PATTERN.matcher(name).matches();
    }

    public boolean isUserNameValid(String userName) {
        return USER_NAME_PATTERN.matcher(userName).matches();
    }

    public boolean isPasswordValid(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    public boolean isEmailValid(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

    public boolean isCountryValid(String country) { return COUNTRY_PATTERN.matcher(country).matches(); }

    public boolean isDateValid(String date) { try { Integer.parseInt(date); return true; } catch (NumberFormatException e) { return false; } }

    public boolean isPhoneValid(String number) { return number.length() > 0; }
}
