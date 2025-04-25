package com.pm.patientservice.repository;

import com.pm.patientservice.model.Patient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {

	boolean existsByEmail(@NotNull @Email String email);

	boolean existsByEmailAndIdNot(@NotNull @Email String email, UUID id);

	@Modifying
	@Query("UPDATE Patient p SET p.billingAccountId = :billingAccountId WHERE p.id = :id")
	Patient savePatientBillingAccountId(@NotNull UUID id, @NotNull String billingAccountId);

}
