package com.example.GreenStitch_Assignment.Controller;


import com.example.GreenStitch_Assignment.Dto.AuthenticationRequest;
import com.example.GreenStitch_Assignment.Dto.AuthenticationResponse;
import com.example.GreenStitch_Assignment.Security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
            String token = jwtTokenProvider.generateToken(authenticationRequest.getUsername());
            AuthenticationResponse response = new AuthenticationResponse(token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}

