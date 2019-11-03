package com.addressbook.domain.model;

import com.addressbook.domain.builder.CountryBuilder;
import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@Setter@Getter
@ToString
public class Country {
    private Integer id;
    private final String name;
    private final String code;
    private final String dialCode;

    public static CountryBuilder aNew() {
        return new CountryBuilder();
    }
}
