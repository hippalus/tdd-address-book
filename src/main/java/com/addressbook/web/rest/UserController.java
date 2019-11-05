package com.addressbook.web.rest;

import com.addressbook.service.UserService;
import com.addressbook.service.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/v1/users")
    public ResponseEntity<UserDTO> createProduct(@RequestBody UserDTO userDTO)
            throws URISyntaxException
    {
        log.debug("REST request to save User : {}", userDTO);

        UserDTO result = userService.save(userDTO);

        return ResponseEntity
                .created(new URI("/api/v1/users/"+result.getId()))
                .body(result);
    }

    @GetMapping("/api/v1/users/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer id)
    {
        return ResponseEntity.ok(userService.findById(id));
    }




}
