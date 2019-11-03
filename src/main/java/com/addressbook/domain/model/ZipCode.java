package com.addressbook.domain.model;


import com.addressbook.domain.builder.ZipCodeBuilder;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter@Setter
@EqualsAndHashCode@ToString
public class ZipCode {

    private Integer id;
    @NotNull @NotBlank
    private String postalCode;
    @NotNull@NotBlank
    private String province;
    @NotNull@NotBlank
    private String district;
    @NotNull@NotBlank
    private String street; //neighborhood or street
    private String region;
    @NotNull
    private Country country;


    public static ZipCodeBuilder aNew() {
        return new ZipCodeBuilder();
    }

}
