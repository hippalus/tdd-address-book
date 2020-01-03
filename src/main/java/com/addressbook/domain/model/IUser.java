package com.addressbook.domain.model;

import java.util.Set;

public interface IUser {
    Integer getId();

    Set<Address> getAddresses();

    String getFullName();


}
