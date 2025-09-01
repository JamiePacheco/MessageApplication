package com.socket_testing.socket.messages;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class HelloMessage {

    @NonNull
    private String name;

}
