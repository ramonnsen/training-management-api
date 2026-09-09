package com.ramon.trainingmanagementapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "physical_assessment")
public class PhysicalAssessment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Double weight;

    private Double height;

    private Double bodyFatPer;

    private Double imc;

    private LocalDateTime createdAt= LocalDateTime.now();

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;
}
