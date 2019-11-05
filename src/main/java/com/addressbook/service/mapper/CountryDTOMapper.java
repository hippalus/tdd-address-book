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
    @Autowired
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
        country.setId(country.getId());
        country.setCode(country.getCode());
        country.setName(country.getName());
        country.setDialCode(country.getDialCode());
        return countryDTO;
    }
}
