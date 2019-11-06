package com.addressbook.domain.model;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

public class ZipCodeTest {

    @Test
    void should_create_by_using_constructor_with_all_args() {
        Country turkey = new Country(1,"Turkey", "TR", "90",new HashSet<>());

        ZipCode zipCode = new ZipCode(12, "3256", "Istanbul", "Pendik", "Yenisehir Neighborhood", "Marmara", turkey);

        assertThat(zipCode.getId()).isEqualTo(12);
        assertThat(zipCode.getPostalCode()).isEqualTo("3256");
        assertThat(zipCode.getProvince()).isEqualTo("Istanbul");
        assertThat(zipCode.getDistrict()).isEqualTo("Pendik");
        assertThat(zipCode.getStreet()).isEqualTo("Yenisehir Neighborhood");
        assertThat(zipCode.getRegion()).isEqualTo("Marmara");
        assertThat(zipCode.getCountry()).isEqualTo(turkey);

    }
}
