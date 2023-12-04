package com.example.lab10.Service;

import com.example.lab10.Model.JobApplication;
import com.example.lab10.Repository.JobApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private final UserService userService;
    private final JobPostService jobPostService;
    public List<JobApplication>getJobApplications(){
        return jobApplicationRepository.findAll();
    }
    public Integer addJobApplication(JobApplication jobApplication){
        //userService.getUsers();
        for(int i=0;i<userService.getUsers().size();i++){
            if(userService.getUsers().get(i).getId().equals(jobApplication.getUserId())){
               // jobPostService.getJobPosts();
                for(int j=0;j<jobPostService.getJobPosts().size();j++){
                    if(jobPostService.getJobPosts().get(j).getId().equals(jobApplication.getJobPostId())){
                        jobApplicationRepository.save(jobApplication);
                        return 0;
                    }
                } return 1;//job post id wrong
            }
        } return 2;//user id wrong
    }
    public Boolean deleteJobApplication(Integer id){
        for(int i=0;i<getJobApplications().size();i++){
            if(getJobApplications().get(i).getId().equals(id)){
                JobApplication jobApplication=jobApplicationRepository.getById(id);
                jobApplicationRepository.delete(jobApplication);
                return true;
            }
        }
            return false;
    }
}
