package com.socket_testing.socket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    // TODO change this when implementing proper spring security
    @Column(name = "password")
    private String password;

    @Column(name = "biography", length = 2000)
    private String biography;

    @Column(name = "profilePicture")
    private byte[] profilePicture;

    @Column(name = "owned_message_boards")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<MessageBoard> ownedMessageBoards = new HashSet<>();

    @ManyToMany(mappedBy = "boards")
    private Set<MessageBoard> messageBoards = new HashSet<>();

    @Column(name = "messages")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Message> messages = new HashSet<>();
}
