package com.socket_testing.socket.messages;

import com.socket_testing.socket.messages.enums.ResponseType;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ChatResponse<T> {

    private T payload;

    private String message;
    private String errorMessage;
    private Long messageBoardId;
    private Instant timestamp;
    private ResponseType type;
}
