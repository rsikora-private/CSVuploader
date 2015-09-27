package robertsikora.pl.web.validator;

import org.springframework.validation.Errors;

/**
 * Created by Robert on 2015-02-27.
 */
public interface Validator <T> {
    void validate(T obj, Errors ... errors);
}
