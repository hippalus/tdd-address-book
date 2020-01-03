package com.addressbook.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    //todo address
    @Test
    void should_create_by_using_constructor_with_all_args() {
        Set<Address> addresses = new HashSet<>();
        addresses.add(new Address());

        User user = new User(10, "Jack", "Wilder", "jack@gmail.com", LocalDate.of(1994, 11, 2), "5062333444", addresses);
        assertThat(user.getId()).isEqualTo(10);
        assertThat(user.getFirstName()).isEqualTo("Jack");
        assertThat(user.getLastName()).isEqualTo("Wilder");
        assertThat(user.getEmailAddress()).isEqualTo("jack@gmail.com");
        assertThat(user.getBirthDate()).isEqualTo(LocalDate.of(1994, 11, 2));
        assertThat(user.getPhoneNumber()).isEqualTo("5062333444");
        assertThat(user.getAddresses()).isEqualTo(addresses);

    }

    @Test
    void should_create_by_using_constructor_with_no_args() {
        Set<Address> addresses = new HashSet<>();
        addresses.add(new Address());

        User user = new User();
        user.setId(10);
        user.setFirstName("Jack");
        user.setLastName("Wilder");
        user.setEmailAddress("jack@gmail.com");
        user.setBirthDate(LocalDate.of(1994, 11, 2));
        user.setAddresses(addresses);
        user.setPhoneNumber("5062333444");

        assertThat(user.getId()).isEqualTo(10);
        assertThat(user.getFirstName()).isEqualTo("Jack");
        assertThat(user.getLastName()).isEqualTo("Wilder");
        assertThat(user.getEmailAddress()).isEqualTo("jack@gmail.com");
        assertThat(user.getBirthDate()).isEqualTo(LocalDate.of(1994, 11, 2));
        assertThat(user.getPhoneNumber()).isEqualTo("5062333444");
        assertThat(user.getAddresses()).isEqualTo(addresses);


    }

    @Test
    void should_not_equal_with_different_class() {
        User user = new User();
        user.setId(10);
        user.setFirstName("Jack");
        user.setLastName("Wilder");
        user.setEmailAddress("jack@gmail.com");
        user.setBirthDate(LocalDate.of(1994, 11, 2));
        user.setPhoneNumber("5062333444");


        assertThat(user).isNotEqualTo(new Address());

    }

}
