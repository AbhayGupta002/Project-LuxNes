package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class UserDto {
    public String getHotelId;
    private String bookingId;
    private String userId;
    private String name;
    private String contactNumber;
    private String gmail;
    private String password;
    private String Location;

}
