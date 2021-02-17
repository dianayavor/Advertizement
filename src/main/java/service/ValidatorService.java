package service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.Serializable;
import java.util.Set;

public class ValidatorService<E extends Serializable> {
    private final static Logger logger = LogManager.getLogger(ValidatorService.class);

    public E validate(E e) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<E>> violations = validator.validate(e);
        for (ConstraintViolation<E> violation : violations) {
            logger.error(violation.getInvalidValue());//getPropertyPath().;//;Message());//todo add exception set
        }
        return e;
    }
}
