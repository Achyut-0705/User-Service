package com.e_job_search.User_Service.controller;

import com.e_job_search.User_Service.dto.UserDto;
import com.e_job_search.User_Service.model.User;
import com.e_job_search.User_Service.services.UserService;
import jakarta.websocket.server.PathParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers() { // TODO: add filters, limits, page number and offset
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathParam(value = "id") String id) {
        return ResponseEntity.ok(service.getUserById(id));
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(service.getUserByEmail(email));
    }

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) throws Exception {
        UserDto user = service.createUser(userDto);

        return ResponseEntity.status(201).body(user);
    }

    @PatchMapping("/user")
    public ResponseEntity<UserDto> updateUser(@RequestBody User user) {
        return ResponseEntity.ok(service.updateUser(user));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String id) {
        return ResponseEntity.ok(service.deleteUserById(id));
    }
}
