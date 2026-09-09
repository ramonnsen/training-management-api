package com.ramon.trainingmanagementapi.service;


import com.ramon.trainingmanagementapi.dto.PageResponseDTO;
import com.ramon.trainingmanagementapi.dto.PhysicalAssessmentCreateDTO;
import com.ramon.trainingmanagementapi.dto.PhysicalAssessmentRequestDTO;
import com.ramon.trainingmanagementapi.dto.PhysicalAssessmentResponseDTO;
import com.ramon.trainingmanagementapi.exception.PhysicalAssessmentAlreadyExistsException;
import com.ramon.trainingmanagementapi.exception.PhysicalAssessmentNotFoundException;
import com.ramon.trainingmanagementapi.exception.UserNotFoundException;
import com.ramon.trainingmanagementapi.model.PhysicalAssessment;
import com.ramon.trainingmanagementapi.model.User;
import com.ramon.trainingmanagementapi.repository.PhysicalAssessmentRepository;
import com.ramon.trainingmanagementapi.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PhysicalAssessmentService {

    private final PhysicalAssessmentRepository physicalAssessmentRepository;
    private final UserRepository userRepository;

    public PhysicalAssessmentService(PhysicalAssessmentRepository physicalAssessmentRepository, UserRepository userRepository) {
        this.physicalAssessmentRepository = physicalAssessmentRepository;
        this.userRepository = userRepository;
    }

    public PhysicalAssessmentResponseDTO createPhysicalAssessment(UUID id, PhysicalAssessmentCreateDTO createDTO) {
        if (physicalAssessmentRepository.existsByUserId(id)) {
            throw new PhysicalAssessmentAlreadyExistsException("Já existe uma avaliação fisica para esse usuario, por favor atualize a avaliação");
        }

        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found"));

        PhysicalAssessment buildPhysicalAssessment = PhysicalAssessment.builder()
                .user(user)
                .weight(createDTO.weight())
                .height(createDTO.height())
                .bodyFatPer(createDTO.bodyFatPer())
                .imc(createDTO.imc())
                .build();

        PhysicalAssessment savedEntity = physicalAssessmentRepository.save(buildPhysicalAssessment);

        return PhysicalAssessmentResponseDTO.builder()
                .id(savedEntity.getId())
                .height(savedEntity.getHeight())
                .weight(savedEntity.getWeight())
                .bodyFatPer(savedEntity.getBodyFatPer())
                .imc(savedEntity.getImc())
                .createdAt(savedEntity.getCreatedAt())
                .build();
    }

    public PhysicalAssessmentResponseDTO findById(UUID id) {
        PhysicalAssessment physicalAssessment = physicalAssessmentRepository.findById(id).orElseThrow(
                () -> new PhysicalAssessmentNotFoundException("Avaliacao fisica não encontrada")
        );

        return PhysicalAssessmentResponseDTO.builder()
                .id(physicalAssessment.getId())
                .height(physicalAssessment.getHeight())
                .weight(physicalAssessment.getWeight())
                .bodyFatPer(physicalAssessment.getBodyFatPer())
                .imc(physicalAssessment.getImc())
                .createdAt(physicalAssessment.getCreatedAt())
                .build();
    }

    public PageResponseDTO<PhysicalAssessmentResponseDTO> findAll(Pageable pageable) {
        Page<PhysicalAssessment> pageAll = physicalAssessmentRepository.findAll(pageable);
        Page<PhysicalAssessmentResponseDTO> pageResponseDTO = pageAll.map(physicalAssessment ->
                PhysicalAssessmentResponseDTO.builder()
                        .id(physicalAssessment.getId())
                        .weight(physicalAssessment.getWeight())
                        .height(physicalAssessment.getHeight())
                        .bodyFatPer(physicalAssessment.getBodyFatPer())
                        .imc(physicalAssessment.getImc())
                        .createdAt(physicalAssessment.getCreatedAt())
                        .build());

        return new PageResponseDTO<>(
                pageResponseDTO.getContent(),
                pageResponseDTO.getTotalPages(),
                pageResponseDTO.getNumber(),
                pageResponseDTO.getNumberOfElements(),
                pageResponseDTO.getSize()
        );
    }

    public PhysicalAssessmentResponseDTO updatePhysicalAssessment(UUID id, PhysicalAssessmentRequestDTO requestDTO) {
        PhysicalAssessment physicalAssessment = physicalAssessmentRepository.findById(id)
                .orElseThrow(() -> new PhysicalAssessmentNotFoundException("Invalid Physical Assessment"));

        if (requestDTO.height() != null) physicalAssessment.setHeight(requestDTO.height());
        if (requestDTO.weight() != null) physicalAssessment.setWeight(requestDTO.weight());
        if (requestDTO.bodyFatPer() != null) physicalAssessment.setBodyFatPer(requestDTO.bodyFatPer());
        if (requestDTO.imc() != null) physicalAssessment.setImc(requestDTO.imc());

        PhysicalAssessment updatedEntity = physicalAssessmentRepository.save(physicalAssessment);
        return PhysicalAssessmentResponseDTO.builder()
                .id(updatedEntity.getId())
                .weight(updatedEntity.getWeight())
                .height(updatedEntity.getHeight())
                .bodyFatPer(updatedEntity.getBodyFatPer())
                .imc(updatedEntity.getImc())
                .createdAt(updatedEntity.getCreatedAt())
                .build();

    }

    public void delete(UUID id) {
        physicalAssessmentRepository.findById(id)
                .orElseThrow(() -> new PhysicalAssessmentNotFoundException("Invalid Physical Assessment"));
        physicalAssessmentRepository.deleteById(id);
    }
}
