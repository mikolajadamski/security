package com.example.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/users")
public class UserController {

    private static final List<User> USERS = Arrays.asList(
            new User(UUID.randomUUID(), "Mike Adams"),
            new User(UUID.randomUUID(), "Lucy Grants"),
            new User(UUID.randomUUID(), "John Smith")
    );

    @GetMapping(path = "{userId}")
    public User getUser(@PathVariable("userId") UUID userId){
        return USERS.stream()
                .filter(user -> userId.equals(user.getUserId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User with id "+userId+" does not exists"));
    }

    @GetMapping()
    public List<User> getAllUsers(){
        return USERS;
    }
}
