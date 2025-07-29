package com.example.demo.controller;

import com.example.demo.dto.ErrorDetails;

import com.example.demo.dto.Response;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserDetails;
import com.example.demo.entity.UserLogin;
import com.example.demo.repository.BookingDetailsRepository;
import com.example.demo.repository.HotelDetailsRepository;
import com.example.demo.repository.UserDetailsRepository;
import com.example.demo.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    private UserDetailsRepository userDetailsRepo;

    @Autowired
    private UserLoginRepository userLoginRepo;

    @Autowired
    private HotelDetailsRepository hotelDetailsRepo;

    @Autowired
    private BookingDetailsRepository bookingDetailsRepo;

    @PostMapping(value = "userregister")
    public ResponseEntity<Response> createUser(@RequestBody UserDto user){
        UserDetails userDetails = new UserDetails();

        UserLogin login =new UserLogin();
        String userId = UUID.randomUUID().toString().concat("USR");
        userDetails.setUserId(userId);
        userDetails.setName(user.getName());
        login.setPassword(user.getPassword());
        userDetails.setGmail(user.getGmail());
        userDetails.setContactNumber(user.getContactNumber());
        Response response = new Response();
        Optional<UserLogin> existing = userLoginRepo.findByGmail(user.getGmail());
        if (existing.isPresent()) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_IMPLEMENTED,
                    "user already exist with this id" + user.getGmail());
            response.setError(errorDetails);
            return ResponseEntity.ofNullable(response);
        }
        userDetailsRepo.save(userDetails);

        UserLogin logincheck = new UserLogin();
        logincheck.setGmail(user.getGmail());
        logincheck.setPassword(user.getPassword());
        userLoginRepo.save(logincheck);

        UserDto dto = new UserDto();
        dto.setUserId(userDetails.getUserId());
        dto.setName(userDetails.getName());
        dto.setGmail(userDetails.getGmail());
        dto.setContactNumber(userDetails.getContactNumber());
        response.setData(dto);
        return ResponseEntity.ofNullable(response);
    }



    @GetMapping(value = "/")
    public ResponseEntity<?> getUser (@RequestBody UserDto userDto) {
        Response response = new Response();
        Optional<UserDetails> existing = userDetailsRepo.findById(userDto.getUserId());
        if(existing.isEmpty()){

            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND,
                    "User not found for id: " + userDto.getUserId());
            response.setError(errorDetails);
            return ResponseEntity.ofNullable(response);
        }
        UserDto user = new UserDto();
        UserDetails userDetails = userDetailsRepo.findById(userDto.getUserId()).orElseThrow(() ->
                new RuntimeException("User not found with id " + userDto.getUserId()));
        user.setName(userDetails.getName());
        user.setContactNumber(userDetails.getContactNumber());
        user.setGmail(userDetails.getGmail());
        response.setData(user);
        return ResponseEntity.ofNullable(response);
    }

    @GetMapping(value = "getpassword")
    public ResponseEntity<?> getPassword(@RequestBody UserDto userDto){
        Response response = new Response();
        UserDto userdto = new UserDto();
        Optional<UserLogin> login = userLoginRepo.findById(userDto.getUserId());
        if(login.isEmpty()){

            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND,
                    "User not found for id: " + userDto.getUserId());
            response.setError(errorDetails);
            return ResponseEntity.ofNullable(response);
        }
        UserLogin userlogin = new UserLogin();
        userdto.setPassword(userlogin.getPassword());
        response.setData(userdto);
        return ResponseEntity.ofNullable(response);
    }

}
