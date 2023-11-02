package com.mediassessment.mediassessment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mediassessment.mediassessment.beans.PatientBean;
import com.mediassessment.mediassessment.beans.dto.PatientBeanDto;
import com.mediassessment.mediassessment.constant.RiskLevel;
import com.mediassessment.mediassessment.service.IAssessmentService;

@ExtendWith(MockitoExtension.class)
class AssessmentControllerTest {

    @InjectMocks
    private AssessmentController assessmentController;

    @Mock
    IAssessmentService assessmentService;

    @Test
    void getRapportAssessmentById() {

        //Given
        PatientBean patientBean = new PatientBean();
        patientBean.setId(1L);
        PatientBeanDto patient= new PatientBeanDto();
        patient.setRiskLevel(RiskLevel.BORDERLINE);
        when(assessmentService.getRapportById(patientBean.getId())).thenReturn(patient);

        //When
        ResponseEntity<PatientBeanDto> response = assessmentController.getRapportAssessmentById(patientBean.getId());

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patient.getRiskLevel(), response.getBody().getRiskLevel());



    }
}