package com.addressbook.service.mapper;

import com.addressbook.config.BaseMockitoTest;
import com.addressbook.domain.model.Address;
import com.addressbook.domain.model.User;
import com.addressbook.domain.validation.BeanValidator;
import com.addressbook.service.BeanUtils;
import com.addressbook.service.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;


import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class UserDTOMapperTest extends BaseMockitoTest {
    @Spy
    private UserDTOMapper userDTOMapper =new UserDTOMapper(new BeanValidator(),new AddressDTOMapper(new BeanValidator(),new ZipCodeDTOMapper(new BeanValidator(),new CountryDTOMapper(new BeanValidator()))));

    @Spy
    private BeanValidator validator;


    @Test
    void should_convert_dto_to_entity() {

        UserDTO userDTO = BeanUtils.createUserDTO(12);

        User user = userDTOMapper.toEntity(userDTO);

        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getId(),userDTO.getId());
        assertEquals(user.getAddresses().size(),userDTO.getAddresses().size());
        assertEquals(user.getBirthDate(),userDTO.getBirthDate());
        assertEquals(user.getFirstName(),userDTO.getFirstName());
        assertEquals(user.getPhoneNumber(),userDTO.getPhoneNumber());

    }

    @Test
    void should_convert_entity_to_dto() {

        User user = BeanUtils.createUserEntity(45);

        UserDTO userDTO = userDTOMapper.toDto(user);

        assertEquals(userDTO.getId(),user.getId());
        assertEquals(userDTO.getAddresses().size(),user.getAddresses().size());
        assertEquals(userDTO.getBirthDate(),user.getBirthDate());
        assertEquals(userDTO.getFirstName(),user.getFirstName());
        assertEquals(userDTO.getPhoneNumber(),user.getPhoneNumber());

    }
    @Test
     void should_not_validate_user()
    {
        UserDTO dto =BeanUtils.createUserDTO(85);
        dto.setEmailAddress("invalid");
        dto.setAddresses(null);

        assertThrows(NullPointerException.class,()->userDTOMapper.toEntity(dto));
      
    }
}