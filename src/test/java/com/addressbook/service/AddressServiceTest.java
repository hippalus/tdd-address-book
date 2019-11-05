package com.addressbook.service;

import com.addressbook.config.BaseMockitoTest;
import com.addressbook.domain.model.Address;
import com.addressbook.domain.model.User;
import com.addressbook.repository.AddressRepository;
import com.addressbook.service.dto.AddressDTO;
import com.addressbook.service.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AddressServiceTest extends BaseMockitoTest {

    @InjectMocks
    private AddressService addressService;
    @Mock
    private ZipCodeService zipCodeService;
    @Mock
    private AddressRepository addressRepository;


    @Test
    void should_save_address() {
        Address address=BeanUtils.createAddressEntity(1);

        lenient().when(zipCodeService.checkIfExists(1)).thenReturn(true);
        lenient().when(addressRepository.save(any(Address.class))).thenReturn(address);


        AddressDTO dto=BeanUtils.createAddressDTO(null);

        AddressDTO persistedAddressDTO=addressService.save(dto);

        assertThat(persistedAddressDTO)
                .isEqualToComparingOnlyGivenFields
                        (address, "id",
                                "title","addressType","addressDetail","otherDescription");
    }


    @Test
    void should_find_address() {
        Address address=BeanUtils.createAddressEntity(1);
        AddressDTO dto=BeanUtils.createAddressDTO(1);


        when(addressRepository.findById(1)).thenReturn(Optional.of(address));

        AddressDTO persistedAddressDTO = addressService.findById(1);

        assertThat(persistedAddressDTO)
                .isEqualToComparingOnlyGivenFields
                        (dto, "id",
                                "title","addressType","addressDetail","otherDescription");
    }


}
