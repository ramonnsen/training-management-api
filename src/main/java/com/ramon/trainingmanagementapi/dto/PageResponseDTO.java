package com.ramon.trainingmanagementapi.dto;

import java.util.List;

public record PageResponseDTO<T>(
        List<T> PhysicalAssessments,
        int totalPages,
        int number,
        long totalElements,
        int size
) {
}
