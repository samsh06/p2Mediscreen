package com.medipatient.medipatient.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medipatient.medipatient.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository <Patient, Long> {

    List<Patient> findByFirstName(String firstName);
}
