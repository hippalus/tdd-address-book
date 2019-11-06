package com.addressbook.domain.model;

import com.addressbook.domain.enums.AddressType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class AddressTest {

    @Test
    void should_create_by_using_constructor_with_all_args() {
//Todo users
        Set<Address> addresses = new HashSet<>();
        addresses.add(new Address());
        Set<User> users=new HashSet<>();
        users.add(new User(10, "Jack", "Wilder", "jack@gmail.com", LocalDate.of(1994, 11, 2), "5062333444",addresses));
        Country turkey = new Country(1,"Turkey", "TR", "90",new HashSet<>());

        Address address = new Address(1, "My Home Address", AddressType.HOME, new ZipCode(12, "3256", "Istanbul", "Pendik", "Yenisehir Neighborhood", "Marmara", turkey),"Brown Street","Go addressDetail ahead follow this rote there is on the left",users);
        assertThat(address.getId()).isEqualTo(1);
        assertThat(address.getTitle()).isEqualTo("My Home Address");
        assertThat(address.getAddressType()).isEqualTo(AddressType.HOME);
        //assertThat(address.getZipCode()).isEqualTo(); todo zip code
        assertThat(address.getAddressDetail()).isEqualTo("Brown Street");
        assertThat(address.getOtherDescription()).isEqualTo("Go addressDetail ahead follow this rote there is on the left");
    }

    @Test
    void should_create_by_using_constructor_with_no_args() {
        Address address=new Address();
        address.setId(1);
        address.setAddressType(AddressType.HOME);
        address.setAddressDetail("Brown Street");
        address.setOtherDescription("Go addressDetail ahead follow this rote there is on the left");
        address.setTitle("My Home Address");
        //address.setZipCode(); todo zipcodes
        address.setAddressDetail("Brown Street");

        assertThat(address.getId()).isEqualTo(1);
        assertThat(address.getTitle()).isEqualTo("My Home Address");
        assertThat(address.getAddressType()).isEqualTo(AddressType.HOME);
        //assertThat(address.getZipCode()).isEqualTo(); todo zip code
        assertThat(address.getAddressDetail()).isEqualTo("Brown Street");
        assertThat(address.getOtherDescription()).isEqualTo("Go addressDetail ahead follow this rote there is on the left");

    }

    @Test
    void should_not_equal_with_different_class() {
        Address address=new Address();
        address.setId(1);
        address.setAddressType(AddressType.HOME);
        address.setAddressDetail("Brown Street");
        address.setOtherDescription("Go addressDetail ahead follow this rote there is on the left");
        address.setTitle("My Home Address");
        //address.setZipCode(); todo zipcodes
        address.setAddressDetail("Brown Street");
        assertThat(address).isNotEqualTo(new User());


    }
}
