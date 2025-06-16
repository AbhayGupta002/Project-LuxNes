package com.example.demo.service;

import com.example.demo.dto.ErrorDetails;
import com.example.demo.dto.LocationDto;
import com.example.demo.dto.Response;
import com.example.demo.entity.UserDetails;
import com.example.demo.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserService {

    @Autowired
    private UserDetailsRepository userRepo;

    public ResponseEntity<?> updateLocation(Long id, LocationDto locationDto) {
        Response response =new Response();
        Optional<UserDetails> userDetailsOptional = userRepo.findById(id);
        if (!userDetailsOptional.isPresent()){
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST,
                    "please check your internet");
        }
        UserDetails user =new UserDetails();
        user.setLatitude(locationDto.getLatitude());
        user.setLongitude(locationDto.getLongitude());
        userRepo.save(user);
        response.setData(user);
        return ResponseEntity.ofNullable(response);
    }

    public Long generateUniqueRegistrationId() {
        Long registrationId;
        do {
            registrationId = (long) ThreadLocalRandom.current().nextInt(1000, 999999999);
        } while (userRepo.existsById(registrationId));
        return registrationId;
    }

    public UserDetails registerUser(UserDetails user) {
        user.setId(generateUniqueRegistrationId());
        return userRepo.save(user);
    }
}
