package com.challenge.endpoints;

import java.util.ArrayList;
import java.util.List;

import com.challenge.entity.Company;
import com.challenge.exceptions.ResourceNotFoundException;
import com.challenge.service.impl.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable("id") Long id){
        return new ResponseEntity<Company>(companyService.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Company")), HttpStatus.OK);
    }

    @GetMapping
    public List<Company> findByAccelerationOrUser(
        @RequestParam(required = false) Long accelerationId,
        @RequestParam(required = false) Long userId){

            List<Company> companies = new ArrayList<>();

            if(accelerationId != null) companies.addAll(companyService.findByAccelerationId(accelerationId));
            if(userId != null) companies.addAll(companyService.findByUserId(userId));
            
            return companies;  
    }
    
}