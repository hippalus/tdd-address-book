package com.addressbook.service;

import com.addressbook.domain.enums.AddressType;
import com.addressbook.domain.model.Address;
import com.addressbook.domain.model.Country;
import com.addressbook.domain.model.User;
import com.addressbook.domain.model.ZipCode;
import com.addressbook.domain.validation.BeanValidator;
import com.addressbook.repository.AddressRepository;
import com.addressbook.repository.CountryRepository;
import com.addressbook.repository.ZipCodeRepository;
import com.addressbook.service.dto.AddressDTO;
import com.addressbook.service.dto.CountryDTO;
import com.addressbook.service.dto.UserDTO;
import com.addressbook.service.dto.ZipCodeDTO;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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
    public static Address createAddressEntity(Integer id,ZipCode zipCode,String s) {
        return Address.aNew()
                .withId(id)
                .withOtherDescription("Go addressDetail ahead follow this rote there is on the left")
                .withAddressType(HOME)
                .withTitle("zipCode")
                .withAddressDetail("Brown Street")
                .withZipCode(zipCode)
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
    public static ZipCode createZipCodeEntity() {
        return ZipCode.aNew()
                .withPostalCode("345100")
                .withProvince("Istanbul")
                .withDistrict("Pendik")
                .withStreet("Camlik")
                .withCountry(createCountryEntity(null))
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

    public static Address createRandomAddressAndSave(AddressRepository addressRepository,ZipCode zipCode) {
        return addressRepository.save((createAddressEntity(null,zipCode,"Address IT"+UUID.randomUUID().toString())));
    }

    public static User createRandomUser(Address address) {

        return createRandomUser(null,"User IT"+UUID.randomUUID().toString(),address);
    }

    private static User createRandomUser(Integer id, String s, Address address) {
        HashSet<Address> addresses=new HashSet<>();
        addresses.add(address);
        return User.aNew()
                .withId(id)
                .withAddress(addresses)
                .withPhoneNumber("5061589898")
                .withEmailAddress("test@test.com")
                .withFirstName(s)
                .withLastName("Isler")
                .withBirthDate(LocalDate.of(1984,5,4))
                .get();

    }

    public static Country createRandomCountryAndSave(CountryRepository countryRepository) {
        return countryRepository.save(createRandomCountry(null,"Country Test"+UUID.randomUUID().toString()));
    }

    private static Country createRandomCountry(Integer id, String name) {
        return Country.aNew()
                .withId(id)
                .withName(name)
                .withCode("TR")
                .withDialCode("90")
                .get();
    }

    public static ZipCode createRandomZipCodeAndSave(ZipCodeRepository zipCodeRepository, Country country) {
        return zipCodeRepository.save(createRandomZipCode(null,country));
    }

    private static ZipCode createRandomZipCode(Integer id, Country country) {
        return ZipCode.aNew()
                .withId(id)
                .withCountry(country)
                .withStreet("Street Test")
                .withRegion("Test region")
                .withPostalCode("6548")
                .withProvince("istanbul")
                .withDistrict("pendik")
                .get();
    }
}
