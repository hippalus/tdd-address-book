package com.addressbook.domain.builder;

import com.addressbook.domain.exceptions.BeanValidationException;
import com.addressbook.domain.model.Country;
import com.addressbook.domain.model.ZipCode;
import com.addressbook.domain.validation.BeanValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ZipCodeBuilderTest {
    private BeanValidator validator;

    @BeforeEach
    void init()
    {
        validator = new BeanValidator();
    }

    @AfterEach
    void terminate() {
        validator.close();
    }


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
    void should_throw_BeanValidationException_if_no_postal_code_specified() {

      assertThrows(BeanValidationException.class, () ->ZipCode.aNew()
              .withId(33)
              .withPostalCode("")
              .withProvince("Istanbul")
              .withDistrict("Pendik")
              .withStreet("Yenisehir")
              .withCountry(Country.aNew()
                      .withId(2)
                      .withName("Turkey")
                      .withCode("TR")
                      .withDialCode("+90")
                      .validateAndGet(validator))
              .validateAndGet(validator));

        assertThrows(BeanValidationException.class, () ->ZipCode.aNew()
                .withId(33)
                .withPostalCode(null)
                .withProvince("Istanbul")
                .withDistrict("Pendik")
                .withStreet("Yenisehir")
                .withCountry(Country.aNew()
                        .withId(2)
                        .withName("Turkey")
                        .withCode("TR")
                        .withDialCode("+90")
                        .validateAndGet(validator))
                .validateAndGet(validator));
    }

    @Test
    void should_throw_BeanValidationException_if_no_province_specified() {

        assertThrows(BeanValidationException.class, () ->ZipCode.aNew()
                .withId(33)
                .withPostalCode("65465")
                .withProvince("")
                .withDistrict("Pendik")
                .withStreet("Yenisehir")
                .withCountry(Country.aNew()
                        .withId(2)
                        .withName("Turkey")
                        .withCode("TR")
                        .withDialCode("+90")
                        .validateAndGet(validator))
                .validateAndGet(validator));

        assertThrows(BeanValidationException.class, () ->ZipCode.aNew()
                .withId(33)
                .withPostalCode("54564")
                .withProvince(null)
                .withDistrict("Pendik")
                .withStreet("Yenisehir")
                .withCountry(Country.aNew()
                        .withId(2)
                        .withName("Turkey")
                        .withCode("TR")
                        .withDialCode("+90")
                        .validateAndGet(validator))
                .validateAndGet(validator));
    }

    @Test
    void should_throw_BeanValidationException_if_no_district_specified() {

        assertThrows(BeanValidationException.class, () ->ZipCode.aNew()
                .withId(33)
                .withPostalCode("545546")
                .withProvince("Istanbul")
                .withDistrict("")
                .withStreet("Yenisehir")
                .withCountry(Country.aNew()
                        .withId(2)
                        .withName("Turkey")
                        .withCode("TR")
                        .withDialCode("+90")
                        .validateAndGet(validator))
                .validateAndGet(validator));

        assertThrows(BeanValidationException.class, () ->ZipCode.aNew()
                .withId(33)
                .withPostalCode("4224")
                .withProvince("Istanbul")
                .withDistrict(null)
                .withStreet("Yenisehir")
                .withCountry(Country.aNew()
                        .withId(2)
                        .withName("Turkey")
                        .withCode("TR")
                        .withDialCode("+90")
                        .validateAndGet(validator))
                .validateAndGet(validator));
    }

    @Test
    void should_throw_BeanValidationException_if_no_street_specified() {

        assertThrows(BeanValidationException.class, () ->ZipCode.aNew()
                .withId(33)
                .withPostalCode("5465")
                .withProvince("Istanbul")
                .withDistrict("Pendik")
                .withStreet("")
                .withCountry(Country.aNew()
                        .withId(2)
                        .withName("Turkey")
                        .withCode("TR")
                        .withDialCode("+90")
                        .validateAndGet(validator))
                .validateAndGet(validator));

        assertThrows(BeanValidationException.class, () ->ZipCode.aNew()
                .withId(33)
                .withPostalCode("5465")
                .withProvince("Istanbul")
                .withDistrict("Pendik")
                .withStreet(null)
                .withCountry(Country.aNew()
                        .withId(2)
                        .withName("Turkey")
                        .withCode("TR")
                        .withDialCode("+90")
                        .validateAndGet(validator))
                .validateAndGet(validator));

    }

    @Test
    void should_throw_BeanValidationException_if_no_country_specified() {

        assertThrows(BeanValidationException.class, () ->ZipCode.aNew()
                .withId(33)
                .withPostalCode("5465")
                .withProvince("Istanbul")
                .withDistrict("Pendik")
                .withStreet("KurtKoy")
                .withCountry(null)
                .validateAndGet(validator));

    }


}
