package com.socket_testing.socket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MESSAGE_BOARD")
public class MessageBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "message_board_name")
    private String messageBoardName;

    @Column(name = "message_board_description")
    private String messageBoardDescription;

    @Column(name = "message_board_profile_image")
    private byte[] messageBoardProfileImage;

    @Column(name = "message_board_banner_image")
    private byte[] messageBoardBannerImage;

    @OneToMany(mappedBy = "messageBoard", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Message> messages = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "message_board_users",
            joinColumns = @JoinColumn(name = "message_board_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();


}
