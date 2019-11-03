package com.addressbook.domain.model;


import com.addressbook.domain.builder.ZipCodeBuilder;
import lombok.*;

@AllArgsConstructor
@Getter@Setter
@EqualsAndHashCode@ToString
public class ZipCode {

    private Integer id;
    private final String postalCode;
    private final String province;
    private final String district;
    private final String street; //neighborhood or street
    private String region;
    private final Country country;


    public static ZipCodeBuilder aNew() {
        return new ZipCodeBuilder();
    }

}
