package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    @Column(name = "userid", unique = true,nullable = false)
    private Long userid;

    @Column(name = "gmail")
    private  String gmail;
}
