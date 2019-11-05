package com.addressbook.service.mapper;

import com.addressbook.domain.model.ZipCode;
import com.addressbook.domain.validation.BeanValidator;
import com.addressbook.service.dto.ZipCodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ZipCodeDTOMapper {

    @Autowired
    private BeanValidator validator;
    @Autowired
    private CountryDTOMapper countryDTOMapper;
    @Autowired
    public ZipCodeDTOMapper(BeanValidator validator, CountryDTOMapper countryDTOMapper) {
        this.validator=validator;
        this.countryDTOMapper=countryDTOMapper;
    }

    public ZipCode toEntity(ZipCodeDTO zipCodeDTO){
        return ZipCode.aNew()
                .withId(zipCodeDTO.getId())
                .withProvince(zipCodeDTO.getProvince())
                .withDistrict(zipCodeDTO.getDistrict())
                .withPostalCode(zipCodeDTO.getPostalCode())
                .withCountry(countryDTOMapper.toEntity(zipCodeDTO.getCountry()))
                .withStreet(zipCodeDTO.getStreet())
                .withRegion(zipCodeDTO.getRegion())
                .validateAndGet(validator);
    }

    public ZipCodeDTO toDto(ZipCode zipCode) {
        ZipCodeDTO zipCodeDTO=new ZipCodeDTO();
        zipCodeDTO.setId(zipCode.getId());
        zipCodeDTO.setPostalCode(zipCode.getPostalCode());
        zipCodeDTO.setProvince(zipCode.getProvince());
        zipCodeDTO.setDistrict(zipCode.getDistrict());
        zipCodeDTO.setStreet(zipCode.getStreet());
        zipCodeDTO.setRegion(zipCode.getRegion());
        zipCodeDTO.setCountry(countryDTOMapper.toDto(zipCode.getCountry()));
        return zipCodeDTO;
    }
}
