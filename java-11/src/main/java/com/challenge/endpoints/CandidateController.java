package com.challenge.endpoints;

import java.util.ArrayList;
import java.util.List;

import com.challenge.dto.CandidateDTO;
import com.challenge.mappers.CandidateMapper;
import com.challenge.service.impl.CandidateService;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired 
    private CandidateService candidateService;

    private CandidateMapper candidateMapper = Mappers.getMapper(CandidateMapper.class);

    @GetMapping("/{userId}/{companyId}/{accelerationId}")
    public ResponseEntity<CandidateDTO> findById(
        @PathVariable("userId") Long userId,
        @PathVariable("companyId") Long companyId,
        @PathVariable("accelerationId") Long accelerationId){
            
        return candidateService.findById(userId, companyId, accelerationId)
            .map(candidate -> ResponseEntity.ok(candidateMapper.map(candidate)))
            .orElseGet(() -> ResponseEntity.ok(new CandidateDTO()));  
           
    }

    @GetMapping
    public List<CandidateDTO> findByCompanyOrAcceleration(
        @RequestParam(required = false) Long companyId,
        @RequestParam(required = false) Long accelerationId
    ){
        List<CandidateDTO> candidates = new ArrayList<>();

        if(companyId!=null){
            candidates = candidateMapper.map(candidateService.findByCompanyId(companyId)); 
        }
        if(accelerationId!=null){
            candidates = candidateMapper.map(candidateService.findByAccelerationId(accelerationId));
        }

        return candidates;
    }



    
}