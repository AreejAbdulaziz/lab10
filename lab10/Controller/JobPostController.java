package com.example.lab10.Controller;

import com.example.lab10.ApiResponse.Api;
import com.example.lab10.Model.JobPost;
import com.example.lab10.Service.JobPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/jobPosts")
@RequiredArgsConstructor
public class JobPostController {
    private final JobPostService jobPostService;

    @GetMapping("/get")
    public ResponseEntity getJobPosts(){
        return ResponseEntity.status(HttpStatus.OK).body(jobPostService.getJobPosts());
    }
    @PostMapping("/add")
    public ResponseEntity addJobPost(@RequestBody@Valid JobPost jobPost, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        jobPostService.addJobPost(jobPost);
        return ResponseEntity.status(HttpStatus.OK).body(new Api("job post added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updatePost(@PathVariable Integer id,@RequestBody@Valid JobPost jobPost,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        Boolean isUpdated=jobPostService.updatePost(id, jobPost);
        if(isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new Api("job post updated"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("wrong id"));
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity deletePost(@PathVariable Integer id){
        Boolean isDeleted=jobPostService.deletePost(id);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new Api("post deleted"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("wrong id"));
    }
}
