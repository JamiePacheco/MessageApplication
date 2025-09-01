package com.socket_testing.socket.model;
import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {

    private HttpStatus status;
    private String message;
    private T responseContent;


}
