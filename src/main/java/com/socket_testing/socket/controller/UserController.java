package com.socket_testing.socket.controller;

import com.socket_testing.socket.model.Response;
import com.socket_testing.socket.model.User;
import com.socket_testing.socket.model.dto.UserDTO;
import com.socket_testing.socket.service.UserService;
import com.socket_testing.socket.utility.JwtUtil;
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

    @NonNull
    private JwtUtil jwtUtil;

    @GetMapping("")
    public ResponseEntity<Response<UserDTO>> getUser(@RequestHeader(name = "Authorization") String token) {
            try {
                String string = jwtUtil.extractUsername(token.substring(7));
                User user = userService.getUser(string);
                UserDTO userDTO = new UserDTO(user);
                return ResponseEntity.ok(
                        Response.<UserDTO>builder()
                                .message("Successfully retrieved user account")
                                .responseContent(userDTO)
                                .status(HttpStatus.ACCEPTED)
                                .build()
                );
            } catch (Exception ex) {
                return ResponseEntity.badRequest().body(
                        Response.<UserDTO>builder()
                                .message("Error retrieving user account: " + ex.getMessage())
                                .responseContent(null)
                                .status(HttpStatus.BAD_REQUEST)
                                .build()
                );
            }
    }

    @GetMapping("/users")
    public ResponseEntity<Response<List<UserDTO>>> getUsers(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        try {
            List<UserDTO> usersDTOs = userService.getUsers(pageNum, pageSize);
            return ResponseEntity.ok(
                    Response.<List<UserDTO>>builder()
                            .message("Successfully retrieved users")
                            .responseContent(usersDTOs)
                            .status(HttpStatus.ACCEPTED)
                            .build()
            );
        } catch (Exception ex) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            Response.<List<UserDTO>>builder()
                                    .message(ex.getMessage())
                                    .responseContent(null)
                                    .status(HttpStatus.CONFLICT)
                                    .build()
                    );
        }
    }

    @PutMapping
    public ResponseEntity<Response<UserDTO>> updateUser(@RequestBody User user) {
        try {
            UserDTO updateUser = userService.updateUser(user);
            return ResponseEntity.ok(
                    Response.<UserDTO>builder()
                            .message("Successfully updated user")
                            .responseContent(updateUser)
                            .status(HttpStatus.ACCEPTED)
                            .build()
            );
        } catch (Exception ex) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            Response.<UserDTO>builder()
                                    .message(ex.getMessage())
                                    .responseContent(null)
                                    .status(HttpStatus.CONFLICT)
                                    .build()
                    );
        }
    }
}
