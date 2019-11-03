package com.addressbook.domain.model;

import com.addressbook.domain.builder.UserBuilder;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
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


    public static UserBuilder aNew(){
        return new UserBuilder();
    }
}
