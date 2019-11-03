package com.addressbook.domain.builder;


import com.addressbook.domain.model.Country;
import com.addressbook.domain.model.ZipCode;


public class ZipCodeBuilder implements IValidatableBuilder<ZipCode> {

    private Integer id;
    private String postalCode;
    private String province;
    private String district;
    private String street; //neighborhood or street
    private String region; //optional
    private Country country;

    @Override
    public ZipCode get() {

        return new ZipCode(id, postalCode, province, district, street, region, country);
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



