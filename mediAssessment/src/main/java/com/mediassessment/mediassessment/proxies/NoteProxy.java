package com.mediassessment.mediassessment.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mediassessment.mediassessment.beans.NoteBean;

/**
 * Interface for communicating with the mediNote service via FeignClient.
 */
@FeignClient(name = "note", url = "${feign.mediNote.url}")
public interface NoteProxy {


    /**
     * @param id patientId
     * @return List of notes by patientId
     */
    @GetMapping("/noteByPatientId")
    public List<NoteBean> getNoteByPatientId(@RequestParam Long id);

}