package com.pm.patientservice.dto;

import com.pm.patientservice.validator.CreatePatientValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientRequestDTO {
	@NotBlank(message = "Name is required")
	@Size(max = 100, message = "Name cannot be longer than 100 characters")
	private String name;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;

	@NotBlank(message = "Address is required")
	private String address;

	@NotBlank(message = "Date of birth is required")
	private String dateOfBirth;

	@NotBlank(groups = {CreatePatientValidationGroup.class} , message = "Date of registration is required")
	private String dateOfRegistration;
}
