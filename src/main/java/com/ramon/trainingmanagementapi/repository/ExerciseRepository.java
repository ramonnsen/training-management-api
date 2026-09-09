package com.ramon.trainingmanagementapi.repository;

import com.ramon.trainingmanagementapi.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExerciseRepository extends JpaRepository<Exercise, UUID> {
}
