package com.addressbook.service;


import com.addressbook.domain.model.User;
import com.addressbook.domain.validation.BeanValidator;
import com.addressbook.repository.UserRepository;
import com.addressbook.service.dto.UserDTO;
import com.addressbook.service.mapper.AddressDTOMapper;
import com.addressbook.service.mapper.CountryDTOMapper;
import com.addressbook.service.mapper.UserDTOMapper;
import com.addressbook.service.mapper.ZipCodeDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.constraints.NotNull;

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
    @Autowired //fixme setter base injection; create bean
    private UserDTOMapper userDTOMapper=new UserDTOMapper(new BeanValidator(),new AddressDTOMapper(new BeanValidator(),new ZipCodeDTOMapper(new BeanValidator(),new CountryDTOMapper(new BeanValidator()))));


    @Override
    public boolean checkIfExists(@NotNull Integer id) {
        return userRepository.existsById(id);
    }

    @Transactional(propagation = REQUIRED)
    public UserDTO save(UserDTO userDto) {

       userDto.getAddresses().stream()
                .filter(addr -> !addressService.checkIfExists(addr.getId()))
                .forEach(addressDTO -> addressService.save(addressDTO));

     if(nonNull(userDto.getId())){
          //todo ex handler
          throw new RuntimeException();
      }
      User userToPersist=userDTOMapper.toEntity(userDto);

      return userDTOMapper.toDto(userRepository.save(userToPersist));

    }
    @Transactional(readOnly = true, propagation = SUPPORTS, isolation = READ_UNCOMMITTED)
    public UserDTO findById(Integer id) {
        //todo custom ex handler
        return userRepository.findById(id)
                .map(userDTOMapper::toDto)
                .orElseThrow(() -> new RuntimeException(" "));
    }
}
