package com.socket_testing.socket.controller;

import com.socket_testing.socket.model.Response;
import com.socket_testing.socket.model.User;
import com.socket_testing.socket.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    @NonNull
    private UserService userService;

    @GetMapping()
    public ResponseEntity<Response<List<User>>> getUsers(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        try {
            List<User> users = userService.getUsers(pageNum, pageSize);
            return ResponseEntity.ok(
                    Response.<List<User>>builder()
                            .message("Successfully retrieved users")
                            .responseContent(users)
                            .status(HttpStatus.ACCEPTED)
                            .build()
            );
        } catch (Exception ex) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            Response.<List<User>>builder()
                                    .message(ex.getMessage())
                                    .responseContent(null)
                                    .status(HttpStatus.CONFLICT)
                                    .build()
                    );
        }
    }

    @PutMapping
    public ResponseEntity<Response<User>> updateUser(@RequestBody User user) {
        try {
            User newUser = userService.updateUser(user);
            return ResponseEntity.ok(
                    Response.<User>builder()
                            .message("Successfully updated user")
                            .responseContent(newUser)
                            .status(HttpStatus.ACCEPTED)
                            .build()
            );
        } catch (Exception ex) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            Response.<User>builder()
                                    .message(ex.getMessage())
                                    .responseContent(null)
                                    .status(HttpStatus.CONFLICT)
                                    .build()
                    );
        }
    }

    @GetMapping("/authenticate")
    public ResponseEntity<Response<User>> authenticateUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            User user = userService.authenticateUser(username, password);
            return ResponseEntity.ok(
                    Response.<User>builder()
                            .message("Successfully authenticated user")
                            .responseContent(user)
                            .status(HttpStatus.ACCEPTED)
                            .build()
            );
        } catch (Exception ex) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            Response.<User>builder()
                                    .message(ex.getMessage())
                                    .responseContent(null)
                                    .status(HttpStatus.CONFLICT)
                                    .build()
                    );
        }
    }




}
