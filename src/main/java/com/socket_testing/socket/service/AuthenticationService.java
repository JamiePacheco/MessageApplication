package com.socket_testing.socket.service;

import com.socket_testing.socket.model.User;
import com.socket_testing.socket.model.enums.Roles;
import com.socket_testing.socket.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @NonNull
    private UserRepository userRepository;

    @NonNull
    private PasswordEncoder passwordEncoder;


    public User createNewUser(User user) {

        // check if username exists already
        if (userRepository.findUserByUsername(user.getUsername()) != null) {
            throw new RuntimeException("Username unavailable");
        }

        //default user role
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(Roles.ROLE_USER);
        User newUser = userRepository.save(user);
        return newUser;
    }
}
