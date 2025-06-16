package com.example.demo.repository;

import com.example.demo.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository <UserDetails, Long>{

}
