package com.pm.patientservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientRequestDTO {
	@NotBlank(message = "First name is required")
	@Size(max = 50, message = "First name cannot be longer than 50 characters")
	private String firstName;
	@NotBlank

	@NotBlank(message = "Last name is required")
	@Size(max = 50, message = "Last name cannot be longer than 50 characters")
	private String lastName;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;

	@NotBlank(message = "Address is required")
	private String address;

	@NotBlank(message = "Date of birth is required")
	private String dateOfBirth;

	@NotBlank(message = "Date of registration is required")
	private String dateOfRegistration;
}
