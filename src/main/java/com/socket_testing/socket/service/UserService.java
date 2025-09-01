package com.socket_testing.socket.service;

import com.socket_testing.socket.model.User;
import com.socket_testing.socket.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

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

    public User authenticateUser(String username, String password) {
        User user = getUser(username);

        if (user == null) {
            throw new RuntimeException("Username does not exist");
        }

        if (user.getPassword().equals(password)) {
            return user;
        } else {
            throw new RuntimeException("Incorrect Password");
        }
    }

    public User createNewUser(User user) {
        User newUser = userRepository.save(user);
        return newUser;
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

}
