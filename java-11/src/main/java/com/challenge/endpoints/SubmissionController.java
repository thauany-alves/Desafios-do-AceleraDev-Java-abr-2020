package com.challenge.endpoints;

import java.util.ArrayList;
import java.util.List;

import com.challenge.dto.SubmissionDTO;
import com.challenge.mappers.SubmissionMapper;
import com.challenge.service.impl.SubmissionService;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/submission")
public class SubmissionController {
    
    @Autowired
    private SubmissionService submissionService;

    private SubmissionMapper submissionMapper = Mappers.getMapper(SubmissionMapper.class);

    @GetMapping
    public List<SubmissionDTO> findAll(
        @RequestParam(required = false) Long challengeId,
        @RequestParam(required = false) Long accelerationId
    ){
        List<SubmissionDTO> submissions = new ArrayList<>();
        
        if(challengeId != null && accelerationId != null) {
            submissions = submissionMapper.map(submissionService.findByChallengeIdAndAccelerationId(challengeId, accelerationId));
        }

        return submissions;
        
    }
}