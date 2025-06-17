package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

//hotel & user details
@Entity
@Table(name = "booking_details")
public class BookingDetails {

    @OneToOne
    @JoinColumn(name = "registrationid")
    private HotelDetails hotelDetails;

    @Column(name = "registration_number")
    private Long registrationNo;

    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "room_no")
    private int roomNo;

    @Column(name = "registered_location")
    private Double registeredLocation;

    @Column(name = "room_rent")
    private int roomRent;

    @Column(name = "type")
    private String type;

    @Column(name = "hotelname")
    private String hotelName;

    @Column(name = "city")
    private String city;




    @OneToOne
    @JoinColumn(name = "userId")
    private  UserDetails userDetails;

    @Column(name = "userid", unique = true,nullable = false)
    private Long userid;

    @Column(name = "gmail", nullable = false)
    private String gmail;

    @Column(name = "contact_number", nullable = false)
    private Long contactNumber;

    @Column(name = "username", nullable = false)
    private String name;

    @Column(name = "payment", nullable = false)
    private String paymentMode;

    @Column(name = "visitors", nullable = false)
    private int totalVisitors;

    @Column(name = "time_duration", nullable = false)
    private String time;

    @Column(name = "boarding_time", nullable = false,updatable = false)
    private String dateTime;

    @Column(name = "leave_time", nullable = false,updatable = false)
    private String leaveTime;

    @Column(name = "status", nullable = false, updatable = false)
    private String status;

}
