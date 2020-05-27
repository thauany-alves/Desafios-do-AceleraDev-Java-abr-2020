package com.challenge.endpoints;

import java.util.List;

import com.challenge.entity.Acceleration;
import com.challenge.exceptions.ResourceNotFoundException;
import com.challenge.service.impl.AccelerationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acceleration")
public class AccelerationController {
    
    @Autowired
    private AccelerationService accelerationService;

    @GetMapping("/{id}")
    public ResponseEntity<Acceleration> findById(@PathVariable("id") Long id){
        return new ResponseEntity<Acceleration>(accelerationService.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Acceleration")), HttpStatus.OK);
    }

    @GetMapping
    public List<Acceleration> findByCompanyId(@RequestParam(required = false) Long companyId){
        return accelerationService.findByCompanyId(companyId);
    }
}