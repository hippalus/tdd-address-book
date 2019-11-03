package com.addressbook.domain.builder;

import com.addressbook.domain.exceptions.PropertyRequiredException;
import com.addressbook.domain.model.Country;
import com.addressbook.domain.model.ZipCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ZipCodeBuilderTest {

    @Test
    void should_build_a_zip_code_with_all_property() {

        ZipCode zipCode = ZipCode.aNew()
                .withId(33)
                .withPostalCode("15488")
                .withProvince("Istanbul")
                .withDistrict("Pendik")
                .withStreet("Yenisehir")
                .withCountry(Country.aNew()
                        .withId(2)
                        .withName("Turkey")
                        .withCode("TR")
                        .withDialCode("+90")
                        .get())
                .get();

        assertEquals("Istanbul", zipCode.getProvince());
        assertEquals("Pendik", zipCode.getDistrict());
        assertEquals(33, zipCode.getId());
        assertEquals("15488", zipCode.getPostalCode());
        assertEquals("Yenisehir", zipCode.getStreet());
        assertEquals("TR", zipCode.getCountry().getCode());
        assertEquals("+90", zipCode.getCountry().getDialCode());
        assertEquals("Turkey", zipCode.getCountry().getName());
        assertEquals(2, zipCode.getCountry().getId());

    }

    @Test
    void should_throw_PropertyRequiredException_if_no_postal_code_specified() {

        Exception ex = assertThrows(PropertyRequiredException.class, () -> ZipCode.aNew().withId(1).get());
        assertEquals("Property postalCode of model ZipCode must not be null !", ex.getMessage());
    }

    @Test
    void should_throw_PropertyRequiredException_if_no_province_specified() {

        Exception ex = assertThrows(PropertyRequiredException.class, () -> ZipCode.aNew().withPostalCode("15488").get());
        assertEquals("Property province of model ZipCode must not be null !", ex.getMessage());
    }

    @Test
    void should_throw_PropertyRequiredException_if_no_district_specified() {

        Exception ex = assertThrows(PropertyRequiredException.class, () -> ZipCode.aNew().withPostalCode("15488").withProvince("Istanbul").get());
        assertEquals("Property district of model ZipCode must not be null !", ex.getMessage());
    }

    @Test
    void should_throw_PropertyRequiredException_if_no_street_specified() {

        Exception ex = assertThrows(PropertyRequiredException.class, () -> ZipCode.aNew().withPostalCode("15488").withProvince("Istanbul").withDistrict("Pendik").get());
        assertEquals("Property street of model ZipCode must not be null !", ex.getMessage());
    }

    @Test
    void should_throw_PropertyRequiredException_if_no_country_specified() {

        Exception ex = assertThrows(PropertyRequiredException.class, () -> ZipCode.aNew().withPostalCode("15488").withProvince("Istanbul").withDistrict("Pendik").withStreet("Yenisehir").get());
        assertEquals("Property country of model ZipCode must not be null !", ex.getMessage());
    }


}
