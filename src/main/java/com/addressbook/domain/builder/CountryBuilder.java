package com.addressbook.domain.builder;

import com.addressbook.domain.model.Country;
import com.addressbook.domain.model.ZipCode;

import java.util.Set;


public class CountryBuilder implements IValidatableBuilder<Country> {
    private Integer id;
    private String name;
    private String code;
    private String dialCode;
    private Set<ZipCode> zipCodes;


    @Override
    public Country get() {
        return new Country(id, name, code, dialCode, zipCodes);
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

    public CountryBuilder withZipCodes(Set<ZipCode> zipCodes) {
        this.zipCodes = zipCodes;
        return this;
    }
}
