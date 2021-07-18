package htc.testproject.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateValidation implements ConstraintValidator<DateValidator, String> {

    private Pattern pattern;
    private Matcher matcher;
    private static final String DATE_PATTERN = "^(0?[1-9]|[12][0-9]|3[01])[.](0?[1-9]|1[012])[.]\\d{4}$";

    @Override
    public boolean isValid(final String date, final ConstraintValidatorContext context) {
        pattern = Pattern.compile(DATE_PATTERN);
        if (date == null) {
            return false;
        }
        matcher = pattern.matcher(date);
        return matcher.matches();
    }

}
