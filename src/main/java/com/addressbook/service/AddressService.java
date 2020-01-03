package com.addressbook.service;

import com.addressbook.domain.model.Address;
import com.addressbook.repository.AddressRepository;
import com.addressbook.service.dto.AddressDTO;
import com.addressbook.service.dto.ZipCodeDTO;
import com.addressbook.service.mapper.AddressDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.transaction.annotation.Isolation.READ_UNCOMMITTED;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;
import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

@Service
@Transactional
public class AddressService implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ZipCodeService zipCodeService;
    @Autowired
    private AddressDTOMapper addressDTOMapper;

    @Override
    public boolean checkIfExists(@NotNull Integer id) {
        return addressRepository.existsById(id);
    }

    @Override
    @Transactional(propagation = REQUIRED)
    public AddressDTO save(AddressDTO addressDTO) {

        if (!zipCodeService.checkIfExists(addressDTO.getZipCode().getId())) {
            //todo custom ex handler
            ZipCodeDTO zipCodeDTO = zipCodeService.save(addressDTO.getZipCode());
            addressDTO.setZipCode(zipCodeDTO);
        }

        Address address = addressDTOMapper.toEntity(addressDTO);

        return addressDTOMapper.toDto(addressRepository.save(address));
    }

    @Override
    @Transactional(readOnly = true, propagation = SUPPORTS, isolation = READ_UNCOMMITTED)
    public AddressDTO findById(Integer id) {
        //todo custom ex handler
        return addressRepository.findById(id)
                .map(addressDTOMapper::toDto)
                .orElseThrow(() -> new RuntimeException(String.format(" Not find Address %d", id)));


    }

    @Override
    @Transactional(readOnly = true, propagation = SUPPORTS, isolation = READ_UNCOMMITTED)
    public List<AddressDTO> findAll() {

        List<Address> allAddresses = addressRepository.findAll();
        return allAddresses.stream()
                .map(address -> (addressDTOMapper.toDto(address)))
                .collect(Collectors.toList());

    }
}
