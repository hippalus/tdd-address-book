package com.addressbook.domain.model;

import lombok.*;

import javax.persistence.criteria.CriteriaBuilder;

@AllArgsConstructor
@EqualsAndHashCode
@Setter@Getter
@ToString
public class Country {
    private Integer id;
    private final String name;
    private final String code;
    private final String dialCode;

}
