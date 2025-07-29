package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee_details")
public class EmployeeDetails {
    @Id
    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "gmail")
    private String gmail;

    @Column(name = "contact_number", nullable = false, unique = true)
    private String contactNumber;

    @Column(name = "empName", nullable = false)
    private String name;

    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "employeeDetails", cascade = CascadeType.ALL)
    private UserLogin userLogin;

//    @JoinColumn(name = "userid")
//    private String userId;

}
