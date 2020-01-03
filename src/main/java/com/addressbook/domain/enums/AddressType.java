package com.addressbook.domain.enums;

import java.util.Optional;

public enum AddressType {
    HOME, BUSINESS, OTHER;

    public static AddressType of(String name) {
        return Optional.of(name)
                .map(String::toUpperCase)
                .map(AddressType::valueOf)
                .orElseThrow(NullPointerException::new);
    }
}
