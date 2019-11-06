package com.addressbook.service.mapper;

import com.addressbook.domain.model.Address;
import com.addressbook.domain.validation.BeanValidator;
import com.addressbook.service.dto.AddressDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AddressDTOMapper {
    @Autowired
    private BeanValidator validator;
    @Autowired
    private ZipCodeDTOMapper zipCodeDTOMapper;

    public AddressDTOMapper(BeanValidator validator, ZipCodeDTOMapper zipCodeDTOMapper) {
        this.validator = validator;
        this.zipCodeDTOMapper = zipCodeDTOMapper;
    }

    public Address toEntity(AddressDTO addressDTO) {
        return Address.aNew().withId(addressDTO.getId())
                .withTitle(addressDTO.getTitle())
                .withAddressType(addressDTO.getAddressType())
                .withAddressDetail(addressDTO.getAddressDetail())
                .withOtherDescription(addressDTO.getOtherDescription())
                .withZipCode(zipCodeDTOMapper.toEntity(addressDTO.getZipCode()))
                .validateAndGet(validator);

    }

    public Set<Address> toEntity(Set<AddressDTO> addressDTOSet) {

        return addressDTOSet.stream().map(addressDTO -> Address.aNew()
                .withId(addressDTO.getId())
                .withAddressType(addressDTO.getAddressType())
                .withAddressDetail(addressDTO.getAddressDetail())
                .withZipCode(zipCodeDTOMapper.toEntity(addressDTO.getZipCode()))
                .withTitle(addressDTO.getTitle())
                .withOtherDescription(addressDTO.getOtherDescription())
                .validateAndGet(validator)
        ).collect(Collectors.toSet());

    }

    public AddressDTO toDto(Address address) {

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setTitle(address.getTitle());
        addressDTO.setZipCode(zipCodeDTOMapper.toDto(address.getZipCode()));
        addressDTO.setAddressType(address.getAddressType());
        addressDTO.setAddressDetail(address.getAddressDetail());
        addressDTO.setOtherDescription(address.getOtherDescription());

        return addressDTO;
    }
}
