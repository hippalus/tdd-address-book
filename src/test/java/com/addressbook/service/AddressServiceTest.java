package com.addressbook.service;

import com.addressbook.config.BaseMockitoTest;
import com.addressbook.domain.model.Address;
import com.addressbook.domain.validation.BeanValidator;
import com.addressbook.repository.AddressRepository;
import com.addressbook.service.dto.AddressDTO;
import com.addressbook.service.mapper.AddressDTOMapper;
import com.addressbook.service.mapper.CountryDTOMapper;
import com.addressbook.service.mapper.ZipCodeDTOMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AddressServiceTest extends BaseMockitoTest {

    @InjectMocks
    private AddressService addressService;
    @Mock
    private ZipCodeService zipCodeService;
    @Mock
    private AddressRepository addressRepository;

    @Spy
    private AddressDTOMapper addressDTOMapper = new AddressDTOMapper(new BeanValidator(), new ZipCodeDTOMapper(new BeanValidator(), new CountryDTOMapper(new BeanValidator())));


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void should_save_address() {
        Address address = BeanUtils.createAddressEntity(1);
        lenient().when(zipCodeService.checkIfExists(1)).thenReturn(true);
        lenient().when(addressRepository.save(any(Address.class))).thenReturn(address);


        AddressDTO dto = BeanUtils.createAddressDTO(null);

        AddressDTO persistedAddressDTO = addressService.save(dto);

        assertThat(persistedAddressDTO)
                .isEqualToComparingOnlyGivenFields
                        (address, "id",
                                "title", "addressType", "addressDetail", "otherDescription");
    }


    @Test
    void should_find_address() {
        Address address = BeanUtils.createAddressEntity(1);
        AddressDTO dto = BeanUtils.createAddressDTO(1);


        when(addressRepository.findById(1)).thenReturn(Optional.of(address));

        AddressDTO persistedAddressDTO = addressService.findById(1);

        assertThat(persistedAddressDTO)
                .isEqualToComparingOnlyGivenFields
                        (dto, "id",
                                "title", "addressType", "addressDetail", "otherDescription");
    }

    @Test
    void should_find_all_addresses() {

        Address address = BeanUtils.createAddressEntity(1);
        Address address2 = BeanUtils.createAddressEntity(2);
        Address address3 = BeanUtils.createAddressEntity(3);

        List<Address> addressList = new ArrayList<>();
        addressList.add(address);
        addressList.add(address2);
        addressList.add(address3);


        when(addressRepository.findAll()).thenReturn(addressList);

        List<AddressDTO> actual = addressService.findAll();

        List<AddressDTO> expected = addressList.stream()
                .map(addr -> addressDTOMapper.toDto(addr))
                .collect(Collectors.toList());

        Assertions.assertEquals(expected.size(), actual.size());
    }


}
