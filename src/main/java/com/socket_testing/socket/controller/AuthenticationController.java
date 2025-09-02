package com.socket_testing.socket.controller;

import com.socket_testing.socket.model.Response;
import com.socket_testing.socket.service.UserService;
import com.socket_testing.socket.utility.JwtUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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
    private final UserService userService;

    @PostMapping("")
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
