package com.nileshk.challengeApp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChallengeController {
   private final ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService=challengeService;
    }

    @GetMapping("/challenges")
    public ResponseEntity<List<Challenge>> getAllChallenges(){

        return  new ResponseEntity<>(challengeService.getAllChallenges(),HttpStatus.OK);
    }
    @PostMapping("/challenges")
    public ResponseEntity<String> addChallenge(@RequestBody Challenge challenge){
       if( challengeService.addChallenge(challenge)){
           return new ResponseEntity<>("Challenge Added Successfully",HttpStatus.OK);
       }
       return new ResponseEntity<>("Challenge was not added",HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/challenges/{month}")
    public ResponseEntity<Challenge> getChallenge(@PathVariable String month){
        Challenge challenge=challengeService.getChallenge(month);
        if (challenge!=null){
            return new ResponseEntity<>(challenge, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @PutMapping("/challenges/{id}")
    public ResponseEntity<String> updateChallenge(@PathVariable Long id,@RequestBody Challenge updatedChallenge){
        boolean isUpdated=challengeService.updateChallenge(id,updatedChallenge);
        if(isUpdated){
            return new ResponseEntity<>("challenge updated successfully",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Channel not found",HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/challenges/{id}")
    public ResponseEntity<String> deleteChallenge(@PathVariable Long id){
        boolean isDeleted=challengeService.deleteChallenge(id);
        if(isDeleted){
            return new ResponseEntity<>("Challenge Deleted Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Challenge not found",HttpStatus.NOT_FOUND);
    }


}
