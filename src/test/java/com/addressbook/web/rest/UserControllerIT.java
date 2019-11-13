package com.addressbook.web.rest;

import com.addressbook.AddressBookApplication;
import com.addressbook.domain.model.Address;
import com.addressbook.domain.model.Country;
import com.addressbook.domain.model.User;
import com.addressbook.domain.model.ZipCode;
import com.addressbook.repository.AddressRepository;
import com.addressbook.repository.CountryRepository;
import com.addressbook.repository.UserRepository;
import com.addressbook.repository.ZipCodeRepository;
import com.addressbook.service.BeanUtils;
import com.addressbook.service.UserService;
import com.addressbook.service.dto.UserDTO;
import com.addressbook.service.mapper.UserDTOMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AddressBookApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIT {

    @Autowired
    private UserService userService;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDTOMapper userDTOMapper;

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ZipCodeRepository zipCodeRepository;

    private MockMvc restMockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UserController userController = new UserController(userService);
        this.restMockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    @Transactional
    public void should_create_user() throws Exception {
        //GIVEN
        Country country = BeanUtils.createRandomCountryAndSave(countryRepository);
        ZipCode zipCode = BeanUtils.createRandomZipCodeAndSave(zipCodeRepository, country);
        Address address = BeanUtils.createRandomAddressAndSave(addressRepository, zipCode);
        User user = BeanUtils.createRandomUser(address);


        UserDTO userDTO = userDTOMapper.toDto(user);

        //WHEN
        ResultActions result = restMockMvc.perform(post("/users/")
                .contentType(APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(userDTO)));


        //THEN
        result.andExpect(status().isCreated())
                .andExpect(content().json(TestUtil.convertObjectToJsonString(userDTO)));
    }

    @Test
    @Transactional
    @Disabled
    public void should_return_404_for_non_existing_user() throws Exception {
        //GIVEN
        Country country = BeanUtils.createRandomCountryAndSave(countryRepository);
        ZipCode zipCode = BeanUtils.createRandomZipCodeAndSave(zipCodeRepository, country);
        Address address = BeanUtils.createRandomAddressAndSave(addressRepository, zipCode);

        User user = BeanUtils.createRandomUser(address);
        Set<Address> addressSet = new HashSet<>();
        addressSet.add(address);
        user.setAddresses(addressSet);
        user.setId(new Random().nextInt());

        //WHEN
        ResultActions result =   restMockMvc.perform(get("/users/" + user.getId()));

        //THEN
        //todo refactor to find by id method in user service :custom exception handler notfound : should be change Runtime Exception
        result.andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void should_find_user_by_id() throws Exception {
        //GIVEN
        Country country = BeanUtils.createRandomCountryAndSave(countryRepository);
        ZipCode zipCode = BeanUtils.createRandomZipCodeAndSave(zipCodeRepository, country);
        Address address = BeanUtils.createRandomAddressAndSave(addressRepository, zipCode);

        Set<Address> addressSet = new HashSet<>();
        addressSet.add(address);
        User user = BeanUtils.createRandomUser(address);
        user.setAddresses(addressSet);
        user.setId(null);

        user = userRepository.save(user);

        //WHEN
        ResultActions result = restMockMvc.perform(get("/users/" + user.getId()));

        //THEN
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName")
                        .value(equalTo("Isler")));
    }

    @Test
    @Transactional
    public void should_find_all_users() throws Exception {
        List<UserDTO> actualUserDTOS = new ArrayList<>();
        //GIVEN
        for (int i = 0; i < 5; i++) {
            Country country = BeanUtils.createRandomCountryAndSave(countryRepository);
            ZipCode zipCode = BeanUtils.createRandomZipCodeAndSave(zipCodeRepository, country);
            Address address = BeanUtils.createRandomAddressAndSave(addressRepository, zipCode);

            Set<Address> addressSet = new HashSet<>();
            addressSet.add(address);
            User user = BeanUtils.createRandomUser(address);
            user.setAddresses(addressSet);
            user.setId(null);
            actualUserDTOS.add(userDTOMapper.toDto(userRepository.save(user)));
        }

        //WHEN
        ResultActions result = restMockMvc.perform(get("/users/"));

        //THEN
        result.andExpect(status().isOk())
                .andExpect(content().json(TestUtil.convertObjectToJsonString(actualUserDTOS)));
    }

}
