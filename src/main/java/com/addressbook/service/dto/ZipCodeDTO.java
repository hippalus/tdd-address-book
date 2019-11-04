package com.addressbook.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter@Getter
public class ZipCodeDTO {

    @Positive
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
    private CountryDTO country;

}
