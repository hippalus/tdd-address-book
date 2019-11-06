package com.addressbook.domain.builder;

import com.addressbook.domain.enums.AddressType;
import com.addressbook.domain.exceptions.BeanValidationException;
import com.addressbook.domain.model.Address;
import com.addressbook.domain.model.Country;
import com.addressbook.domain.model.User;
import com.addressbook.domain.model.ZipCode;

import com.addressbook.domain.validation.BeanValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static com.addressbook.domain.enums.AddressType.BUSINESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddressBuilderTest {

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
    void should_build_a_address_with_id() {

        Address address = Address.aNew().withId(34).get();
        assertEquals(34, address.getId());

    }

    @Test
    void should_build_a_address_with_title() {

        Address address = Address.aNew().withTitle("My Company").get();
        assertEquals("My Company", address.getTitle());

    }

    @Test
    void should_build_a_address_with_address_type() {

        Address address = Address.aNew().withAddressType(BUSINESS).get();
        assertEquals(BUSINESS, address.getAddressType());

    }

    @Test
    void should_build_a_address_with_zip_code() {

        Country turkey = new Country(1, "Turkey", "TR", "90",new HashSet<>());
        ZipCode zipCode = new ZipCode(12, "3256", "Istanbul", "Pendik", "Yenisehir Neighborhood", "Marmara", turkey);

        Address address = Address.aNew().withZipCode(zipCode).get();
        assertEquals(zipCode, address.getZipCode());

    }

    @Test
    void should_build_a_address_with_address_detail() {

        Address address = Address.aNew().withAddressDetail("4.Street York Avenue").get();
        assertEquals("4.Street York Avenue", address.getAddressDetail());

    }

    @Test
    void should_build_a_address_with_other_description() {

        Address address = Address.aNew().withOtherDescription("There is next to a lady").get();
        assertEquals("There is next to a lady", address.getOtherDescription());

    }

    @Test
    void should_build_a_address_with_users() {

        Set<User> users = new HashSet<>();
        users.add(User.aNew()
                .withId(10)
                .withLastName("Wilder")
                .withFirstName("Jack")
                .withBirthDate(LocalDate.of(1994, 11, 2))
                .withAddress(new HashSet<>())
                .withEmailAddress("jack@gmail.com")
                .withPhoneNumber("5062333444")
                .get());

        Address address = Address.aNew().withUsers(users).get();
        assertEquals(users, address.getUsers());

    }

    @Test
    void should_throw_BeanValidationException_when_address_type_is_null(){
        assertThrows(BeanValidationException.class,() -> Address.aNew()
                .withOtherDescription("There is next to a lady")
                .validateAndGet(validator));
    }
    @Test
    void should_throw_BeanValidationException_when_zip_code_is_null(){
        assertThrows(BeanValidationException.class,() -> Address.aNew()
                .withOtherDescription("There is next to a lady")
                .withAddressType(BUSINESS)
                .withTitle("Amazon")
                .withZipCode(null)
                .validateAndGet(validator));
    }
    @Test
    void should_throw_BeanValidationException_when_address_detail_is_blank(){
        assertThrows(BeanValidationException.class,() -> Address.aNew()
                .withOtherDescription("There is next to a lady")
                .withAddressType(BUSINESS)
                .withTitle("Amazon")
                .withAddressDetail("")
                .withZipCode(ZipCode.aNew()
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
                                .validateAndGet(validator))
                        .validateAndGet(validator))
                .validateAndGet(validator));
    }
    @Test
    void should_throw_BeanValidationException_when_address_detail_is_null(){
        assertThrows(BeanValidationException.class,() -> Address.aNew()
                .withOtherDescription("There is next to a lady")
                .withAddressType(BUSINESS)
                .withTitle("Amazon")
                .withAddressDetail(null)
                .withZipCode(ZipCode.aNew()
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
                                .validateAndGet(validator))
                        .validateAndGet(validator))
                .validateAndGet(validator));
    }


}
