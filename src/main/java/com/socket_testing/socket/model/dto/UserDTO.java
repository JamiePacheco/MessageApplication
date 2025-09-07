package com.socket_testing.socket.model.dto;

import com.socket_testing.socket.model.Message;
import com.socket_testing.socket.model.MessageBoard;
import com.socket_testing.socket.model.User;
import com.socket_testing.socket.model.enums.Roles;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String biography;
    private Roles role;
    private byte[] profilePicture;
    private Set<MessageBoard> ownedMessageBoards;
    private Set<Message> messages;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.biography = user.getBiography();
        this.role = user.getRole();
        this.profilePicture = user.getProfilePicture();
        this.ownedMessageBoards = user.getOwnedMessageBoards();
        this.messages = user.getMessages();
    }
}
