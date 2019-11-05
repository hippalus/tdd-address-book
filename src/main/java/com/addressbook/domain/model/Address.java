package com.addressbook.domain.model;

import com.addressbook.domain.builder.AddressBuilder;
import com.addressbook.domain.enums.AddressType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "tbl_address")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Setter@Getter
public class Address {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @NotNull
    @Enumerated(EnumType.STRING)
    private AddressType addressType;
    @NotNull@OneToOne
    private ZipCode zipCode;
    @NotNull@NotBlank
    private String addressDetail;
    private String otherDescription;

    @ManyToMany(mappedBy = "addresses",fetch = FetchType.LAZY)
    private Set<User> users;


    public static AddressBuilder aNew() {
        return new AddressBuilder();
    }

}
