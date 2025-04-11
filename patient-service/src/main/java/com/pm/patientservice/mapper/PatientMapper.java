package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.model.Patient;

import java.time.LocalDate;
import java.util.UUID;

public class PatientMapper {
	public static PatientResponseDTO toDTO(Patient patient) {
		return PatientResponseDTO.builder()
			.id(patient.getId().toString())
			.firstName(patient.getFirstName())
			.lastName(patient.getLastName())
			.email(patient.getEmail())
			.address(patient.getAddress())
			.dateOfBirth(patient.getDateOfBirth().toString())
			.dateOfRegistration(patient.getDateOfRegistration().toString())
			.build();
	}

	public static Patient toEntity(PatientResponseDTO patientResponseDTO) {
		return Patient.builder()
			.id(UUID.fromString(patientResponseDTO.getId()))
			.firstName(patientResponseDTO.getFirstName())
			.lastName(patientResponseDTO.getLastName())
			.email(patientResponseDTO.getEmail())
			.address(patientResponseDTO.getAddress())
			.dateOfBirth(LocalDate.parse(patientResponseDTO.getDateOfBirth()))
			.dateOfRegistration(LocalDate.parse(patientResponseDTO.getDateOfRegistration()))
			.build();
	}
}
