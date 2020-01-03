package com.addressbook.domain.exceptions;

import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BeanValidationException extends RuntimeException {

    private final List<FieldError> fieldErrors = new ArrayList<>();

    public BeanValidationException add(FieldError fieldError) {
        fieldErrors.add(fieldError);
        return this;
    }

    public List<FieldError> getFieldErrors() {
        return Collections.unmodifiableList(fieldErrors);
    }
}
