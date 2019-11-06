package com.addressbook.service;

import com.addressbook.config.BaseMockitoTest;
import com.addressbook.domain.model.User;
import com.addressbook.domain.validation.BeanValidator;
import com.addressbook.repository.UserRepository;
import com.addressbook.service.dto.UserDTO;
import com.addressbook.service.mapper.AddressDTOMapper;
import com.addressbook.service.mapper.CountryDTOMapper;
import com.addressbook.service.mapper.UserDTOMapper;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest extends BaseMockitoTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AddressService addressService;
    @Mock
    private ZipCodeService zipCodeService;

    @Spy
    private UserDTOMapper userDTOMapper = new UserDTOMapper(new BeanValidator(), new AddressDTOMapper(new BeanValidator(), new ZipCodeDTOMapper(new BeanValidator(), new CountryDTOMapper(new BeanValidator()))));

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void should_save_user() {
        User user = BeanUtils.createUserEntity(1);

        lenient().when(addressService.checkIfExists(1)).thenReturn(true);
        lenient().when(zipCodeService.checkIfExists(1)).thenReturn(true);

        lenient().when(userRepository.save(any(User.class))).thenReturn(user);
        UserDTO dto = BeanUtils.createUserDTO(null);

        UserDTO persistedUserDto = userService.save(dto);

        assertThat(persistedUserDto)
                .isEqualToComparingOnlyGivenFields
                        (user, "id",
                                "firstName", "lastName", "emailAddress", "birthDate", "phoneNumber");
    }

    @Test
    void should_find_user() {
        User user = BeanUtils.createUserEntity(1);
        UserDTO dtoOfPersisted = BeanUtils.createUserDTO(1);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        UserDTO persistedUserDTO = userService.findById(1);

        assertThat(persistedUserDTO)
                .isEqualToComparingOnlyGivenFields
                        (dtoOfPersisted, "id",
                                "firstName", "lastName", "emailAddress", "birthDate", "phoneNumber");
    }

    @Test
    void should_find_all_users() {

        User user = BeanUtils.createUserEntity(1);
        User user2 = BeanUtils.createUserEntity(2);
        User user1 = BeanUtils.createUserEntity(3);

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user1);
        userList.add(user2);

        when(userRepository.findAll()).thenReturn(userList);

        List<UserDTO> actual = userService.findAll();

        List<UserDTO> expected = userList.stream()
                .map(user3 -> userDTOMapper.toDto(user3))
                .collect(Collectors.toList());

        Assertions.assertEquals(expected.size(), actual.size());
    }


}
