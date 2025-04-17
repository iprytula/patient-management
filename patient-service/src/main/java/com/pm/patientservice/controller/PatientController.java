package com.pm.patientservice.controller;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.service.PatientService;
import com.pm.patientservice.validator.CreatePatientValidationGroup;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient", description = "The Patient API")
public class PatientController {

	private final PatientService patientService;

	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}

	@GetMapping
	@Operation(summary = "Get all patients")
	public ResponseEntity<List<PatientResponseDTO>> getPatients() {
		List<PatientResponseDTO> patients = patientService.getPatients();
		return ResponseEntity.ok().body(patients);
	}

	@PostMapping
	@Operation(summary = "Create a new patient")
	public ResponseEntity<PatientResponseDTO> createPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patient) {
		PatientResponseDTO newPatient = patientService.createPatient(patient);
		return ResponseEntity.ok().body(newPatient);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update a patient")
	public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id, @Valid @RequestBody PatientRequestDTO patient) {
		PatientResponseDTO updatedPatient = patientService.updatePatient(id, patient);
		return ResponseEntity.ok().body(updatedPatient);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a patient")
	public ResponseEntity<PatientResponseDTO> deletePatient(@PathVariable UUID id) {
		PatientResponseDTO deletedPatient = patientService.deletePatient(id);
		return ResponseEntity.accepted().body(deletedPatient);
	}

}
