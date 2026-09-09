package com.ramon.trainingmanagementapi.dto;

import com.ramon.trainingmanagementapi.model.enums.UserRole;
import lombok.Builder;

import java.util.UUID;
@Builder
public record UserResponseDTO(
        UUID id,
        String name,
        String phoneNumber,
        UserRole role
) {
}
