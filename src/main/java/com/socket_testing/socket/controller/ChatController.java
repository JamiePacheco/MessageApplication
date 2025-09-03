package com.socket_testing.socket.controller;

import com.socket_testing.socket.messages.ChatResponse;
import com.socket_testing.socket.messages.GreetingResponse;
import com.socket_testing.socket.messages.HelloMessage;
import com.socket_testing.socket.messages.enums.ResponseType;
import com.socket_testing.socket.model.Message;
import com.socket_testing.socket.service.MessageService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.Instant;

@Controller
public class ChatController {

    private MessageService messageService;

    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/chat.send/{message-board-id}")
    @SendTo("/topic/chat.{message-board-id}")
    public ChatResponse<Message> greeting(@DestinationVariable(value = "message-board-id") Long messageBoardId, @Payload Message message) {
        try {
            Message savedMessage = messageService.saveMessage(message);
            return ChatResponse.<Message>builder()
                    .payload(savedMessage)
                    .messageBoardId(messageBoardId)
                    .timestamp(Instant.now())
                    .type(ResponseType.CHAT)
                    .build();
        } catch (Exception ex) {
            return ChatResponse.<Message>builder()
                    .errorMessage(ex.getMessage())
                    .messageBoardId(messageBoardId)
                    .type(ResponseType.ERROR)
                    .timestamp(Instant.now())
                    .build();
        }
    }




}