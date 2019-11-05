package com.addressbook.service;

import com.addressbook.config.BaseMockitoTest;
import com.addressbook.domain.model.User;
import com.addressbook.domain.model.ZipCode;
import com.addressbook.repository.UserRepository;
import com.addressbook.service.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AssertionsKt;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

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

    @Test
    void should_save_user() {
        User user=BeanUtils.createUserEntity(1);

        lenient().when(addressService.checkIfExists(1)).thenReturn(true);
        lenient().when(zipCodeService.checkIfExists(1)).thenReturn(true);

        lenient().when(userRepository.save(any(User.class))).thenReturn(user);
        UserDTO dto=BeanUtils.createUserDTO(null);

        UserDTO persistedUserDto=userService.save(dto);

        assertThat(persistedUserDto)
                .isEqualToComparingOnlyGivenFields
                        (user, "id",
                                "firstName","lastName","emailAddress","birthDate","phoneNumber");
    }

    @Test
    void should_find_user() {
        User user=BeanUtils.createUserEntity(1);
        UserDTO dtoOfPersisted=BeanUtils.createUserDTO(1);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        UserDTO persistedUserDTO = userService.findById(1);

        assertThat(persistedUserDTO)
                .isEqualToComparingOnlyGivenFields
                        (dtoOfPersisted, "id",
                                "firstName","lastName","emailAddress","birthDate","phoneNumber");
    }




}
