package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.mapper.PatientMapper;
import org.springframework.stereotype.Service;
import com.pm.patientservice.repository.PatientRepository;

import java.util.List;

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

}
