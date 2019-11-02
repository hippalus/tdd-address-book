package com.addressbook.domain.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AddressTypeTest {

    @Test
    void should_return_HOME() {

        AddressType addressType = AddressType.of("HOME");
        assertThat(addressType).isEqualTo(AddressType.HOME);

    }

    @Test
    void should_return_BUSINESS() {

        AddressType addressType = AddressType.of("BUSINESS");
        assertThat(addressType).isEqualTo(AddressType.BUSINESS);
    }

    @Test
    void should_return_OTHER() {

        AddressType addressType = AddressType.of("OTHER");
        assertThat(addressType).isEqualTo(AddressType.OTHER);
    }
    @Test
     void should_return_type_for_upper_cased_string () {

        AddressType addressType = AddressType.of("HOME");
        assertThat(addressType).isEqualTo(AddressType.HOME);

    }

    @Test
     void should_return_type_for_lower_cased_string () {

        AddressType addressType = AddressType.of("other");
        assertThat(addressType).isEqualTo(AddressType.OTHER);
    }

    @Test
     void should_throw_NullPointerException_on_string_is_null () {

        Assertions.assertThrows(NullPointerException.class,() -> AddressType.of(null));
    }

    @Test
     void should_throw_IllegalArgumentException_on_string_is_not_known () {

        Assertions.assertThrows(IllegalArgumentException.class,() -> AddressType.of("hello"));
    }


}