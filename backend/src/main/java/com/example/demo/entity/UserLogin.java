package com.example.demo.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@JsonInclude(JsonInclude.Include.NON_NULL)


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_login")
public class UserLogin {
    @Id
    @Column(name = "gmail", nullable = false, unique = true)
    private String gmail;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserDetails userDetails;

    @Column(name = "password")
    private String password;

    @OneToOne
    @JoinColumn(name = "hotel_id")
    private HotelDetails hotelDetails;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private EmployeeDetails employeeDetails;

}
