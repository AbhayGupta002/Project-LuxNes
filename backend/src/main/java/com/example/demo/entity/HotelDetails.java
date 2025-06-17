package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotelregistered")
public class HotelRegistered {

    @Id
    @Column(name = "Hregistrationid")
    private Long registrationId;

    @Column(name = "Hhotelname", nullable = false)
    private String hotelName;

    @Column(name = "Hphonenumber", nullable = false, unique = true)
    private Long phoneNumber;

    @Column(name = "registeredlocation", nullable = false)
    private Double location;

    @Column(name = "Hgmail", nullable = false)
    private String gmail;

    @Column(name = "Hrooms", nullable = false)
    private String rooms;

    @Column(name = "Htype", nullable = false)
    private String type;

    @Column(name = "Hcity", nullable = false)
    private String city;

}
