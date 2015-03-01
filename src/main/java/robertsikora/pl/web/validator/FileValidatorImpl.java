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

    @Override
    public void validate(MultipartFile obj, Errors ... errors) {

        if(StringUtils.isEmpty(obj.getOriginalFilename()))
            throw new ValidationException("Please choose file");

        if(obj.getSize() == 0)
            throw new ValidationException("The file is empty");

    }
}
