package com.ramon.trainingmanagementapi.repository;

import com.ramon.trainingmanagementapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByNameAndPhoneNumber(String name, String phoneNumber);
}
