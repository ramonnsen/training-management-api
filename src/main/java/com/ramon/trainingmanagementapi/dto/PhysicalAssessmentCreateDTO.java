package com.ramon.trainingmanagementapi.dto;

public record PhysicalAssessmentCreateDTO(
        Double weight,
        Double height,
        Double bodyFatPer,
        Double imc
) {
}
