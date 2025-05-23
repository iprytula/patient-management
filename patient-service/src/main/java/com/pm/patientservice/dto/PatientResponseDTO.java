package com.pm.patientservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PatientResponseDTO {
	private String id;
	private String fullName;
	private String email;
	private String address;
	private String dateOfBirth;
	private String dateOfRegistration;
}
