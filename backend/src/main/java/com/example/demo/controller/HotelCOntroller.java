package com.example.demo.controller;

import com.example.demo.dto.ErrorDetails;
import com.example.demo.dto.HotelDto;
import com.example.demo.dto.Response;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.HotelDetails;
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
@RequestMapping("hotellogin")
public class HotelController {
    @Autowired
    private UserDetailsRepository userDetailsRepo;

    @Autowired
    private UserLoginRepository userLoginRepo;

    @Autowired
    private HotelDetailsRepository hotelDetailsRepo;

    @Autowired
    private BookingDetailsRepository bookingDetailsRepo;

    @PostMapping(value = "hotelregister")
    public ResponseEntity<Response> registerHotel(@RequestBody HotelDto hotelDto){

        HotelDetails hotelDetails = new HotelDetails();
        String hotelId = UUID.randomUUID().toString().concat("HOT");
        hotelDetails.setHotelId(hotelId);
        hotelDetails.setHotelName(hotelDto.getHotelName());
        hotelDetails.setGmail(hotelDto.getGmail());
        hotelDetails.setPhoneNumber(hotelDto.getPhoneNumber());
        hotelDetails.setCity(hotelDto.getCity());
        hotelDetails.setHotelType(hotelDto.getHotelType());
        hotelDetails.setLocation(hotelDto.getLocation());
        hotelDetails.setPassword(hotelDto.getPassword());
        // hotelDetails.setAvailableRooms(hotelDto.getAvailableRooms());

        Response response = new Response();
        Optional<UserLogin> existing = userLoginRepo.findByGmail(hotelDto.getGmail());
        if (existing.isPresent()) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.ALREADY_REPORTED,
                    "Hotel already exists with this ID: " + hotelDto.getGmail()+"or"+hotelDto.getHotelId());
            response.setError(errorDetails);
            return ResponseEntity.ofNullable(response);
        }

        hotelDetailsRepo.save(hotelDetails);

        UserLogin login = new UserLogin();
        login.setGmail(hotelDto.getGmail());
        login.setPassword(hotelDto.getPassword());
        userLoginRepo.save(login);

        HotelDto dto = new HotelDto();
        //dto.setUserId(login.getUserId()); // Make sure `getUserId()` exists
        dto.setHotelId(hotelDetails.getHotelId());
        dto.setGmail(login.getGmail());
        dto.setCity(hotelDetails.getCity());
        dto.setHotelName(hotelDetails.getHotelName());
        dto.setHotelType(hotelDetails.getHotelType());
        dto.setPhoneNumber(hotelDetails.getPhoneNumber());
        response.setData(dto);

        return ResponseEntity.ofNullable(response);
    }

    @GetMapping(value = "gethotel")
    public ResponseEntity<?> getHotel (@RequestBody HotelDto hotelDto) {
        Response response = new Response();
        Optional<HotelDetails> existing = hotelDetailsRepo.findById(hotelDto.getHotelId());
        if (existing.isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND,
                    "Hotel not found for this id: " + hotelDto.getHotelId());
            response.setError(errorDetails);
            return ResponseEntity.ofNullable(response);
        }
        HotelDto hotel = new HotelDto();
        HotelDetails hotelDetails = hotelDetailsRepo.findById(hotelDto.getHotelId()).orElseThrow(() ->
                new RuntimeException("User not found with id " + hotelDto.getHotelId()));
        hotel.setHotelName(hotelDetails.getHotelName());
        hotel.setPhoneNumber(hotelDetails.getPhoneNumber());
        hotel.setGmail(hotelDetails.getGmail());
        hotel.setHotelType(hotelDetails.getHotelType());
        hotel.setCity(hotelDetails.getCity());

        response.setData(hotel);
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
