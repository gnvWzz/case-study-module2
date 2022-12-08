package CustomerPackage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateIdentityCardNumber {
    private static final String IDENTITY_CARD_NUMBER_REGEX = "^0\\d{2}[0123]\\d{8}$";

    public ValidateIdentityCardNumber() {
    }

    public boolean validate(String regex) {
        Pattern pattern = Pattern.compile(IDENTITY_CARD_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }
}
