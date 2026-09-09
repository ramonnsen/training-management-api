package com.ramon.trainingmanagementapi.repository;

import com.ramon.trainingmanagementapi.model.PhysicalAssessment;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface PhysicalAssessmentRepository extends JpaRepository<PhysicalAssessment, UUID>{

    boolean existsByUserId(UUID id);
}
