package com.addressbook.service;

import com.addressbook.repository.ZipCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class ZipCodeService implements IService {
    @Autowired
    private ZipCodeRepository zipCodeRepository;

    @Override
    public boolean checkIfExists(@NotNull Integer id) {
        return zipCodeRepository.existsById(id);
    }
}
