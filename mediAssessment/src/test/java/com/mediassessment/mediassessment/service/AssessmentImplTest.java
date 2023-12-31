package com.mediassessment.mediassessment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mediassessment.mediassessment.beans.NoteBean;
import com.mediassessment.mediassessment.beans.PatientBean;
import com.mediassessment.mediassessment.beans.dto.PatientBeanDto;
import com.mediassessment.mediassessment.constant.Gender;
import com.mediassessment.mediassessment.constant.RiskLevel;
import com.mediassessment.mediassessment.proxies.NoteProxy;
import com.mediassessment.mediassessment.proxies.PatientProxy;

@ExtendWith(MockitoExtension.class)
class AssessmentImplTest {
    @InjectMocks
    AssessmentServiceImpl assessment;
    @Mock
    NoteProxy noteProxy;
    @Mock
    PatientProxy patientProxy;

    private PatientBean patient;
    private PatientBean patient2;
    private List<NoteBean> allNotes;


    @BeforeEach
    void setUp(){
        patient = new PatientBean(1L, "Mario", "Bros", LocalDate.of(1990, 9, 10), Gender.MALE, "St Toto", "213213213213");
        patient2 = new PatientBean(2L, "Monica", "Carlita", LocalDate.of(1981, 5, 1), Gender.FEMALE, "St Toto", "213213213213");

        allNotes = List.of(
                new NoteBean("1234123", 1L, "The is patient have more Hemoglobin", LocalDate.of(1084, 9, 10)),
                new NoteBean("9876543", 1L, "The patient is Smoker", LocalDate.of(1084, 12, 12)));
    }

    @Test
    void getPatientAge() {
        //Given //When
        long age = assessment.getPatientAge(patient);

        //Then
        assertEquals(age, ChronoUnit.YEARS.between(patient.getBirthday(), LocalDate.now()));
    }

    @Test
    void calculRisks() {
        //Given
        List<NoteBean> notes= new ArrayList<>();

        notes.add(new NoteBean("1234123", 1L, "The is patient have more Hemoglobin", LocalDate.of(1084, 9, 10)));
        notes.add(new NoteBean("9876543", 1L, "The patient is Smoker", LocalDate.of(1084, 12, 12)));

        //When
        RiskLevel riskLevel = assessment.calculRisks(patient, notes);

        //Then
        assertEquals(riskLevel,RiskLevel.BORDERLINE);
    }

    @Test
    void calculateTriggerTerms() {
        //Given
        List<NoteBean> notes= new ArrayList<>();

        notes.add(new NoteBean("1234123", 1L, "The is patient have more Hemoglobin", LocalDate.of(1084, 9, 10)));
        notes.add(new NoteBean("9876543", 1L, "The patient is Smoker", LocalDate.of(1084, 12, 12)));

        //When
        long nbTriggers = assessment.calculateTriggerTerms(notes);

        //Then
        assertEquals(nbTriggers,2);

    }

    @Test
    void getRapportById_shouldResultTriggerTwo() {
        //Given

        List<NoteBean> notes= new ArrayList<>();
        notes.add(new NoteBean("1234123", 1L, "The is patient have more Hemoglobin", LocalDate.of(1084, 9, 10)));
        notes.add(new NoteBean("9876543", 1L, "The patient is Smoker", LocalDate.of(1084, 12, 12)));
        when(patientProxy.getPatient(patient.getId())).thenReturn(patient);
        when(noteProxy.getNoteByPatientId(patient.getId())).thenReturn(notes);

        //When
        PatientBeanDto status = assessment.getRapportById(patient.getId());

        //Then
        assertEquals(status.getRiskLevel().name(),"BORDERLINE");

    }

