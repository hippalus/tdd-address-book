package com.addressbook.domain.builder;

import com.addressbook.domain.model.Address;
import com.addressbook.domain.model.Country;
import com.addressbook.domain.model.User;
import com.addressbook.domain.model.ZipCode;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static com.addressbook.domain.enums.AddressType.BUSINESS;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressBuilderTest {


    @Test
    void should_build_a_address_with_id(){

        Address address=Address.aNew().withId(34).get();
        assertEquals(34,address.getId());

    }
    @Test
    void should_build_a_address_with_title(){

        Address address=Address.aNew().withTitle("My Company").get();
        assertEquals("My Company",address.getTitle());

    }

    @Test
    void should_build_a_address_with_address_type(){

        Address address=Address.aNew().withAddressType(BUSINESS).get();
        assertEquals(BUSINESS,address.getAddressType());

    }

    @Test
    void should_build_a_address_with_zip_code(){

        Country turkey = new Country(1,"Turkey", "TR", "90");
        ZipCode zipCode = new ZipCode(12, "3256", "Istanbul", "Pendik", "Yenisehir Neighborhood", "Marmara", turkey);

        Address address=Address.aNew().withZipCode(zipCode).get();
        assertEquals(zipCode,address.getZipCode());

    }
    @Test
    void should_build_a_address_with_address_detail(){

        Address address=Address.aNew().withAddressDetail("4.Street York Avenue").get();
        assertEquals("4.Street York Avenue",address.getAddressDetail());

    }
    @Test
    void should_build_a_address_with_other_description(){

        Address address=Address.aNew().withOtherDescription("There is next to a lady").get();
        assertEquals("There is next to a lady",address.getOtherDescription());

    }
    @Test
    void should_build_a_address_with_users(){

        Set<User> users=new HashSet<>();
        users.add(User.aNew()
                .withId(10)
                .withLastName("Wilder")
                .withFirstName("Jack")
                .withBirthDate(LocalDate.of(1994, 11, 2))
                .withAddress(new HashSet<>())
                .withEmailAddress("jack@gmail.com")
                .withPhoneNumber("5062333444")
                .get());

        Address address=Address.aNew().withUsers(users).get();
        assertEquals(users,address.getUsers());

    }

}
