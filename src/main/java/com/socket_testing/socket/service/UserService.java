package com.socket_testing.socket.service;

import com.socket_testing.socket.model.User;
import com.socket_testing.socket.model.enums.Roles;
import com.socket_testing.socket.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    @NonNull
    private UserRepository userRepository;


    public User getUser(String username) {
        return userRepository.findUserByUsername(username);
    }

    public List<User> getUsers(int pageNum, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        Page page = userRepository.findAll(pageRequest);
        return page.get().toList();
    }

    public User updateUser(User user) {
        User savedUser = userRepository.findUserByUsername(user.getUsername());
        savedUser.setUsername(user.getUsername());
        savedUser.setPassword(user.getPassword());
        savedUser.setBiography(user.getBiography());
        savedUser.setMessageBoards(user.getMessageBoards());
        savedUser.setMessages(user.getMessages());
        savedUser.setOwnedMessageBoards(user.getOwnedMessageBoards());

        User updatedUser = userRepository.save(savedUser);
        return updatedUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User  user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
}
