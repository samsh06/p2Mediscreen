package com.medipatient.medipatient.curl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.medipatient.medipatient.constant.Gender;
import com.medipatient.medipatient.model.Patient;
import com.medipatient.medipatient.model.dto.PatientDto;
import com.medipatient.medipatient.service.PatientServiceImpl;

/**
 * Tests PatientCurlControllerTest
 */
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class PatientCurlControllerTest {
    @InjectMocks
    PatientCurlController patientCurlController;
    @Mock
    PatientServiceImpl patientService;

    @Test
    void addPatientCurlShouldReturnModifiedModelAndViewCaseMale() {
        //Given
        PatientDto patientDto=new PatientDto();
        patientDto.setId(1L);
        patientDto.setFamily("Mariottide");
        patientDto.setGiven("Nando");
        patientDto.setDob("1954-10-23");
        patientDto.setAddress("my address");
        patientDto.setPhone("000111333222");
        patientDto.setSex(String.valueOf(Gender.MALE));
        Patient patient = new Patient(patientDto.getId(), patientDto.getFamily(), patientDto.getGiven(),  LocalDate.parse(patientDto.getDob()),Gender.MALE, patientDto.getAddress(), patientDto.getPhone());
       when(patientService.savePatient(any(Patient.class))).thenReturn(patient);

       //When
        ResponseEntity<Patient> result = patientCurlController.addPatientCurl(patientDto);

        //Then
        assertEquals(result.getStatusCode(), HttpStatus.CREATED);

    }
    @Test
    void addPatientCurlShouldReturnModifiedModelAndViewCaseFemale() {
        //Given
        PatientDto patientDto=new PatientDto(1L,"Mariottide","Nando","1954-10-23",String.valueOf(Gender.FEMALE),"my address","000111333222");

        Patient patient = new Patient(patientDto.getId(), patientDto.getFamily(), patientDto.getGiven(),  LocalDate.parse(patientDto.getDob()), Gender.FEMALE, patientDto.getAddress(), patientDto.getPhone());
        when(patientService.savePatient(any(Patient.class))).thenReturn(patient);

        //When
        ResponseEntity<Patient> result = patientCurlController.addPatientCurl(patientDto);

        //Then
        assertEquals(result.getStatusCode(), HttpStatus.CREATED);

    }
}