package com.addressbook.web.rest;

import com.addressbook.service.AddressService;
import com.addressbook.service.dto.AddressDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class AddressController {
    private final Logger log = LoggerFactory.getLogger(AddressController.class);

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }
    @PostMapping("/api/v1/addresses")
    public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDTO) throws URISyntaxException
    {
        log.debug("REST request to save Address : {}", addressDTO);

        AddressDTO result = addressService.save(addressDTO);

        return ResponseEntity
                .created(new URI("/api/v1/addresses/"+result.getId()))
                .body(result);
    }

    @GetMapping("/api/v1/addresses/{id}")
    public ResponseEntity<AddressDTO> getAddress(@PathVariable Integer id)
    {
        return ResponseEntity.ok(addressService.findById(id));
    }

}
