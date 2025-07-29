package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor

//hotel & user details
@Entity
@Table(name = "booking_details")
public class BookingDetails {

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private HotelDetails hotelDetails;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "booking_id")
    private String bookingId;

//    @Column(name = "hotel_id")
//    private String hotelId;

//    @Column(name = "user_id")
//    private String userId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetails userDetails;

    @Column(name = "userid", unique = true, nullable = false)
    private String userid;

    @Column(name = "payment")
    private String paymentMode;

    @Column(name = "visitors", nullable = false)
    private String totalGuest;

    @Column(name = "room_no")
    private String roomNo;

    @Column(name = "time_duration", nullable = false)
    private String time;

    @Column(name = "bording_time", nullable = false, updatable = false)
    private String bordingTime;

    @Column(name = "leave_time", nullable = false, updatable = false)
    private String leaveTime;

    @Column(name = "status", nullable = false, updatable = false)
    private String status;

}
