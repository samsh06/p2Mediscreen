package com.mediassessment.mediassessment.controller;

import static org.springframework.http.HttpStatus.OK;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mediassessment.mediassessment.beans.dto.PatientBeanDto;
import com.mediassessment.mediassessment.service.IAssessmentService;

/**
 * Assessment Controller
 */
@Controller
@RequestMapping

public class AssessmentController {

    /**
     * SLF4J Logger instance.
     */
    private final Logger log = LoggerFactory.getLogger(AssessmentController.class);

    /**
     * Instance of IAssessmentService
     */
    private final IAssessmentService assessmentService;

    public AssessmentController(IAssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }


    /**
     * method to get assessment
     * @param id patient
     * @return report of assessment
     */
    @GetMapping("/assess")
    public ResponseEntity<PatientBeanDto> getRapportAssessmentById (@RequestParam Long id){
        log.info("get report by patientId :{} ", id);
        return new ResponseEntity<>(assessmentService.getRapportById((id)), OK);

    }



}