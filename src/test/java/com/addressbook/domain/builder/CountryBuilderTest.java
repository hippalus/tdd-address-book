package com.addressbook.domain.builder;

import com.addressbook.domain.exceptions.PropertyRequiredException;
import com.addressbook.domain.model.Country;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CountryBuilderTest {

    @Test
    void should_build_a_country_with_all_property(){

        Country country= Country.aNew().withId(1).withName("Turkey").withCode("TR").withDialCode("+90").get();
        assertEquals("+90",country.getDialCode());
        assertEquals("Turkey",country.getName());
        assertEquals("TR",country.getCode());
        assertEquals(1,country.getId());

    }
    @Test
    void should_throw_PropertyRequiredException_if_no_name_specified(){

        Exception ex=assertThrows(PropertyRequiredException.class,()-> Country.aNew().withId(1).get());
        assertEquals( "Property name of model Country must not be null !",ex.getMessage() );
    }
    @Test
    void should_throw_PropertyRequiredException_if_no_code_specified(){

        Exception ex=assertThrows(PropertyRequiredException.class,()-> Country.aNew().withId(1).withName("Turkey").get());
        assertEquals( "Property code of model Country must not be null !",ex.getMessage() );
    }
    @Test
    void should_throw_PropertyRequiredException_if_no_dial_code_specified(){

        Exception ex=assertThrows(PropertyRequiredException.class,()-> Country.aNew().withId(1).withName("Turkey").withCode("TR").get());
        assertEquals( "Property dialCode of model Country must not be null !",ex.getMessage() );
    }

}
