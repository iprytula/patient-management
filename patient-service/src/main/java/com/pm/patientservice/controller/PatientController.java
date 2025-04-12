package com.pm.patientservice.controller;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

	private final PatientService patientService;

	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}

	@GetMapping
	public ResponseEntity<List<PatientResponseDTO>> getPatients() {
		List<PatientResponseDTO> patients = patientService.getPatients();
		return ResponseEntity.ok().body(patients);
	}

	@PostMapping
	public ResponseEntity<PatientResponseDTO> addPatient(@Valid @RequestBody PatientRequestDTO patient) {
		PatientResponseDTO newPatient = patientService.createPatient(patient);
		return ResponseEntity.ok().body(newPatient);
	}

}
