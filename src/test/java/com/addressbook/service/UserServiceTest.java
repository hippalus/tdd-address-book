package com.addressbook.service;

import com.addressbook.config.BaseMockitoTest;
import com.addressbook.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class UserServiceTest extends BaseMockitoTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;


    @Test
    void should_save_product() {
    }

}
