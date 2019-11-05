package com.addressbook.service.mapper;


import com.addressbook.domain.model.User;
import com.addressbook.domain.validation.BeanValidator;
import com.addressbook.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class UserDTOMapper {
    private BeanValidator validator;
    private AddressDTOMapper addressDTOMapper;
    @Autowired
    public UserDTOMapper(BeanValidator validator,AddressDTOMapper addressDTOMapper) {
        this.validator=validator;
        this.addressDTOMapper=addressDTOMapper;
    }

    public User toEntity(UserDTO userDTO) {
        return User.aNew()
                .withId(userDTO.getId())
                .withFirstName(userDTO.getFirstName())
                .withLastName(userDTO.getLastName())
                .withBirthDate(userDTO.getBirthDate())
                .withEmailAddress(userDTO.getEmailAddress())
                .withPhoneNumber(userDTO.getPhoneNumber())
                .withAddress(addressDTOMapper.toEntity(userDTO.getAddresses()))
                .validateAndGet(validator);

    }

    public UserDTO toDto(User user) {
        UserDTO userDTO=new UserDTO();
        userDTO.setAddresses(user.getAddresses().stream()
                .map(address -> addressDTOMapper.toDto(address))
                .collect(Collectors.toSet()));
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmailAddress(user.getEmailAddress());
        userDTO.setBirthDate(user.getBirthDate());
        userDTO.setId(user.getId());
        return userDTO;
    }

}
