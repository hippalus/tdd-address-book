package com.addressbook.domain.model;

import com.addressbook.domain.builder.UserBuilder;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class User implements IUser {

    private Integer id;
    @NotBlank@NotNull
    private String firstName;
    @NotBlank@NotNull
    private String lastName;
    @NotBlank@NotNull@Email
    private String emailAddress;
    private LocalDate birthDate;
    @NotNull@NotBlank@Pattern(regexp = "(^$|[0-9]{10})")
    private String phoneNumber;
    @NotNull
    private Set<Address> addresses;


    @Override
    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }


    public static UserBuilder aNew(){
        return new UserBuilder();
    }
}
