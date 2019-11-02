package com.addressbook.domain.model;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class User implements IUser {

    private Integer id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private LocalDate birthDate;
    private String phoneNumber;
    private Set<Address> addresses;


    @Override
    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }
}
