package com.example.demo.controller;

import com.example.demo.dto.Response;
import com.example.demo.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class LoginController {

    @PostMapping(value = "reggister")
    public ResponseEntity<Response> createUser(@RequestBody UserDto user){

        return ;
    }
}
