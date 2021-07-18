package htc.testproject.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocumentFieldsValidation implements ConstraintValidator<DocumentFieldsValidator, String> {

    private Pattern pattern;
    private Matcher matcher;
    private static final String FIELDS_PATTERN = "^[0-9]+$";

    @Override
    public boolean isValid(final String field, final ConstraintValidatorContext context) {
        pattern = Pattern.compile(FIELDS_PATTERN);
        if (field == null) {
            return true;
        }
        matcher = pattern.matcher(field);
        return matcher.matches();
    }

}
