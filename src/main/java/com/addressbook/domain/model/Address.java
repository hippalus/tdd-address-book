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
@Data
public class Address {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    @NotNull
    @OneToOne(cascade ={ CascadeType.MERGE,CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name = "zip_code_id", referencedColumnName = "id")
    private ZipCode zipCode;

    @NotNull
    @NotBlank
    private String addressDetail;

    private String otherDescription;

    @ManyToMany(cascade = {CascadeType.REMOVE,CascadeType.REFRESH,CascadeType.MERGE},mappedBy = "addresses", targetEntity = User.class, fetch = FetchType.LAZY)
    private Set<User> users;

    public static AddressBuilder aNew() {
        return new AddressBuilder();
    }

}
