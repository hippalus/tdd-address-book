package com.addressbook.domain.model;


import com.addressbook.domain.builder.ZipCodeBuilder;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "tbl_zip_code")
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
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

    @ManyToOne(cascade ={CascadeType.REMOVE,CascadeType.REFRESH})
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    @NotNull
    private Country country;


    public static ZipCodeBuilder aNew() {
        return new ZipCodeBuilder();

    }

}
