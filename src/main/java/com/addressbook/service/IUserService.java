package com.addressbook.service;

import com.addressbook.service.dto.UserDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.READ_UNCOMMITTED;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;
import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

public interface IUserService extends IService {

    @Transactional(propagation = REQUIRED)
    UserDTO save(UserDTO userDto);

    @Transactional(readOnly = true, propagation = SUPPORTS, isolation = READ_UNCOMMITTED)
    UserDTO findById(Integer id);

    @Transactional(readOnly = true, propagation = SUPPORTS, isolation = READ_UNCOMMITTED)
    List<UserDTO> findAll();
}
