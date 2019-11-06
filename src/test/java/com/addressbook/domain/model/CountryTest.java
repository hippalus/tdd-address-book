package com.addressbook.domain.model;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

class CountryTest {
    @Test
    void should_create_by_using_constructor_with_all_args() {
        Country germany=new Country(1,"Germany","DE","080",new HashSet<>());

        assertThat(germany.getId()).isEqualTo(1);
        assertThat(germany.getName()).isEqualTo("Germany");
        assertThat(germany.getCode()).isEqualTo("DE");
        assertThat(germany.getDialCode()).isEqualTo("080");

    }


}