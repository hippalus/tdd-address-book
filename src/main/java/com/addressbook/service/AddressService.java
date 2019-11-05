package com.addressbook.service;
import com.addressbook.domain.model.Address;
import com.addressbook.domain.validation.BeanValidator;
import com.addressbook.repository.AddressRepository;
import com.addressbook.service.dto.AddressDTO;
import com.addressbook.service.mapper.AddressDTOMapper;
import com.addressbook.service.mapper.CountryDTOMapper;
import com.addressbook.service.mapper.ZipCodeDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

import static org.springframework.transaction.annotation.Isolation.READ_UNCOMMITTED;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;
import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

@Service
@Transactional
public class AddressService implements IService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ZipCodeService zipCodeService;
    @Autowired//fixme setter base injection
    private AddressDTOMapper addressDTOMapper=new AddressDTOMapper(new BeanValidator(),new ZipCodeDTOMapper(new BeanValidator(),new CountryDTOMapper(new BeanValidator())));


    @Override
    public boolean checkIfExists(@NotNull Integer id) {
        return addressRepository.existsById(id);
    }

    @Transactional(propagation = REQUIRED)
    public AddressDTO save(AddressDTO addressDTO){

        if(!zipCodeService.checkIfExists(addressDTO.getZipCode().getId())){
            //todo custom ex handler
            throw new RuntimeException(String.format(" Zip Code Exists %s", addressDTO.getZipCode().toString()));
        }

        Address address=addressDTOMapper.toEntity(addressDTO);

        return addressDTOMapper.toDto(addressRepository.save(address));
    }
    @Transactional(readOnly = true, propagation = SUPPORTS, isolation = READ_UNCOMMITTED)
    public AddressDTO findById(Integer id) {
        //todo custom ex handler
        return addressRepository.findById(id)
                .map(addressDTOMapper::toDto)
                .orElseThrow(() -> new RuntimeException(String.format(" Not find Address %d", id)));


    }
}
