package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class HotelDto {
    private String hotelId;
    private String hotelName;
    private String phoneNumber;
    private String gmail;
    private String totalRoom;
    private String availableRooms;
    private String hotelType;
    private String city;
    private String location;
    private String password;
}
