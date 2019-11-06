package com.addressbook.service.mapper;

import com.addressbook.config.BaseMockitoTest;
import com.addressbook.domain.model.Address;
import com.addressbook.domain.validation.BeanValidator;
import com.addressbook.service.BeanUtils;
import com.addressbook.service.dto.AddressDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.*;

class AddressDTOMapperTest extends BaseMockitoTest {
    @Spy
    private AddressDTOMapper addressDTOMapper=new AddressDTOMapper(new BeanValidator(),new ZipCodeDTOMapper(new BeanValidator(),new CountryDTOMapper(new BeanValidator())));

    @Spy
    private BeanValidator validator;


    @Test
    void should_convert_dto_to_entity() {

        AddressDTO addressDTO = BeanUtils.createAddressDTO(65);

        Address address = addressDTOMapper.toEntity(addressDTO);

        assertEquals(address.getId(), addressDTO.getId());
        assertEquals(address.getAddressDetail(),addressDTO.getAddressDetail());
        assertEquals(address.getAddressType(),addressDTO.getAddressType());
        assertEquals(address.getOtherDescription(),addressDTO.getOtherDescription());
        assertEquals(address.getTitle(),addressDTO.getTitle());
        assertEquals(address.getZipCode().getCountry().getCode(),addressDTO.getZipCode().getCountry().getCode());
        assertEquals(address.getZipCode().getCountry().getDialCode(),addressDTO.getZipCode().getCountry().getDialCode());
        assertEquals(address.getZipCode().getCountry().getName(),addressDTO.getZipCode().getCountry().getName());
        assertEquals(address.getZipCode().getId(),addressDTO.getZipCode().getId());


    }

    @Test
    void should_convert_entity_to_dto() {

        Address address = BeanUtils.createAddressEntity(45);

        AddressDTO addressDTO = addressDTOMapper.toDto(address);

          assertEquals(45,addressDTO.getId());
    }

}