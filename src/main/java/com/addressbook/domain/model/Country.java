package com.addressbook.domain.model;

import com.addressbook.domain.builder.CountryBuilder;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "tbl_country")
@AllArgsConstructor
@EqualsAndHashCode
@Setter
@Getter
@ToString
public class Country {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive
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
