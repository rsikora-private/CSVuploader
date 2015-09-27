package robertsikora.pl.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import robertsikora.pl.web.validator.Exception.ValidationException;

/**
 * Created by Robert on 2015-02-27.
 */

@Component(value="schemaValidator")
public class FileSchemaValidatorImpl implements Validator<String>{

    private final static String[] SCHEMA = {"First Name", "Last Name", "SSN", "DOB", "E-mail"};
    private final static String MESSAGE = "Wrong file schema. Should be '%s' whilst there is '%s' </br>";

    @Override
    public void validate(final String obj, final Errors... errors) {
        final StringBuilder str = new StringBuilder();
        final String [] schema = obj.split(",");
        for(int i = 0; i< SCHEMA.length; i++){
            if(!SCHEMA[i].equals(schema[i])) {
                str.append(String.format(MESSAGE, SCHEMA[i], schema[i]));
            }
        }
        if(str.length() >0) {
            throw new ValidationException(str.toString());
        }
    }
}
