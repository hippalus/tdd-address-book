package com.addressbook.service;


import com.addressbook.domain.model.User;
import com.addressbook.repository.UserRepository;
import com.addressbook.service.dto.UserDTO;
import com.addressbook.service.mapper.UserDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.*;
import static org.springframework.transaction.annotation.Isolation.READ_UNCOMMITTED;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;
import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

@Service
@Transactional
public class UserService implements IService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserDTOMapper userDTOMapper;


    @Override
    public boolean checkIfExists(@NotNull Integer id) {
        return userRepository.existsById(id);
    }

    @Transactional(propagation = REQUIRED)
    public UserDTO save(UserDTO userDto) {

        userDto.getAddresses().parallelStream()
                .filter(addr -> !addressService.checkIfExists(addr.getId()))
                .forEach(addressDTO -> addressService.save(addressDTO));

        if (nonNull(userDto.getId())) {
            //todo ex handler
            throw new RuntimeException();
        }

        User userToPersist = userDTOMapper.toEntity(userDto);

        return userDTOMapper.toDto(userRepository.save(userToPersist));

    }

    @Transactional(readOnly = true, propagation = SUPPORTS, isolation = READ_UNCOMMITTED)
    public UserDTO findById(Integer id) {
        //todo custom ex handler
        return userRepository.findById(id)
                .map(userDTOMapper::toDto)
                .orElseThrow(() -> new RuntimeException(" "));
    }

    @Transactional(readOnly = true, propagation = SUPPORTS, isolation = READ_UNCOMMITTED)
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> userDTOMapper.toDto(user))
                .collect(Collectors.toList());
    }
}
