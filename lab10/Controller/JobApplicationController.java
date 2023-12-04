package com.example.lab10.Controller;

import com.example.lab10.ApiResponse.Api;
import com.example.lab10.Model.JobApplication;
import com.example.lab10.Service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/jobApplication")
@RequiredArgsConstructor
public class JobApplicationController {
    private final JobApplicationService jobApplicationService;
    @GetMapping("/get")
    public ResponseEntity getJobApplications(){
        return ResponseEntity.status(HttpStatus.OK).body(jobApplicationService.getJobApplications());
    }
    @PostMapping("/add")
    public ResponseEntity addJobApplication(@RequestBody@Valid JobApplication jobApplication, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(message));
        }
       Integer status =jobApplicationService.addJobApplication(jobApplication);
        switch (status){
            case 1:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("job post id wrong"));
            case 2:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("user id wrong"));
            default:
                return ResponseEntity.status(HttpStatus.OK).body("job application added");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteJobApplication(@PathVariable Integer id){
        Boolean isDeleted=jobApplicationService.deleteJobApplication(id);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new Api("job application deleted"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("wrong id"));
    }
}
