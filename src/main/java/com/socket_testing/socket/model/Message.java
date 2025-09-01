package com.socket_testing.socket.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(name = "author")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    @NonNull
    @Column(name = "author")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_board_id")
    private MessageBoard messageBoard;

    @NonNull
    @Column(name = "message", length = 1000)
    private String message;

    @Nullable
    @Column(name = "image")
    private Byte[] image;

    @NonNull
    @Column(name = "timestamp")
    private Instant timestamp;
}