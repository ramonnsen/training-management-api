package com.ramon.trainingmanagementapi.dto;

public record PhysicalAssessmentRequestDTO(
        Double weight,
        Double height,
        Double bodyFatPer,
        Double imc
) {
}
