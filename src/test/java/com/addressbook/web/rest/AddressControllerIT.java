package com.addressbook.web.rest;

import com.addressbook.AddressBookApplication;
import com.addressbook.domain.model.Address;
import com.addressbook.domain.model.Country;
import com.addressbook.domain.model.ZipCode;
import com.addressbook.repository.AddressRepository;
import com.addressbook.repository.CountryRepository;
import com.addressbook.repository.ZipCodeRepository;
import com.addressbook.service.AddressService;
import com.addressbook.service.BeanUtils;
import com.addressbook.service.CountryService;
import com.addressbook.service.dto.AddressDTO;
import com.addressbook.service.mapper.AddressDTOMapper;
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

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AddressBookApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressControllerIT {

    @Autowired
    private CountryService countryService;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private AddressDTOMapper addressDTOMapper;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ZipCodeRepository zipCodeRepository;
    @Autowired
    private AddressService addressService;

    private MockMvc restMockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AddressController addressController = new AddressController(addressService);
        this.restMockMvc = MockMvcBuilders.standaloneSetup(addressController).build();
        // addressDTOMapper=new AddressDTOMapper(new BeanValidator(),new ZipCodeDTOMapper(new BeanValidator(),new CountryDTOMapper(new BeanValidator())));
    }

    @Test
    @Transactional
    @Disabled//fixme Country bean is invalid:should be  refactor BeanUtils
    public void should_create_address() throws Exception {
        //GIVEN

        Country country = BeanUtils.createRandomCountryAndSave(countryRepository);
        ZipCode zipCode = BeanUtils.createRandomZipCodeAndSave(zipCodeRepository, country);
        Address address = BeanUtils.createRandomAddressAndSave(addressRepository, zipCode);


        AddressDTO addressDTO = addressDTOMapper.toDto(address);

        ResultActions result = restMockMvc.perform(post("/api/v1/addresses/")
                .contentType(APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(addressDTO)));
        //THEN
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title")
                        .value(equalTo(address.getTitle())))
                .andExpect(jsonPath("$.addressType")
                        .value(equalTo(address.getAddressType())))
                .andExpect(jsonPath("$.addressDetail")
                        .value(equalTo(address.getAddressDetail())));
    }


    @Test
    @Transactional
    public void should_find_address_by_id() throws Exception {
        //GIVEN
        Country country = BeanUtils.createRandomCountryAndSave(countryRepository);
        ZipCode zipCode = BeanUtils.createRandomZipCodeAndSave(zipCodeRepository, country);
        Address address = BeanUtils.createRandomAddressAndSave(addressRepository, zipCode);


        Address address1 = addressRepository.save(address);

        //WHEN
        ResultActions result = restMockMvc.perform(
                get("/api/v1/addresses/" +
                        address1.getId()));

        //THEN
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.addressType")
                        .value(equalTo("HOME")));
    }

    @Test
    @Transactional
    public void should_find_all_address() throws Exception {
        List<AddressDTO> actualAddressList = new ArrayList<>();
        //GIVEN
        for (int i = 0; i < 5; i++) {
            Country country = BeanUtils.createRandomCountryAndSave(countryRepository);
            ZipCode zipCode = BeanUtils.createRandomZipCodeAndSave(zipCodeRepository, country);
            Address addr = BeanUtils.createRandomAddressAndSave(addressRepository, zipCode);
            actualAddressList.add(addressDTOMapper.toDto(addr));
        }

        //WHEN
        ResultActions result = restMockMvc.perform(get("/api/v1/addresses/"));

        //THEN
        result.andExpect(status().isOk())
                .andExpect(content().json(TestUtil.convertObjectToJsonString(actualAddressList)));
    }
}
