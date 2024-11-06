package com.e_job_search.User_Service.services;

import com.e_job_search.User_Service.dto.UserDto;
import com.e_job_search.User_Service.model.User;
import com.e_job_search.User_Service.repositories.UserRepo;
import com.e_job_search.User_Service.requests.AuthServiceClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthServiceClient authServiceClient;

    public String generateHashPassword(String password) {
        ResponseEntity<String> hashPasswordResponse = authServiceClient.generateHashPassword(password);

        return hashPasswordResponse.getBody();
    }

    public UserDto createUser(UserDto userDto) throws Exception {
        User user = modelMapper.map(userDto, User.class);

        String hashedPassword = generateHashPassword(user.getPassword());
        user.setPassword(hashedPassword);

        return modelMapper.map(repo.save(user), UserDto.class);
    }

    public UserDto getUserById(String id) {
        User user = repo.findById(id).orElse(null);

        return modelMapper.map(user, UserDto.class);
    }

    public UserDto getUserByEmail(String email) {
        User user = repo.findByEmail(email).orElseThrow();

        return modelMapper.map(user, UserDto.class);
    }

    public List<UserDto> getAllUsers() {
        return repo.findAll().stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
    }

    public UserDto updateUser(User userDto) {
        User user = modelMapper.map(userDto, User.class);

        if (user.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User id not found");
        }

        boolean userExists = repo.existsById(user.getId());

        if (!userExists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        return modelMapper.map(repo.save(user), UserDto.class);
    }

    public boolean deleteUser(User user) {
        if (user.getId() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User id not found");

        boolean userExists = repo.existsById(user.getId());

        if (!userExists)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User details not found");

        repo.deleteById(user.getId());

        return true;
    }

    public boolean deleteUserById(String id) {
        boolean userExists = repo.existsById(id);

        if (!userExists)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exists");

        repo.deleteById(id);
        return true;
    }
}
