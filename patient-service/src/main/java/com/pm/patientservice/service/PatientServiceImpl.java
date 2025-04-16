package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.exception.EmailAlreadyExistsException;
import com.pm.patientservice.exception.PatientNotFoundException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import org.springframework.stereotype.Service;
import com.pm.patientservice.repository.PatientRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientServiceImpl implements PatientService {
	private final PatientRepository patientRepository;


	public PatientServiceImpl(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	public List<PatientResponseDTO> getPatients() {
		return patientRepository.findAll().stream()
			.map(PatientMapper::toDTO)
			.toList();
	}

	@Override
	public PatientResponseDTO createPatient(PatientRequestDTO patient) {
		if (patientRepository.existsByEmail(patient.getEmail())) {
			throw new EmailAlreadyExistsException("Patient with email " + patient.getEmail() + " already exists");
		}
		Patient newPatient = patientRepository.save(PatientMapper.toEntity(patient));

		return PatientMapper.toDTO(newPatient);
	}

	@Override
	public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patient) {
		Patient patientToUpdate = patientRepository.findById(id)
			.orElseThrow(() -> new PatientNotFoundException("Patient not found with id " + id));

		if (patientRepository.existsByEmailAndIdNot(patient.getEmail(), id)) {
			throw new EmailAlreadyExistsException("Patient with email " + patient.getEmail() + " already exists");
		}

		patientToUpdate.setName(patient.getName());
		patientToUpdate.setEmail(patient.getEmail());
		patientToUpdate.setAddress(patient.getAddress());
		patientToUpdate.setDateOfBirth(LocalDate.parse(patient.getDateOfBirth()));

		Patient updatedPatient = patientRepository.save(patientToUpdate);

		return PatientMapper.toDTO(updatedPatient);
	}

	@Override
	public void deletePatient(UUID id) {
		patientRepository.deleteById(id);
	}

}
