package com.addressbook.service;

import com.addressbook.domain.model.Country;
import com.addressbook.repository.CountryRepository;
import com.addressbook.service.dto.CountryDTO;
import com.addressbook.service.mapper.CountryDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@Transactional
public class CountryService implements IService {

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private CountryDTOMapper countryDTOMapper;

    @Override
    public boolean checkIfExists(@NotNull Integer id) {
        return countryRepository.existsById(id);
    }

    @Transactional(propagation = REQUIRED)
    public CountryDTO save(CountryDTO countryDTO) {
        Country country = countryRepository.save(countryDTOMapper.toEntity(countryDTO));

        return countryDTOMapper.toDto(country);
    }


}
