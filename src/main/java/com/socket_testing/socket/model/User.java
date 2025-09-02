package com.socket_testing.socket.model;

import com.socket_testing.socket.model.enums.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_USER")
public class User implements UserDetails {

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
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<MessageBoard> ownedMessageBoards = new HashSet<>();

    @ManyToMany(mappedBy = "users")
    private Set<MessageBoard> messageBoards = new HashSet<>();

    @Column(name = "messages")
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Message> messages = new HashSet<>();

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Roles role;

    //TODO finish implementing custom roles (or remove them idk)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(() -> role.name());
    }
}
