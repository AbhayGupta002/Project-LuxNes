package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@NoArgsConstructor
@AllArgsConstructor

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "user_details")
public class UserDetails {
    @Id
    @Column(name = "user_id", unique = true, nullable = false)
    private String userId;

    @Column(name = "name")
    private String name;

    @Column(name = "contact_number", unique = true, nullable = false)
    private String contactNumber;

    @OneToOne(mappedBy = "userDetails", cascade = CascadeType.ALL)
    private UserLogin userLogin;

    @Column(name = "gmail", unique = true, nullable = false)
    private String gmail;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "latitude")
    private String latitude;

}
