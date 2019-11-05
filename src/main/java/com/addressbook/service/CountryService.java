package com.addressbook.service;

import com.addressbook.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class CountryService implements IService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public boolean checkIfExists(@NotNull Integer id) {
        return countryRepository.existsById(id);
    }


}
