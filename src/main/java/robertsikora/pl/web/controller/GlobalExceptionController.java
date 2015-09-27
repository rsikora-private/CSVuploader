package robertsikora.pl.web.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import robertsikora.pl.web.validator.Exception.ValidationException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Robert on 2015-02-27.
 */

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity handleValidationException(ValidationException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity handleConstraintViolationException(DataIntegrityViolationException ex) {
        return new ResponseEntity("SSN must be unique. You attempt to store duplicated data", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleGeneralException(Exception ex) {
        //TO-DO use more professional logger
        Logger.getAnonymousLogger().log(Level.SEVERE, ex.getMessage());
        return new ResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
