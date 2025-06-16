package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @PostMapping("/register")
    public String register(@RequestBody String body) {
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody String body) {
        return "Login successful";
    }
}
