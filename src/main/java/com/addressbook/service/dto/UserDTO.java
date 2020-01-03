package com.addressbook.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Integer id;

    @NotBlank
    @NotNull
    private String firstName;

    @NotBlank
    @NotNull
    private String lastName;

    @NotBlank
    @NotNull
    @Email
    private String emailAddress;

    private LocalDate birthDate;

    @NotNull
    @NotBlank
    @Pattern(regexp = "(^$|[0-9]{10})")
    private String phoneNumber;

    @NotNull
    private Set<AddressDTO> addresses;

}
