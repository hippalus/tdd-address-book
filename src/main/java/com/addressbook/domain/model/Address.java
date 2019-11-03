package com.addressbook.domain.model;

import com.addressbook.domain.builder.AddressBuilder;
import com.addressbook.domain.enums.AddressType;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Setter@Getter
public class Address {
    private Integer id;
    private String title;
    @NotNull
    @Enumerated(EnumType.STRING)
    private AddressType addressType;
    @NotNull
    private ZipCode zipCode;
    @NotNull@NotBlank
    private String addressDetail;
    private String otherDescription;
    private Set<User> users;


    public static AddressBuilder aNew() {
        return new AddressBuilder();
    }

}
