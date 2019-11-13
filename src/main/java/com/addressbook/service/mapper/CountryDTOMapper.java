package com.addressbook.service.mapper;

import com.addressbook.domain.model.Country;
import com.addressbook.domain.validation.BeanValidator;
import com.addressbook.service.dto.CountryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountryDTOMapper {

    @Autowired
    private BeanValidator validator;

    public CountryDTOMapper(BeanValidator beanValidator) {
        this.validator = beanValidator;
    }

    public Country toEntity(CountryDTO countryDTO) {
        return Country.aNew()
                .withId(countryDTO.getId())
                .withName(countryDTO.getName())
                .withCode(countryDTO.getCode())
                .withDialCode(countryDTO.getDialCode())
                .validateAndGet(validator);
    }

    public CountryDTO toDto(Country country) {
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setId(country.getId());
        countryDTO.setCode(country.getCode());
        countryDTO.setName(country.getName());
        countryDTO.setDialCode(country.getDialCode());
        return countryDTO;
    }
}