    @Test
    void getRapportById_shouldPatientGenderFemaleResultTriggerTwo() {
        //Given

        List<NoteBean> notes= new ArrayList<>();
        notes.add(new NoteBean("1234123", 2L, "The is patient have more Hemoglobin", LocalDate.of(1084, 9, 10)));
        notes.add(new NoteBean("9876543", 2L, "The patient is Smoker", LocalDate.of(1084, 12, 12)));
        when(patientProxy.getPatient(patient2.getId())).thenReturn(patient2);
        when(noteProxy.getNoteByPatientId(patient2.getId())).thenReturn(notes);

        //When
        PatientBeanDto status = assessment.getRapportById(patient2.getId());

        //Then
        assertEquals(status.getRiskLevel().name(),"BORDERLINE");

    }

    @Test
    void getRapportById_shouldResultTriggerSix() {
        //Given

        List<NoteBean> notes= new ArrayList<>();
        notes.add(new NoteBean("1234123", 1L, " Le patient a plus d'Hemoglobin et peu d' Antibody /n", LocalDate.of(1084, 9, 10)));
        notes.add(new NoteBean("9876543", 1L, " The patient is Smoker et gain de Weight n/", LocalDate.of(1084, 12, 12)));
        notes.add(new NoteBean("9876543", 1L, " le patient a des Dizziness et Cholesterol increase /n", LocalDate.of(1084, 12, 12)));

        when(patientProxy.getPatient(patient.getId())).thenReturn(patient);
        when(noteProxy.getNoteByPatientId(patient.getId())).thenReturn(notes);

        //When
        PatientBeanDto status = assessment.getRapportById(patient.getId());

        //Then
        assertEquals(status.getRiskLevel().name(),"IN_DANGER");

    }


    @Test
    void getRapportById_shouldPatientGenderFemaleResultTriggerSix() {
        //Given
        PatientBean patient3 = new PatientBean(3L, "Monica", "Carlita", LocalDate.of(2015, 5, 1), Gender.FEMALE, "St Toto", "213213213213");

        List<NoteBean> notes= new ArrayList<>();
        notes.add(new NoteBean("1234123", 3L, " Le patient a plus d' Hemoglobin et peu d' Antibody ", LocalDate.of(1084, 9, 10)));
        notes.add(new NoteBean("9876543", 3L, " The patient is Smoker et gain de Weight ", LocalDate.of(1084, 12, 12)));
        notes.add(new NoteBean("9876543", 3L, " le patient a des Dizziness et Cholesterol increase ", LocalDate.of(1084, 12, 12)));

        when(patientProxy.getPatient(patient3.getId())).thenReturn(patient3);
        when(noteProxy.getNoteByPatientId(patient3.getId())).thenReturn(notes);

        //When
        PatientBeanDto status = assessment.getRapportById(patient3.getId());

        //Then
        assertEquals(status.getRiskLevel().name(),"IN_DANGER");

    }
    @Test
    void getRapportById_shouldPatientGenderMaleOverThirtyYearsResultTriggerSix() {
        //Given
        PatientBean patient3 = new PatientBean(3L, "Calogero", "Bianchi", LocalDate.of(2014, 5, 1), Gender.MALE, "St Toto", "213213213213");

        List<NoteBean> notes= new ArrayList<>();
        notes.add(new NoteBean("1234123", 3L, " Le patient a plus d'Hemoglobin et peu d' Antibody ", LocalDate.of(2023, 9, 10)));
        notes.add(new NoteBean("9876543", 3L, " The patient is Smoker et gain de Weight ", LocalDate.of(2023, 12, 12)));
        notes.add(new NoteBean("9876543", 3L, " le patient a des Dizziness et Cholesterol increase ", LocalDate.of(2023, 12, 12)));

        when(patientProxy.getPatient(patient3.getId())).thenReturn(patient3);
        when(noteProxy.getNoteByPatientId(patient3.getId())).thenReturn(notes);
        //When
        PatientBeanDto status = assessment.getRapportById(patient3.getId());

        //Then
        assertEquals(status.getRiskLevel().name(),"EARLY_ONSET");

    }

