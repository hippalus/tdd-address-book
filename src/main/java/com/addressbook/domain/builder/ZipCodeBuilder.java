package com.addressbook.domain.builder;

import com.addressbook.domain.exceptions.PropertyRequiredException;
import com.addressbook.domain.model.Country;
import com.addressbook.domain.model.ZipCode;

import static com.addressbook.domain.constants.DomainConstants.*;
import static java.util.Objects.*;

public class ZipCodeBuilder {

    private Integer id;
    private String postalCode;
    private String province;
    private String district;
    private String street; //neighborhood or street
    private String region; //optional
    private Country country;

    public ZipCode get() {
        checkModelProperties();
        return new ZipCode(id, postalCode, province, district, street, region, country);
    }

    private void checkModelProperties() {
        if (isNull(this.postalCode)) throw new PropertyRequiredException(ENTITY_ZIP_CODE, "postalCode");
        if (isNull(this.province)) throw new PropertyRequiredException(ENTITY_ZIP_CODE, "province");
        if (isNull(this.district)) throw new PropertyRequiredException(ENTITY_ZIP_CODE, "district");
        if (isNull(this.street)) throw new PropertyRequiredException(ENTITY_ZIP_CODE, "street");
        if (isNull(this.country)) throw new PropertyRequiredException(ENTITY_ZIP_CODE, "country");

    }

    public ZipCodeBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public ZipCodeBuilder withPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public ZipCodeBuilder withProvince(String province) {
        this.province = province;
        return this;

    }

    public ZipCodeBuilder withDistrict(String district) {

        this.district = district;
        return this;

    }

    public ZipCodeBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public ZipCodeBuilder withRegion(String region) {

        this.region = region;
        return this;
    }

    public ZipCodeBuilder withCountry(Country country) {

        this.country = country;
        return this;
    }
}



