package com.example.user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("management/api/users")
public class UserManagementController {

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

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public List<User> getAllUsers(){
        return USERS;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:write')")
    public void addUser(@RequestBody User user){
        System.out.println(user);
    }
    @DeleteMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('user:write')")
    public void deleteUser(@PathVariable("userId") UUID userId) {
        System.out.println(userId);
    }

    @PreAuthorize("hasAuthority('user:write')")
    @PutMapping(path="{userId}")
    public void updateUser(@PathVariable("userId") UUID userId, @RequestBody User user){
        System.out.println(user);
    }


}
