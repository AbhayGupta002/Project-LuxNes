package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

@Entity
@Table(name = "hotel_details")
public class HotelDetails {

    @Id
    @Column(name = "hotel_id")
    private String hotelId;

    @Column(name = "hotel_name", nullable = false)
    private String hotelName;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "registered_location")
    private String location;

    @Column(name = "gmail", nullable = false)
    private String gmail;

    @OneToOne(mappedBy = "hotelDetails", cascade = CascadeType.ALL)
    private UserLogin userLogin;

    @Column(name = "available_rooms")
    private String availableRooms;

    @Column(name = "type", nullable = false)
    private String hotelType;

    @Column(name = "city")
    private String city;

    @Column(name = "password")
    private String password;
}
