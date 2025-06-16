package com.example.demo.controller;

import com.example.demo.dto.LocationDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping("/{id}/location")
    public ResponseEntity<?> updateLocation(@PathVariable Long id, @RequestBody LocationDto locationDto) {
        try {
            userService.updateLocation(id, locationDto);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("Location updated successfully");
    }
}
