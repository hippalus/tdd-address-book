package com.addressbook.service;

import com.addressbook.domain.model.ZipCode;
import com.addressbook.repository.ZipCodeRepository;
import com.addressbook.service.dto.CountryDTO;
import com.addressbook.service.dto.ZipCodeDTO;
import com.addressbook.service.mapper.ZipCodeDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Service
@Transactional
public class ZipCodeService implements IService {
    @Autowired
    private ZipCodeRepository zipCodeRepository;

    @Autowired
    private CountryService countryService;

    @Autowired
    private ZipCodeDTOMapper zipCodeDTOMapper;

    @Override
    public boolean checkIfExists(@NotNull Integer id) {
        return zipCodeRepository.existsById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ZipCodeDTO save(ZipCodeDTO zipCodeDTO) {

        if (!countryService.checkIfExists(zipCodeDTO.getCountry().getId())) {
            CountryDTO countryDTO=countryService.save(zipCodeDTO.getCountry());
            zipCodeDTO.setCountry(countryDTO);

        }
        ZipCode zipCode = zipCodeDTOMapper.toEntity(zipCodeDTO);
        return zipCodeDTOMapper.toDto(zipCodeRepository.save(zipCode));
    }

}
