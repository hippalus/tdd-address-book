package com.addressbook.domain.builder;

import com.addressbook.domain.exceptions.PropertyRequiredException;
import com.addressbook.domain.model.Country;

import static com.addressbook.domain.constants.DomainConstants.*;
import static java.util.Objects.isNull;

public class CountryBuilder {
    private Integer id;
    private String name;
    private String code;
    private String dialCode;

    public Country get() {
        checkModelProperties();
        return new Country(id, name, code, dialCode);
    }

    private void checkModelProperties() {
        if (isNull(this.name)) throw new PropertyRequiredException(ENTITY_COUNTRY, "name");
        if (isNull(this.code)) throw new PropertyRequiredException(ENTITY_COUNTRY, "code");
        if (isNull(this.dialCode)) throw new PropertyRequiredException(ENTITY_COUNTRY, "dialCode");

    }

    public CountryBuilder withId(Integer id) {
        this.id = id;
        return this;

    }

    public CountryBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CountryBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public CountryBuilder withDialCode(String dialCode) {
        this.dialCode = dialCode;
        return this;
    }
}
