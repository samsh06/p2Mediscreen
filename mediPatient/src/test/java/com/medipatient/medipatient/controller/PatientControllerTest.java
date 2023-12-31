package com.medipatient.medipatient.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.medipatient.medipatient.Exception.PatientNotFoundException;
import com.medipatient.medipatient.constant.Gender;
import com.medipatient.medipatient.model.Patient;
import com.medipatient.medipatient.service.PatientServiceImpl;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class PatientControllerTest {

    @Mock
    PatientServiceImpl patientService;

    @InjectMocks
    private PatientController patientController;



    @Test
    void getAllPatientsShouldReturnModifiedModelAndView() {
        //Given
        List<Patient> patientsList = new ArrayList<>();
        Patient patient = new Patient(1L, "Piero", "Brow", LocalDate.of(1084, 9, 10), Gender.MALE, "St Toto", "213213213213");
        Patient patient1 = new Patient(2L, "Mario", "Rossi", LocalDate.of(1988, 8, 15), Gender.MALE, "St Tata", "123123123123");
        patientsList.add(patient);
        patientsList.add(patient1);
        when(patientService.getAllPatients()).thenReturn(patientsList);

        //When
        ResponseEntity<List<Patient>> response = patientController.getAllPatients();

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patientsList, response.getBody());
    }


    @Test
    void getPatientByIdShouldReturnModifiedModelAndView() throws PatientNotFoundException {
        //Given
        List<Patient> patientsList = new ArrayList<>();
        Patient patient = new Patient(1L, "Piero", "Brow", LocalDate.of(1084, 9, 10), Gender.MALE, "St Toto", "213213213213");
        Patient patient1 = new Patient(2L, "Mario", "Rossi", LocalDate.of(1988, 8, 15), Gender.MALE, "St Tata", "123123123123");
        patientsList.add(patient);
        patientsList.add(patient);
        when(patientService.findById(1L)).thenReturn(patient);

        //When
        ResponseEntity<Patient> response = patientController.getPatientById(1L);

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patient, response.getBody());
    }

    @Test
    void getPatientByIdForUiShouldReturnModifiedModelAndView() throws PatientNotFoundException {
        //Given
        List<Patient> patientsList = new ArrayList<>();
        Patient patient = new Patient(1L, "Piero", "Brow", LocalDate.of(1084, 9, 10), Gender.MALE, "St Toto", "213213213213");
        Patient patient1 = new Patient(2L, "Mario", "Rossi", LocalDate.of(1988, 8, 15), Gender.MALE, "St Tata", "123123123123");
        patientsList.add(patient);
        patientsList.add(patient);
        when(patientService.findById(2L)).thenReturn(patient);

        //When
        ResponseEntity<Patient> response = patientController.getPatientByIdForUi(2L);

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patient, response.getBody());
    }

    @Test
    void updatePatientShouldReturnModifiedModelAndView() throws PatientNotFoundException {
        //Given
        List<Patient> patientsList = new ArrayList<>();
        Patient patient = new Patient(1L, "Piero", "Brow", LocalDate.of(1084, 9, 10), Gender.MALE, "St Toto", "213213213213");
        Patient patient1 = new Patient(2L, "Mario", "Rossi", LocalDate.of(1988, 8, 15), Gender.MALE, "St Tata", "123123123123");
        patientsList.add(patient);
        patientsList.add(patient);
        when(patientService.updatePatient(patient)).thenReturn(patient);

        //When
        ResponseEntity<Patient> response = patientController.updatePatient(patient);

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patient, response.getBody());
    }

    @Test
    void addPatientShouldReturnModifiedModelAndView() throws PatientNotFoundException {
        //Given
        List<Patient> patientsList = new ArrayList<>();
        Patient patient = new Patient(1L, "Piero", "Brow", LocalDate.of(1084, 9, 10), Gender.MALE, "St Toto", "213213213213");
        Patient patient1 = new Patient(2L, "Mario", "Rossi", LocalDate.of(1988, 8, 15), Gender.MALE, "St Tata", "123123123123");
        patientsList.add(patient);
        patientsList.add(patient);
        when(patientService.savePatient(patient)).thenReturn(patient);

        //When
        ResponseEntity<Patient> response = patientController.addPatient(patient);

        //Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(patient, response.getBody());
    }

    @Test
    void deletePatientShouldReturnModifiedModelAndView() throws PatientNotFoundException {
        //Given
        List<Patient> patientsList = new ArrayList<>();
        Patient patient = new Patient(1L, "Piero", "Brow", LocalDate.of(1084, 9, 10), Gender.MALE, "St Toto", "213213213213");
        Patient patient1 = new Patient(2L, "Mario", "Rossi", LocalDate.of(1988, 8, 15), Gender.MALE, "St Tata", "123123123123");
        patientsList.add(patient);
        patientsList.add(patient);
        when(patientService.deletePatient(1L)).thenReturn(patient);

        //When
        ResponseEntity<Patient> response = patientController.deletePatient(1L);

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patient, response.getBody());
    }

    @Test
    void deletePatientShouldReturnModifiedModelAndView2() throws PatientNotFoundException {
        //Given
        List<Patient> patientsList = new ArrayList<>();
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setFirstName("Piero");
        patient.setLastName("Brow");
        patient.setBirthday(LocalDate.of(1084, 9, 10));
        patient.setGender(Gender.MALE);
        patient.setAddress("St Toto");
        patient.setPhone("123123123");
        patientsList.add(patient);
        patientsList.add(patient);
        when(patientService.deletePatient(1L)).thenReturn(patient);

        //When
        ResponseEntity<Patient> response = patientController.deletePatient(1L);

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patient, response.getBody());
    }

    @Test
    void getPatientByLastNameShouldReturnModifiedModelAndView() throws PatientNotFoundException {
        //Given
        List<Patient> patientsList = new ArrayList<>();
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setFirstName("Piero");
        patient.setLastName("Brow");
        patient.setBirthday(LocalDate.of(1084, 9, 10));
        patient.setGender(Gender.MALE);
        patient.setAddress("St Toto");
        patient.setPhone("123123123");
        patientsList.add(patient);
        patientsList.add(patient);
        List<Patient> patients = new ArrayList<>();
        patients.add(patient);

        when(patientService.findByFirstName(patient.getFirstName())).thenReturn(patients);

        //When
        ResponseEntity<List<Patient>> response = patientController.getPatientByLastName("Piero");

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patients, response.getBody());
    }

}
