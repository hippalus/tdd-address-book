package com.addressbook.domain.model;

import com.addressbook.domain.builder.UserBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name = "tbl_user")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = "addresses")
public class User implements IUser {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_address",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Set<Address> addresses;

    public static UserBuilder aNew() {
        return new UserBuilder();
    }

    @Override
    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }
}
