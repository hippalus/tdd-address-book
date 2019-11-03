package com.addressbook.domain.validation;

import com.addressbook.domain.exceptions.BeanValidationException;
import org.springframework.validation.FieldError;

import javax.validation.*;
import java.util.Set;

public class BeanValidator {
    private ValidatorFactory factory;

    public BeanValidator() {
        Configuration<?> config = Validation.byDefaultProvider().configure();
        factory = config.buildValidatorFactory();
    }

    public Validator get() {
        return factory.getValidator();
    }

    public <T> T validate(T object) {

        Set<ConstraintViolation<T>> violations = get().validate(object);
        if (!violations.isEmpty()) {
            BeanValidationException exception = new BeanValidationException();
            violations
                    .stream()
                    .map(violation ->
                            new FieldError(violation.getRootBeanClass().getSimpleName(),
                                    violation.getPropertyPath().toString(),
                                    violation.getMessage()))
                    .forEach(exception::add);
            throw exception;

        }
        return object;
    }

    public void close() {
        factory.close();
    }
}