    @Test
    void getRapportById_shouldPatientGenderFemaleOverThirtyYearsResultTriggerSix() {
        //Given
        PatientBean patient3 = new PatientBean(3L, "Monica", "Carlita", LocalDate.of(1991, 5, 1), Gender.FEMALE, "St Toto", "213213213213");

        List<NoteBean> notes= new ArrayList<>();
        notes.add(new NoteBean("1234123", 3L, " Le patient a plus d'Hemoglobin et peu d' Antibody ", LocalDate.of(2023, 9, 10)));
        notes.add(new NoteBean("9876543", 3L, " The patient is Smoker et gain de Weight ", LocalDate.of(2023, 12, 12)));
        notes.add(new NoteBean("9876543", 3L, " le patient a des Dizziness et Cholesterol increase ", LocalDate.of(2023, 12, 12)));

        when(patientProxy.getPatient(patient3.getId())).thenReturn(patient3);
        when(noteProxy.getNoteByPatientId(patient3.getId())).thenReturn(notes);
        //When
        PatientBeanDto status = assessment.getRapportById(patient3.getId());

        //Then
        assertEquals(status.getRiskLevel().name(),"IN_DANGER");

    }

    @Test
    void getRapportById_shouldResultTriggerEight() {
        //Given
        List<NoteBean> notes= new ArrayList<>();
        notes.add(new NoteBean("1234123", 1L, " Le patient a plus d'Hemoglobin et peu d' Antibody ", LocalDate.of(1084, 9, 10)));
        notes.add(new NoteBean("9876543", 1L, " The patient is Smoker et gain de Weight n/ ", LocalDate.of(1084, 12, 12)));
        notes.add(new NoteBean("9876543", 1L, " le patient a des Dizziness et Cholesterol increase ", LocalDate.of(1084, 12, 12)));
        notes.add(new NoteBean("9876543", 1L, " le patient a des symptomless Abnormal  et des strangers Reaction a la therapize ", LocalDate.of(1084, 12, 12)));

        when(patientProxy.getPatient(patient.getId())).thenReturn(patient);
        when(noteProxy.getNoteByPatientId(patient.getId())).thenReturn(notes);
        //When
        PatientBeanDto status = assessment.getRapportById(patient.getId());

        //Then
        assertEquals(status.getRiskLevel().name(),"EARLY_ONSET");

    }

    @Test
    void getRapportById_shouldResultGenderFemaleOverThirtyYearsResultTriggerEight() {
        //Given
        PatientBean patient3 = new PatientBean(3L, "Monica", "Carlita", LocalDate.of(2015, 5, 1), Gender.FEMALE, "St Toto", "213213213213");
        List<NoteBean> notes= new ArrayList<>();
        notes.add(new NoteBean("1234123", 1L, " Le patient a plus d'Hemoglobin et peu d' Antibody ", LocalDate.of(1084, 9, 10)));
        notes.add(new NoteBean("9876543", 1L, " The patient is Smoker et gain de Weight n/ ", LocalDate.of(1084, 12, 12)));
        notes.add(new NoteBean("9876543", 1L, " le patient a des Dizziness et Cholesterol increase ", LocalDate.of(1084, 12, 12)));
        notes.add(new NoteBean("9876543", 1L, " le patient a des symptomless Abnormal  et des strangers Reaction a la therapize ", LocalDate.of(1084, 12, 12)));

        when(patientProxy.getPatient(patient3.getId())).thenReturn(patient3);
        when(noteProxy.getNoteByPatientId(patient3.getId())).thenReturn(notes);
        //When
        PatientBeanDto status = assessment.getRapportById(patient3.getId());

        //Then
        assertEquals(status.getRiskLevel().name(),"EARLY_ONSET");

    }

