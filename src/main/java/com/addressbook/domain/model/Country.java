package com.addressbook.domain.model;

import com.addressbook.domain.builder.CountryBuilder;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_country")
@AllArgsConstructor
@Data
public class Country {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String code;

    @NotNull
    @NotBlank
    private String dialCode;

    @OneToMany(mappedBy = "country" )
    private Set<ZipCode> zipCodes;


    public static CountryBuilder aNew() {
        return new CountryBuilder();
    }
}
