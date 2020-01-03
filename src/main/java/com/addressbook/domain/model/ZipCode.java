package com.addressbook.domain.model;


import com.addressbook.domain.builder.ZipCodeBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_zip_code")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ZipCode {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @NotBlank
    private String postalCode;
    @NotNull
    @NotBlank
    private String province;
    @NotNull
    @NotBlank
    private String district;
    @NotNull
    @NotBlank
    private String street; //neighborhood or street

    private String region;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    @NotNull
    private Country country;


    public static ZipCodeBuilder aNew() {
        return new ZipCodeBuilder();

    }

}
