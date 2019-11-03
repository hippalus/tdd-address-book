package com.addressbook.domain.builder;

import com.addressbook.domain.enums.AddressType;
import com.addressbook.domain.model.Address;
import com.addressbook.domain.model.User;
import com.addressbook.domain.model.ZipCode;

import java.util.Set;

public class AddressBuilder {
    private Integer id;
    private String title;
    private AddressType addressType;
    private ZipCode zipCode;
    private String addressDetail;
    private String otherDescription;
    private Set<User> users;

    public Address get() {
        return new Address(id,title,addressType,zipCode,addressDetail,otherDescription,users);
    }


    public AddressBuilder withId(Integer id) {
        this.id=id;
        return this;
    }

    public AddressBuilder withTitle(String title) {
        this.title=title;
        return this;
    }

    public AddressBuilder withAddressType(AddressType addressType) {

        this.addressType=addressType;
        return this;
    }

    public AddressBuilder withZipCode(ZipCode zipCode) {

        this.zipCode=zipCode;
        return this;
    }

    public AddressBuilder withAddressDetail(String addressDetail) {
        this.addressDetail=addressDetail;
        return this;
    }
    public AddressBuilder withOtherDescription(String otherDescription) {
        this.otherDescription=otherDescription;
        return this;
    }

    public AddressBuilder withUsers(Set<User> users) {
        this.users=users;
        return this;
    }
}
