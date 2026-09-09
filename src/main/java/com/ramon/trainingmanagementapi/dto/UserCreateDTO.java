package com.ramon.trainingmanagementapi.dto;

import com.ramon.trainingmanagementapi.model.PhysicalAssessment;
import com.ramon.trainingmanagementapi.model.enums.UserRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;

public record UserCreateDTO(
        String name,
        String phoneNumber,
        UserRole role
) {
}
