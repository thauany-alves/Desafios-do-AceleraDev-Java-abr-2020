package com.challenge.endpoints;

import java.util.List;

import com.challenge.entity.Challenge;
import com.challenge.service.impl.ChallengeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/challenge")
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;

    @GetMapping
    public List<Challenge> findByAccelerationAndUser(@RequestParam(required = false) Long accelerationId,
        @RequestParam(required = false) Long userId){

            return challengeService.findByAccelerationIdAndUserId(accelerationId, userId);
    }
    
}