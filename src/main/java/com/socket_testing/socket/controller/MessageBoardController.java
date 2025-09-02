package com.socket_testing.socket.controller;


import com.socket_testing.socket.service.MessageBoardService;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/message-board")
@RequiredArgsConstructor
public class MessageBoardController {

    @NonNull
    MessageBoardService messageBoardService;

    @PostMapping()
    public





}
