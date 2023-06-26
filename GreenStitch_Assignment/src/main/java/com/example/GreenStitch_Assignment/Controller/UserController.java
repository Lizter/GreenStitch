package com.example.GreenStitch_Assignment.Controller;

import com.example.GreenStitch_Assignment.Dto.AuthenticationRequest;
import com.example.GreenStitch_Assignment.Dto.AuthenticationResponse;
import com.example.GreenStitch_Assignment.Dto.UserDto;
import com.example.GreenStitch_Assignment.Entity.User;
import com.example.GreenStitch_Assignment.Security.JwtTokenProvider;
import com.example.GreenStitch_Assignment.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserDto userDTO) {
        User existingUser = userService.findByUsername(userDTO.getUsername());
        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        userService.register(user);

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            String username = String.valueOf(authenticationRequest.getUsername());
            User user = userService.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }
            String token = jwtTokenProvider.generateToken(user);

            return ResponseEntity.ok(new AuthenticationResponse(token));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

}
