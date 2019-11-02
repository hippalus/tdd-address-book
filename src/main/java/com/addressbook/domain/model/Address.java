package com.addressbook.domain.model;

import com.addressbook.domain.enums.AddressType;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Setter@Getter@ToString
public class Address {
    private Integer id;
    private String title;
    private AddressType addressType;
    private ZipCode zipCode;
    private String addressDetail;
    private String otherDescription;
    private Set<User> users;


}
