package robertsikora.pl.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import robertsikora.pl.core.model.Worker;
import robertsikora.pl.web.validator.Exception.ValidationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Robert on 2015-02-27.
 */

@Component(value="workerValidator")
public class WorkerValidatorImpl implements Validator<Worker> {
    @Override
    public void validate(Worker obj, Errors... errors) {

        StringBuilder str = new StringBuilder();

        Set<ConstraintViolation<Worker>> errorSet = Validation.buildDefaultValidatorFactory().getValidator().validate(obj);
        Iterator<ConstraintViolation<Worker>> iter = errorSet.iterator();

        while (iter.hasNext()){
            ConstraintViolation<Worker> constraintViolation = iter.next();
            str.append(String.format("Wrong worker data. Field '%s' should be: '%s' <br><br>",
                    constraintViolation.getPropertyPath(), constraintViolation.getMessage()));
        }

        if (str.length() >0)
            throw new ValidationException(str.toString());

    }
}
