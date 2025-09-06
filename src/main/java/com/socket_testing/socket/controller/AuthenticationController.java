package com.socket_testing.socket.controller;

import com.socket_testing.socket.model.Response;
import com.socket_testing.socket.model.User;
import com.socket_testing.socket.service.AuthenticationService;
import com.socket_testing.socket.service.UserService;
import com.socket_testing.socket.utility.JwtUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @NonNull
    private final AuthenticationManager authenticationManager;
    @NonNull
    private final JwtUtil jwtUtil;
    @NonNull
    private final AuthenticationService authenticationService;

    @NonNull
    private final UserService userService;

    private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping()
    public ResponseEntity<Response<User>> createNewUser(@RequestBody User user) {

        logger.info("Accessed create user endpoint");

        try {
            User newUser = authenticationService.createNewUser(user);

            return ResponseEntity.ok(
                    Response.<User>builder()
                            .message("Successfully created new user")
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

    @GetMapping()
    public ResponseEntity<Response<String>> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            UserDetails userDetails = userService.loadUserByUsername(username);

            return ResponseEntity.ok(
                    Response.<String>builder()
                            .responseContent(jwtUtil.generateToken(userDetails.getUsername()))
                            .message("Successfully Authenticated User")
                            .status(HttpStatus.ACCEPTED)
                            .build()
            );
        } catch (Exception ex) {
            return ResponseEntity.badRequest()
                    .body(
                            Response.<String>builder()
                                    .message("Error Authenticating User")
                                    .responseContent(ex.getMessage())
                                    .status(HttpStatus.BAD_REQUEST)
                                    .build()
                    );
        }
    }





}
