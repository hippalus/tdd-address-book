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

public class BeanUtils {
    private BeanUtils() {
        //DO NOTING
    }


    public static UserDTO createUserDTO(Integer id) {
        Set<AddressDTO> addressDTOSet = new HashSet<>();
        addressDTOSet.add(createAddressDTO(id));
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
        addressDto.setAddressType(AddressType.HOME);
        addressDto.setAddressDetail("Brown Street");
        addressDto.setOtherDescription("Go addressDetail ahead follow this rote there is on the left");
        addressDto.setTitle("My Home Address");
        addressDto.setZipCode(createZipCodeDTO(id));
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

    public static User createUserEntity(Integer id, BeanValidator validator) {
        Set<Address> addresses=new HashSet<>();
        addresses.add(createAddressEntity(id,validator));
        return User.aNew()
                .withId(id)
                .withLastName("Isler")
                .withFirstName("Habip Hakan")
                .withBirthDate(LocalDate.of(1994, 11, 2))
                .withEmailAddress("habiphakan@live.com")
                .withPhoneNumber("5064665989")
                .withAddress(addresses)
                .validateAndGet(validator);
    }

    public static Address createAddressEntity(Integer id,BeanValidator validator) {
        return Address.aNew()
                .withOtherDescription("There is next to a lady")
                .withAddressType(BUSINESS)
                .withTitle("Amazon")
                .withAddressDetail("next to train station")
                .withZipCode(createZipCodeEntity(id,validator))
                .validateAndGet(validator);
    }

    public static  ZipCode createZipCodeEntity(Integer id,BeanValidator validator){
        return ZipCode.aNew()
                .withId(id)
                .withPostalCode("15488")
                .withProvince("Istanbul")
                .withDistrict("Pendik")
                .withStreet("Yenisehir")
                .withCountry(createCountryEntity(id,validator))
                .validateAndGet(validator);
    }

    public static Country createCountryEntity(Integer id ,BeanValidator validator){
        return Country.aNew()
                .withId(id)
                .withName("Turkey")
                .withCode("TR")
                .withDialCode("+90")
                .validateAndGet(validator);
    }
}