    @Test
    void getRapportById_shouldResultGenderMaleLessThirtyYearsResultTriggerFour() {
        //Given
        PatientBean patient3 = new PatientBean(3L, "Calogero", "Bianchi", LocalDate.of(2000, 5, 1), Gender.MALE, "St Toto", "213213213213");
        List<NoteBean> notes= new ArrayList<>();
        notes.add(new NoteBean("1234123", 3L, " Le patient a plus d' Hemoglobin et peu d' Antibody ", LocalDate.of(1084, 9, 10)));
        notes.add(new NoteBean("9876543", 3L, " The patient is Smoker et gain de Weight n/ ", LocalDate.of(2022, 11, 12)));

        when(patientProxy.getPatient(patient3.getId())).thenReturn(patient3);
        when(noteProxy.getNoteByPatientId(patient3.getId())).thenReturn(notes);
        //When
        PatientBeanDto status = assessment.getRapportById(patient3.getId());

        //Then
        assertEquals(status.getRiskLevel().name(),"IN_DANGER");

    }

    @Test
    void getRapportById_shouldResultGenderFemaleLessThirtyYearsResultTriggerThree() {

        //Given
        PatientBean patient3 = new PatientBean(3L, "Monica", "Carlita", LocalDate.of(2015, 5, 1), Gender.FEMALE, "St Toto", "213213213213");
        List<NoteBean> notes= new ArrayList<>();
        notes.add(new NoteBean("1234123", 3L, " Le patient a plus d'Hemoglobin et peu d' Antibody ", LocalDate.of(1084, 9, 10)));
        notes.add(new NoteBean("9876543", 3L, " The patient is Smoker ", LocalDate.of(1084, 12, 12)));
        when(patientProxy.getPatient(patient3.getId())).thenReturn(patient3);
        when(noteProxy.getNoteByPatientId(patient3.getId())).thenReturn(notes);

        //When
        PatientBeanDto status = assessment.getRapportById(patient3.getId());
        //Then
        assertEquals(status.getRiskLevel().name(),"NONE");

    }

    @Test
    void getRapportById_shouldResultGenderFemaleLessThirtyYearsOldResultTriggerTwo() {

        //Given
        PatientBean patient3 = new PatientBean(3L, "Geltrude", "Carlita", LocalDate.of(2000, 5, 1), Gender.FEMALE, "St Toto", "213213213213");
        List<NoteBean> notes= new ArrayList<>();
        notes.add(new NoteBean("1234123", 3L, " Le patient a plus d' Hemoglobin ", LocalDate.of(1084, 9, 10)));
        when(patientProxy.getPatient(patient3.getId())).thenReturn(patient3);
        when(noteProxy.getNoteByPatientId(patient3.getId())).thenReturn(notes);

        //When
        PatientBeanDto status = assessment.getRapportById(patient3.getId());
        //Then
        assertEquals(status.getRiskLevel().name(),"NONE");

    }


    @Test
    void getRapportByFamilyName_shouldResultRapport()  {

        //Given
        PatientBean patient3 = new PatientBean();
        patient3.setId(3L);
        patient3.setFirstName("Geltrude");
        patient3.setLastName("Carlita");
        patient3.setBirthday(LocalDate.of(2000, 5, 1));
        patient3.setGender( Gender.FEMALE);
        patient3.setAddress("St Toto");
        patient3.setPhone("213213213213");
        List<NoteBean> notes= new ArrayList<>();
        List<PatientBean> patients = new ArrayList<>();
        patients.add(patient3);
        notes.add(new NoteBean("1234123", patient3.getId(), " Le patient a plus d' Hemoglobin ", LocalDate.of(2022, 9, 10)));
        when(patientProxy.getPatientByFirstName(patient3.getFirstName())).thenReturn(patients);
        when(noteProxy.getNoteByPatientId(patient3.getId())).thenReturn(notes);

        //When
        PatientBeanDto status = assessment.getRapportByFamilyName(patient3.getFirstName());
        //Then
        assertEquals(status.getRiskLevel().name(),"NONE");

    }

}