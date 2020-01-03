package com.addressbook.service;

import com.addressbook.service.dto.AddressDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.READ_UNCOMMITTED;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;
import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

public interface IAddressService extends IService {
    @Transactional(propagation = REQUIRED)
    AddressDTO save(AddressDTO addressDTO);

    @Transactional(readOnly = true, propagation = SUPPORTS, isolation = READ_UNCOMMITTED)
    AddressDTO findById(Integer id);

    @Transactional(readOnly = true, propagation = SUPPORTS, isolation = READ_UNCOMMITTED)
    List<AddressDTO> findAll();
}
