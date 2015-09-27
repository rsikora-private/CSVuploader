package robertsikora.pl.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;
import robertsikora.pl.web.validator.Exception.ValidationException;

/**
 * Created by Robert on 2015-02-27.
 */

@Component(value="fileValidator")
public class FileValidatorImpl implements Validator<MultipartFile> {

    private final static String CHOOSE_FILE_MESSAGE = "Please choose file";
    private final static String EMPTY_FILE_MESSAGE = "The file is empty";

    @Override
    public void validate(final MultipartFile obj, final Errors ... errors) {
        if(StringUtils.isEmpty(obj.getOriginalFilename())) {
            throw new ValidationException(CHOOSE_FILE_MESSAGE);
        }
        if(obj.getSize() == 0) {
            throw new ValidationException(EMPTY_FILE_MESSAGE);
        }
    }
}
