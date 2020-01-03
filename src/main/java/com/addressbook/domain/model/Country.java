package com.addressbook.domain.model;

import com.addressbook.domain.builder.CountryBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "tbl_country")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = "zipCodes")
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
    @OneToMany(mappedBy = "country", cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private Set<ZipCode> zipCodes;


    public static CountryBuilder aNew() {
        return new CountryBuilder();
    }
}
