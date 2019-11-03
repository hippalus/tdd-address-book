package com.addressbook.domain.builder;

import com.addressbook.domain.validation.BeanValidator;

public interface IValidatableBuilder<T> extends IBuilder<T> {

    default T validateAndGet(BeanValidator validator) {
        return validator.validate(get());
    }
}
