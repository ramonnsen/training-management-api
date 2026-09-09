package com.ramon.trainingmanagementapi.controller;

import com.ramon.trainingmanagementapi.dto.PageResponseDTO;
import com.ramon.trainingmanagementapi.dto.PhysicalAssessmentCreateDTO;
import com.ramon.trainingmanagementapi.dto.PhysicalAssessmentRequestDTO;
import com.ramon.trainingmanagementapi.dto.PhysicalAssessmentResponseDTO;
import com.ramon.trainingmanagementapi.service.PhysicalAssessmentService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/physicalassessment")
public class PhysicalAssessmentController {
    private final PhysicalAssessmentService physicalAssessmentService;

    public PhysicalAssessmentController(PhysicalAssessmentService physicalAssessmentService) {
        this.physicalAssessmentService = physicalAssessmentService;
    }

    @PostMapping("/user/{id}/physical-assessment")
    public ResponseEntity<PhysicalAssessmentResponseDTO> createPhysicalAssessment(@PathVariable UUID id, @RequestBody PhysicalAssessmentCreateDTO createDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(physicalAssessmentService.createPhysicalAssessment(id, createDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhysicalAssessmentResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(physicalAssessmentService.findById(id));
    }

    @GetMapping
    public ResponseEntity<PageResponseDTO<PhysicalAssessmentResponseDTO>> findAll(@PageableDefault Pageable pageable){
        return ResponseEntity.ok().body(physicalAssessmentService.findAll(pageable));
    }

    @PutMapping("/user/{id}/physical-assessment")
    public ResponseEntity<PhysicalAssessmentResponseDTO> update(@PathVariable UUID id, @RequestBody PhysicalAssessmentRequestDTO requestDTO){
        return ResponseEntity.ok().body(physicalAssessmentService.updatePhysicalAssessment(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        physicalAssessmentService.delete(id);
        return ResponseEntity.ok().build();
    }
}
