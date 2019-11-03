package com.addressbook.domain.builder;

import com.addressbook.domain.model.Address;
import com.addressbook.domain.model.User;

import java.time.LocalDate;
import java.util.Set;

public class UserBuilder implements IValidatableBuilder<User> {

    private Integer id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private LocalDate birthDate;
    private String phoneNumber;
    private Set<Address> addresses;

    @Override
    public User get() {
        return new User(id, firstName, lastName, emailAddress, birthDate, phoneNumber, addresses);
    }

    public UserBuilder withId(Integer id) {
        this.id = id;
        return this;

    }

    public UserBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder withEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public UserBuilder withBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public UserBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserBuilder withAddress(Set<Address> addresses) {
        this.addresses = addresses;
        return this;
    }


}
