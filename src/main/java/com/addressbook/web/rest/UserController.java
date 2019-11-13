package com.addressbook.web.rest;

import com.addressbook.service.UserService;
import com.addressbook.service.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Controller
public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO)
            throws URISyntaxException {
        log.debug("REST request to save User : {}", userDTO);

        UserDTO result = userService.save(userDTO);

        return ResponseEntity
                .created(new URI("/users/" + result.getId()))
                .body(result);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/users/")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }


}
