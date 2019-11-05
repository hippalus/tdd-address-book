package com.addressbook.domain.model;

import com.addressbook.domain.builder.UserBuilder;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name = "tbl_user")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class User implements IUser {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive
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
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_address",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    @NotNull
    private Set<Address> addresses;


    @Override
    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }


    public static UserBuilder aNew() {
        return new UserBuilder();
    }
}
