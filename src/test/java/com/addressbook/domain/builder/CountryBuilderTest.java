package com.addressbook.domain.builder;

import com.addressbook.domain.exceptions.BeanValidationException;
import com.addressbook.domain.model.Country;
import com.addressbook.domain.validation.BeanValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CountryBuilderTest {
    private BeanValidator validator;

    @BeforeEach
    void init() {
        validator = new BeanValidator();
    }

    @AfterEach
    void terminate() {
        validator.close();
    }

    @Test
    void should_build_a_country_with_all_property() {

        Country country = Country.aNew().withId(1).withName("Turkey").withCode("TR").withDialCode("+90").get();
        assertEquals("+90", country.getDialCode());
        assertEquals("Turkey", country.getName());
        assertEquals("TR", country.getCode());
        assertEquals(1, country.getId());

    }

    @Test
    void should_throw_BeanValidationException_when_name_is_null() {

        assertThrows(BeanValidationException.class, () -> Country.aNew().withId(1).withName(null).withCode("TR").validateAndGet(validator));

    }

    @Test
    void should_throw_BeanValidationException_when_name_is_blank() {

        assertThrows(BeanValidationException.class, () -> Country.aNew().withId(1).withName("").withCode("TR").validateAndGet(validator));

    }

    @Test
    void should_throw_BeanValidationException_when_code_is_null() {

        assertThrows(BeanValidationException.class, () -> Country.aNew().withId(1).withName(null).validateAndGet(validator));

    }

    @Test
    void should_throw_BeanValidationException_when_code_is_blank() {

        assertThrows(BeanValidationException.class, () -> Country.aNew().withId(1).withName(null).withCode("").validateAndGet(validator));

    }

    @Test
    void should_throw_BeanValidationException_when_dial_code_is_blank() {

        assertThrows(BeanValidationException.class, () -> Country.aNew().withId(1).withName("Turkey").withCode("TR").withDialCode("").validateAndGet(validator));

    }

    @Test
    void should_throw_BeanValidationException_when_dial_code_is_null() {

        assertThrows(BeanValidationException.class, () -> Country.aNew().withId(1).withName("Turkey").withCode("TR").withDialCode(null).validateAndGet(validator));

    }


}
