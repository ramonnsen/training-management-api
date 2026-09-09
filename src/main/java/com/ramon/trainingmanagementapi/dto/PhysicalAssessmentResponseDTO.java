package com.ramon.trainingmanagementapi.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;
@Builder
public record PhysicalAssessmentResponseDTO(
        UUID id,
        Double weight,
        Double height,
        Double bodyFatPer,
        Double imc,
        LocalDateTime createdAt
) {
}
