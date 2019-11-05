package com.addressbook.service;

import com.addressbook.domain.enums.AddressType;
import com.addressbook.domain.model.Address;
import com.addressbook.domain.model.Country;
import com.addressbook.domain.model.User;
import com.addressbook.domain.model.ZipCode;
import com.addressbook.domain.validation.BeanValidator;
import com.addressbook.service.dto.AddressDTO;
import com.addressbook.service.dto.CountryDTO;
import com.addressbook.service.dto.UserDTO;
import com.addressbook.service.dto.ZipCodeDTO;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static com.addressbook.domain.enums.AddressType.BUSINESS;
import static com.addressbook.domain.enums.AddressType.HOME;

public class BeanUtils {
    private BeanUtils() {
        //DO NOTING
    }


    public static UserDTO createUserDTO(Integer id) {
        Set<AddressDTO> addressDTOSet = new HashSet<>();
        addressDTOSet.add(createAddressDTO(1));
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setBirthDate(LocalDate.of(1994, 11, 2));
        userDTO.setEmailAddress("habip@hisler.com");
        userDTO.setFirstName("Habip Hakan");
        userDTO.setLastName("Isler");
        userDTO.setPhoneNumber("5435154878");
        userDTO.setAddresses(addressDTOSet);
        return userDTO;
    }

    public static AddressDTO createAddressDTO(Integer id) {
        AddressDTO addressDto = new AddressDTO();
        addressDto.setId(id);
        addressDto.setAddressType(HOME);
        addressDto.setAddressDetail("Brown Street");
        addressDto.setOtherDescription("Go addressDetail ahead follow this rote there is on the left");
        addressDto.setTitle("My Home Address");
        addressDto.setZipCode(createZipCodeDTO(1));
        addressDto.setAddressDetail("Brown Street");
        return addressDto;
    }

    public static ZipCodeDTO createZipCodeDTO(Integer id) {
        ZipCodeDTO zipCodeDTO = new ZipCodeDTO();
        zipCodeDTO.setId(id);
        zipCodeDTO.setCountry(createCountryDTO(id));
        zipCodeDTO.setDistrict("Pendik");
        zipCodeDTO.setProvince("Istanbul");
        zipCodeDTO.setPostalCode("345100");
        zipCodeDTO.setStreet("Camlik");
        return zipCodeDTO;
    }

    public static CountryDTO createCountryDTO(Integer id) {
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setId(id);
        countryDTO.setCode("TR");
        countryDTO.setDialCode("+90");
        countryDTO.setName("Turkey");
        return countryDTO;
    }

    public static User createUserEntity(Integer id) {
        Set<Address> addresses = new HashSet<>();
        addresses.add(createAddressEntity(id));
        return User.aNew()
                .withId(id)
                .withLastName("Isler")
                .withFirstName("Habip Hakan")
                .withBirthDate(LocalDate.of(1994, 11, 2))
                .withEmailAddress("habip@hisler.com")
                .withPhoneNumber("5435154878")
                .withAddress(addresses)
                .get();
    }

    public static Address createAddressEntity(Integer id) {
        return Address.aNew()
                .withId(id)
                .withOtherDescription("Go addressDetail ahead follow this rote there is on the left")
                .withAddressType(HOME)
                .withTitle("My Home Address")
                .withAddressDetail("Brown Street")
                .withZipCode(createZipCodeEntity(id))
                .get();
    }

    public static ZipCode createZipCodeEntity(Integer id) {
        return ZipCode.aNew()
                .withId(id)
                .withPostalCode("345100")
                .withProvince("Istanbul")
                .withDistrict("Pendik")
                .withStreet("Camlik")
                .withCountry(createCountryEntity(id))
                .get();
    }

    public static Country createCountryEntity(Integer id) {
        return Country.aNew()
                .withId(id)
                .withName("Turkey")
                .withCode("TR")
                .withDialCode("+90")
                .get();
    }
}
