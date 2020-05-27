package com.challenge.endpoints;

import java.util.ArrayList;
import java.util.List;

import com.challenge.entity.User;
import com.challenge.exceptions.ResourceNotFoundException;
import com.challenge.service.impl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long id){
        return new ResponseEntity<User>(this.userService.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User")),HttpStatus.OK );
    }

    @GetMapping
    public List<User> findByAccelerationNameOrCompanyId(
        @RequestParam(required = false) String accelerationName, 
        @RequestParam(required = false) Long companyId){
        List<User> users = new ArrayList<>();
       
        if(accelerationName != null){
            users.addAll(userService.findByAccelerationName(accelerationName));
        } 
        if(companyId != null){
            users.addAll(userService.findByCompanyId(companyId)) ;
        }  
        return users;
    }


    
}