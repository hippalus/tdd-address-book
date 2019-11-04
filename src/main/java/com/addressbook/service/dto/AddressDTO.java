package com.addressbook.service.dto;

import com.addressbook.domain.enums.AddressType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    @Positive
    private Integer id;
    private String title;
    @NotNull
    private AddressType addressType;
    @NotNull
    private ZipCodeDTO zipCode;
    @NotNull@NotBlank
    private String addressDetail;
    private String otherDescription;

}
