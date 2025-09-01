package com.socket_testing.socket.controller;

import com.socket_testing.socket.messages.GreetingResponse;
import com.socket_testing.socket.messages.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public GreetingResponse greeting(@Payload HelloMessage message) throws Exception {
        return new GreetingResponse("Hello, " + message.getName());
    }

}
