package com.socket_testing.socket.controller;


import com.socket_testing.socket.model.Message;
import com.socket_testing.socket.model.Response;
import com.socket_testing.socket.service.MessageService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/message")
public class MessageController {

    @NonNull
    private MessageService messageService;

    @PostMapping()
    public ResponseEntity<Response<String>> saveMessage(@RequestBody Message message) {
        try {
            messageService.saveMessage(message);
            return ResponseEntity.ok(
                    Response.<String>builder()
                            .message("Successfully saved messages")
                            .responseContent(null)
                            .status(HttpStatus.ACCEPTED)
                            .build()
            );
        } catch (Exception ex) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            Response.<String>builder()
                                    .message(ex.getMessage())
                                    .responseContent(ex.getLocalizedMessage())
                                    .status(HttpStatus.CONFLICT)
                                    .build()
                    );
        }
    }

    @GetMapping()
    public ResponseEntity<Response<List<Message>>> getMessages(
            @RequestParam("pageNum") int pageNum,
            @RequestParam("pageSize") int pageSize
    ) {
        try {
            List<Message> messages = messageService.getMessages(pageNum, pageSize);
            return ResponseEntity.ok(
                    Response.<List<Message>>builder()
                            .message("Successfully retrieved messages")
                            .responseContent(messages)
                            .status(HttpStatus.ACCEPTED)
                            .build()
            );
        } catch (Exception ex) {
            return ResponseEntity
                    .badRequest()
                    .body(Response.<List<Message>>builder()
                            .message("Error retrieving messages: " + ex.getMessage())
                            .status(HttpStatus.BAD_REQUEST)
                            .responseContent(null)
                            .build()
                    );
        }
    }

    @GetMapping("/{user}")
    public ResponseEntity<Response<List<Message>>> getMessagesByUser(
            @RequestParam("pageNum") int pageNum,
            @RequestParam("pageSize") int pageSize,
            @PathVariable("user") String user
    ) {
        try {
            List<Message> messages = messageService.getMessagesByUser(pageNum, pageSize, user);
            return ResponseEntity.ok(
                    Response.<List<Message>>builder()
                            .message("Successfully retrieved messages from user: " + user)
                            .responseContent(messages)
                            .status(HttpStatus.ACCEPTED)
                            .build()
            );
        } catch (Exception ex) {
            return ResponseEntity
                    .badRequest()
                    .body(Response.<List<Message>>builder()
                            .message("Error retrieving messages: " + ex.getMessage())
                            .status(HttpStatus.BAD_REQUEST)
                            .responseContent(null)
                            .build()
                    );
        }
    }

    @GetMapping("/{message-board}")
    public ResponseEntity<Response<List<Message>>> getMessagesByMessageBoard(
            @RequestParam("pageNum") int pageNum,
            @RequestParam("pageSize") int pageSize,
            @PathVariable("message-board") Long messageBoardId
    ) {
        try {
            List<Message> messages = messageService.getMessagesByMessageBoard(pageNum, pageSize, messageBoardId);
            return ResponseEntity.ok(
                    Response.<List<Message>>builder()
                            .message("Successfully retrieved messages from board: " + messageBoardId)
                            .responseContent(messages)
                            .status(HttpStatus.ACCEPTED)
                            .build()
            );
        } catch (Exception ex) {
            return ResponseEntity
                    .badRequest()
                    .body(Response.<List<Message>>builder()
                            .message("Error retrieving messages from board: " + ex.getMessage())
                            .status(HttpStatus.BAD_REQUEST)
                            .responseContent(null)
                            .build()
                    );
        }
    }
}
