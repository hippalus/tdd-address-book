package com.addressbook.domain.model;

import com.addressbook.domain.builder.CountryBuilder;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@EqualsAndHashCode
@Setter
@Getter
@ToString
public class Country {
    private Integer id;
    @NotNull@NotBlank
    private String name;
    @NotNull@NotBlank
    private String code;
    @NotNull@NotBlank
    private String dialCode;

    public static CountryBuilder aNew() {
        return new CountryBuilder();
    }
}
