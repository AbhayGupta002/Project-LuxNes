package com.example.demo.controller;

import com.example.demo.dto.ErrorDetails;
import com.example.demo.dto.HotelDto;
import com.example.demo.dto.Response;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.HotelDetails;
import com.example.demo.entity.UserDetails;
import com.example.demo.entity.UserLogin;
import com.example.demo.repository.HotelDetailsRepository;
import com.example.demo.repository.UserDetailsRepository;
import com.example.demo.repository.UserLoginRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public class LoginController {
    @Autowired
    private UserDetailsRepository userDetailsRepo;

    @Autowired
    private UserLoginRepository userLoginRepo;

    @Autowired
    private HotelDetailsRepository hotelDetailsRepo;

    @PostMapping(value = "userregister")
    public ResponseEntity<Response> createUser(@RequestBody UserDto user){
        UserDetails userDetails = new UserDetails();

        userDetails.setName(user.getName());
        userDetails.setPassword(user.getPassword());
        userDetails.setGmail(user.getGmail());
        userDetails.setContactNumber(user.getContactNumber());
        Response response = new Response();
        Optional<UserLogin> existing = userLoginRepo.findByGmail(user.getGmail());
        if (existing.isPresent()) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_IMPLEMENTED,
                    "user already exist with this id" + user.getGmail());
            return ResponseEntity.ofNullable(response);
        }
        UserService userService = new UserService();
        userDetails.setUserId(userService.generateUniqueRegistrationId());
        userLoginRepo.save(userDetails);

        UserLogin login = new UserLogin();
        login.setEmail(user.getGmail());
        login.setPassword(user.getPassword());
        userLoginRepo.save(login);

        UserDto dto = new UserDto();
        dto.setUserId(userDetails.getUserId());
        dto.setName(userDetails.getName());
        dto.setGmail(userDetails.getGmail());
        dto.setContactNumber(userDetails.getContactNumber());
        response.setData(dto);
        return ResponseEntity.ofNullable(response);
    }

    @PostMapping(value = "hotelregister")
    public ResponseEntity<Response> registerHotel(@RequestBody HotelDto hotelDto){
        HotelDetails hotelDetails = new HotelDetails();
        hotelDetails.setHotelName(hotelDto.getHotelName());
        hotelDetails.setGmail(hotelDto.getGmail());
        hotelDetails.setPhoneNumber(hotelDto.getPhoneNumber());
        hotelDetails.setCity(hotelDto.getCity());
        hotelDetails.setHotelType(hotelDto.getHotelType());
        hotelDetails.setTotalRooms(hotelDto.getTotalRoom());
        hotelDetails.setLocation(hotelDto.getLocation());
        hotelDetails.setAvailableRooms(hotelDto.getAvailableRooms());

        Response response = new Response();
        Optional<UserLogin> existing = userLoginRepo.findByGmail(hotelDto.getGmail());
        if (existing.isPresent()) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.ALREADY_REPORTED,
                    "user already exist with this id" + hotelDto.getGmail());
            return ResponseEntity.ofNullable(response);
        }
        UserService userService = new UserService();
        hotelDetails.setRegistrationId(userService.generateUniqueRegistrationId());
        hotelDetailsRepo.save(hotelDetails);

        UserLogin login = new UserLogin();
        login.setEmail(hotelDto.getGmail());
        login.setPassword(hotelDto.getPassword());
        hotelDetailsRepo.save(login);

        HotelDto dto = new HotelDto();
        dto.setUserId(login.getUserId());
        dto.setGmail(login.getEmail());
        dto.setPassword(login.getPassword());
        response.setData(dto);
        return ResponseEntity.ofNullable(response);
    }

}
